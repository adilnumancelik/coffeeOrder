package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.Coffee;
import com.example.demo.model.User;

import java.util.List;

@Component
public class ExampleClient {
    @Autowired
    private CoffeeRep dao;
    @Autowired
    private UserRep daoo;

    public void run() {
       /*System.out.println("-- loading all --");
         List<Coffee> persons =  dao.loadAll();
         persons.forEach(System.out::println);
        
    	dao.delete((dao.getCoffee(1)));
    	
        System.out.println("-- loading all --");
        persons =  dao.loadAll();
        persons.forEach(System.out::println);
        */
    	//Coffee c=Coffee.create("aaab", 10, 15);
    	//User u=User.create("aliberkeozdemir","ali123");
    	//User u2=User.create("berkesu","berkesuuu");
    	//System.out.println(u);
    	//daoo.save(u);
    	//dao.update("aaab", 9);
    	
         //System.out.println(daoo.getUser(4));
       
         

      
    }
}