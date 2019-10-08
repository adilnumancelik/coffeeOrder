package com.example.demo.controller;


import java.util.List;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CoffeeRep;
import com.example.demo.OrderRep;
import com.example.demo.UserRep;
import com.example.demo.model.Coffee;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.model.loginAnswer;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CoffeeController {
	@Autowired
	CoffeeRep cofdao=new CoffeeRep();
	@Autowired 
	UserRep usdao=new UserRep();
	@Autowired 
	OrderRep ordao=new OrderRep();
	
	@RequestMapping(value ="/giveorder", method = RequestMethod.GET)
	public ResponseEntity<Object> home(){
		return new ResponseEntity<>("Please Give Order", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ordergiven", method = RequestMethod.PUT)
	public loginAnswer orderDetails(@RequestBody List<Order> list) {
		loginAnswer order=new loginAnswer();
		String username;
		String coffeename;
		int amount;
		for(int i=0 ;i<list.size() ; i++) {
			username=list.get(i).getUsername();
			coffeename=list.get(i).getCoffeename();
			amount=list.get(i).getAmount();
			if(cofdao.coffeeFound(coffeename)) {
				if(amount<cofdao.getCoffee(coffeename).getHowManyLeft()) {	
				} else {
					order.setMessage("We don't have enough "+coffeename+" left in our stores");
					order.setSuccess(false);
					return order;
				}
			} else {
				order.setMessage("We don't sell "+coffeename+ " type of coffee.");
				order.setSuccess(false);
				return order;
			}
		}
		
		for(int i=0 ;i<list.size() ; i++) {
			username=list.get(i).getUsername();
			coffeename=list.get(i).getCoffeename();
			amount=list.get(i).getAmount();
			cofdao.update(coffeename, amount);
			ordao.save(list.get(i));
		}
		order.setMessage("Order is successful, enjoy!");
		order.setSuccess(true);
	    return order;
	}

	@PostMapping("/addcoffee")
    public Coffee orderDetails(@RequestBody Coffee coffee) {
		cofdao.save(coffee);
        return cofdao.getCoffee(coffee.getName());
   }
	
	@RequestMapping(value = "/loginn", method = RequestMethod.POST)
	public loginAnswer checkCredentials(@RequestBody User user) {
	    loginAnswer ans=new loginAnswer();  
		if(usdao.userFound(user.getName())) {
			User a=usdao.getUser(user.getName());
	    	if(a.getPassword().equals(user.getPassword())) {
	    		ans.setMessage("You are successfully logged in.");
	    		ans.setSuccess(true);
	    		usdao.updateLogInStatus(user.getName(),1);
	    		return ans;
		    } else {
		    	ans.setMessage("Wrong Password");
		    	ans.setSuccess(false);
		    	return ans;
		    }
	    } 
		ans.setMessage("There is no such user.");
		ans.setSuccess(false);
	    return ans;    
	}
	@RequestMapping(value="/admin/orders", method=RequestMethod.GET)
	public List<Order> orderspage(){
		return ordao.loadAll();
	}
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public loginAnswer logthemout(@RequestBody User user) {
	    loginAnswer ans=new loginAnswer();  
		if(usdao.userFound(user.getName())) {
			User a=usdao.getUser(user.getName());
	    	if(a.isLoggedIn()==1) {
	    		ans.setMessage("You are successfully logged out.");
	    		ans.setSuccess(true);
	    		usdao.updateLogInStatus(user.getName(),0);
	    		return ans;
		    } else {
		    	ans.setMessage("User is already logged out.");
		    	ans.setSuccess(false);
		    	return ans;
		    }
	    } 
		ans.setMessage("There is no such user.");
		ans.setSuccess(false);
	    return ans; 
	}
	@RequestMapping(value = "/isloggedin", method = RequestMethod.GET)
	public boolean isloggedin(@RequestBody User user) {
		if(usdao.userFound(user.getName())) {
			User a=usdao.getUser(user.getName());
	    	if(a.isLoggedIn()==1) {
	    		return true;
		    } else {
		    	return false;
		    }
	    } 
	    return false; 
	}
}

