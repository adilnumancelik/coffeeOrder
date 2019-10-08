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

import com.example.demo.model.User;


@Repository
@CrossOrigin(origins = "http://localhost:4200")
public class UserRep {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void save(User coffee) {
      String sql = "insert into Users (name, password) values (?, ?)";
      jdbcTemplate.update(sql, coffee.getName(),
              coffee.getPassword());
  }
  public void updateLogInStatus(String name, int bool) {
	  String sql="update Users set Users.isloggedin ="+bool+" where Users.name="+"\""+name+"\"";
	  jdbcTemplate.update(sql);
  }
  
  public void delete(User coffee) {
	  String sql="delete from Users where id=?";
	  jdbcTemplate.update(sql,coffee.getId());
	
  }
  public void delete(int id) {
	  delete(getUser(id));
  }
  
  public void delete(String name) {
	  delete(getUser(name));
  }
  /*public void deleteById(int id) {
	  String sql="select * from Coffee where Coffee.id= "+id;
	  jdbcTemplate.update(sql, coffee.getName(),
              coffee.getHowManyLeft(), coffee.getPrice());
  }*/
  public User getUser(int id){
	  	String sqlname= (String) this.jdbcTemplate.queryForObject("select Users.name from Users where id="+id, String.class);
	  	int sqlisloggedin=(Integer) this.jdbcTemplate.queryForObject("select Users.isloggedin from Users where name="+id, Integer.class);
	  	String sqlpassword= (String) this.jdbcTemplate.queryForObject("select Users.password from Users where id="+id, String.class);
	  	User cof = new User(id,sqlname,sqlpassword,sqlisloggedin);
	  	return cof;
	  }
  public User getUser(String name){
	  	int sqlid= (Integer) this.jdbcTemplate.queryForObject("select Users.id from Users where name="+"\""+name+"\"", Integer.class);
	    int sqlisloggedin=(Integer) this.jdbcTemplate.queryForObject("select Users.isloggedin from Users where name="+"\""+name+"\"", Integer.class);
	  	String sqlpassword= (String) this.jdbcTemplate.queryForObject("select Users.password from Users where name="+"\""+name+"\"", String.class);
	  	User cof = new User(sqlid,name,sqlpassword,sqlisloggedin);
	  	return cof;
	  }
  
  
  public String getName(int id){
  	return (String) this.jdbcTemplate.queryForObject("select Users.name from Users where id="+id, String.class);
  }
  
  public Integer getId(String name){
  	return (Integer) this.jdbcTemplate.queryForObject("select Users.id from Users where name="+ "\""+name+"\"", Integer.class);
  }
  
  public List<Integer> getListId(String name){
	  List<Integer> list = new ArrayList<>();
	  int max=loadAll().size();
	  for(int i=0 ; i<max ; i++) {
		  User ith=loadAll().get(i);
		  String x=ith.getName();
		 
		  if(ith.getName().equals(name)) {
			  list.add(ith.getId());	  
		  }
	  }
	  return list; 
 }
  public boolean userFound(String name) {
	  List<User> list=loadAll();
	  for(int i=0 ; i<list.size() ; i++) {
		  if(list.get(i).getName().equals(name)) {
			  return true;
		  }
	  }
	  return false;
	}

  
  public java.util.List<User> loadAll() {
      return jdbcTemplate.query("select * from Users", (resultSet, i) -> {
          return toUser(resultSet);
      });
  }
  public java.util.List<User> loadByName(String name) {
      return jdbcTemplate.query("select Users.name from Users where name="+ "\""+name+"\"", (resultSet, i) -> {
          return toUser(resultSet);
      });
  }

  private User toUser(ResultSet resultSet) throws SQLException {
      User person = new User();
      person.setId(resultSet.getInt("ID"));
      person.setName(resultSet.getString("NAME"));
      person.setPassword(resultSet.getString("PASSWORD"));
      person.setLoggedIn(resultSet.getInt("ISLOGGEDIN"));
      return person;
  }
}