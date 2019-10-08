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

import com.example.demo.model.Coffee;
import com.example.demo.model.User;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public class CoffeeRep {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void save(Coffee coffee) {
      String sql = "insert into Coffee (name, howManyLeft, price) values (?, ?, ?)";
      jdbcTemplate.update(sql, coffee.getName(),
              coffee.getHowManyLeft(), coffee.getPrice());
  }
  public void update(String name,int amountToDecrease) {
	  int howmanyleft=getCoffee(name).getHowManyLeft();
	  String sql="update Coffee set Coffee.howmanyleft ="+(howmanyleft-amountToDecrease)+" where Coffee.name="+"\""+name+"\"";
	  jdbcTemplate.update(sql);
  }
  
  public void delete(Coffee coffee) {
	  String sql="delete from Coffee where id=?";
	  jdbcTemplate.update(sql,coffee.getId());
	
  }
  public void delete(int id) {
	  delete(getCoffee(id));
  }
  
  public void delete(String name) {
	  delete(getCoffee(name));
  }
  /*public void deleteById(int id) {
	  String sql="select * from Coffee where Coffee.id= "+id;
	  jdbcTemplate.update(sql, coffee.getName(),
              coffee.getHowManyLeft(), coffee.getPrice());
  }*/
  public Coffee getCoffee(int id){
	  	String sqlname= (String) this.jdbcTemplate.queryForObject("select Coffee.name from Coffee where id="+id, String.class);
	  	int sqlhowmany = (Integer) this.jdbcTemplate.queryForObject("select Coffee.howmanyleft from Coffee where id="+id, Integer.class);
	  	int sqlprice=(Integer) this.jdbcTemplate.queryForObject("select Coffee.price from Coffee where id="+id, Integer.class);
	  	Coffee cof = new Coffee(id,sqlname,sqlhowmany,sqlprice);
	  	return cof;
	  }
  public Coffee getCoffee(String name){
	  	int sqlid= (Integer) this.jdbcTemplate.queryForObject("select Coffee.id from Coffee where name="+"\""+name+"\"", Integer.class);
	  	int sqlhowmany = (Integer) this.jdbcTemplate.queryForObject("select Coffee.howmanyleft from Coffee where name="+"\""+name+"\"", Integer.class);
	  	int sqlprice=(Integer) this.jdbcTemplate.queryForObject("select Coffee.price from Coffee where name="+"\""+name+"\"", Integer.class);
	  	Coffee cof = new Coffee(sqlid,name,sqlhowmany,sqlprice);
	  	return cof;
	  }
  
  
  public String getName( int id){
  	return (String) this.jdbcTemplate.queryForObject("select Coffee.name from Coffee where id="+id, String.class);
  }
  
  public Integer getId(String name){
  	return (Integer) this.jdbcTemplate.queryForObject("select Coffee.id from Coffee where name="+ "\""+name+"\"", Integer.class);
  }
  
  public List<Integer> getListId(String name){
	  List<Integer> list = new ArrayList<>();
	  int max=loadAll().size();
	  for(int i=0 ; i<max ; i++) {
		  Coffee ith=loadAll().get(i);
		  String x=ith.getName();
		 
		  if(ith.getName().equals(name)) {
			  list.add(ith.getId());	  
		  }
	  }
	  return list; 
 }
  public boolean coffeeFound(String name) {
	  List<Coffee> list=loadAll();
	  for(int i=0 ; i<list.size() ; i++) {
		  if(list.get(i).getName().equals(name)) {
			  return true;
		  }
	  }
	  return false;
	}
  
  public java.util.List<Coffee> loadAll() {
      return jdbcTemplate.query("select * from Coffee", (resultSet, i) -> {return toCoffee(resultSet);});
  }

  private Coffee toCoffee(ResultSet resultSet) throws SQLException {
      Coffee person = new Coffee();
      person.setId(resultSet.getInt("ID"));
      person.setName(resultSet.getString("NAME"));
      person.setPrice(resultSet.getInt("PRICE"));
      person.setHowManyLeft(resultSet.getInt("HOWMANYLEFT"));
      return person;
  }
}