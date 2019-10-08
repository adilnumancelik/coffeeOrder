package com.example.demo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.Order;
import com.example.demo.model.User;


@Repository
@CrossOrigin(origins = "http://localhost:4200")
public class OrderRep {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void save(Order coffee) {
      String sql = "insert into Orders (coffeename, username, amount) values (?,?, ?)";
      jdbcTemplate.update(sql, coffee.getCoffeename(),
              coffee.getUsername(), coffee.getAmount());
  }
  public void delete(Order coffee) {
	  String sql="delete from Orders where id=?";
	  jdbcTemplate.update(sql,coffee.getId());
	
  }
  public void delete(int id) {
	  delete(getOrder(id));
  }
  
  public void delete(String name) {
	  delete(getOrder(name));
  }
  /*public void deleteById(int id) {
	  String sql="select * from Coffee where Coffee.id= "+id;
	  jdbcTemplate.update(sql, coffee.getName(),
              coffee.getHowManyLeft(), coffee.getPrice());
  }*/
  public Order getOrder(int id){
	  	String sqlcoffeename= (String) this.jdbcTemplate.queryForObject("select Orders.coffeename from Orders where id="+id, String.class);
	  	String sqlusername= (String) this.jdbcTemplate.queryForObject("select Orders.username from Orders where id="+id, String.class);
	  	int sqlamount= (Integer) this.jdbcTemplate.queryForObject("select Orders.amount from Orders where id="+id, Integer.class);
	    
	  	Order cof = new Order(id,sqlcoffeename,sqlusername,sqlamount);
	  	return cof;
	  }
  public Order getOrder(String name){
	  	int sqlid= (Integer) this.jdbcTemplate.queryForObject("select Orders.id from Orders where coffeename="+"\""+name+"\"", Integer.class);
	    String sqlusername= (String) this.jdbcTemplate.queryForObject("select Orders.username from Users where coffeename="+"\""+name+"\"", String.class);
	    int sqlamount= (Integer) this.jdbcTemplate.queryForObject("select Orders.amount from Orders where coffeename="+"\""+name+"\"", Integer.class);
	    Order cof = new Order(sqlid,name,sqlusername, sqlamount);
	  	return cof;
	  }
  
  
  public String getCoffeName(int id){
  	return (String) this.jdbcTemplate.queryForObject("select Orders.coffeename from Orders where id="+id, String.class);
  }
  
  public Integer getId(String name){
  	return (Integer) this.jdbcTemplate.queryForObject("select Orders.id from Orders where coffeename="+ "\""+name+"\"", Integer.class);
  }
  
  public List<Integer> getListId(String name){
	  List<Integer> list = new ArrayList<>();
	  int max=loadAll().size();
	  for(int i=0 ; i<max ; i++) {
		  Order ith=loadAll().get(i);
		  String x=ith.getCoffeename();
		 
		  if(ith.getCoffeename().equals(name)) {
			  list.add(ith.getId());	  
		  }
	  }
	  return list; 
 }
  /*public boolean coffeeFound(String name) {
	  List<Order> list=loadAll();
	  for(int i=0 ; i<list.size() ; i++) {
		  if(list.get(i).getName().equals(name)) {
			  return true;
		  }
	  }
	  return false;
	}*/

  
  public java.util.List<Order> loadAll() {
      return jdbcTemplate.query("select * from Orders", (resultSet, i) -> {
          return toOrder(resultSet);
      });
  }
  public java.util.List<Order> loadByName(String name) {
      return jdbcTemplate.query("select Orders.coffeename from Orders where coffeename="+ "\""+name+"\"", (resultSet, i) -> {
          return toOrder(resultSet);
      });
  }

  private Order toOrder(ResultSet resultSet) throws SQLException {
      Order person = new Order();
      person.setId(resultSet.getInt("ID"));
      person.setCoffeename(resultSet.getString("COFFEENAME"));
      person.setUsername(resultSet.getString("USERNAME"));
      person.setAmount(resultSet.getInt("AMOUNT"));
      return person;
  }
}