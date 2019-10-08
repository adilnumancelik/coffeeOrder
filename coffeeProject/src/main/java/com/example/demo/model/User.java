package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	private int id;
	private int isLoggedIn=0;
	@Id
	private String name;
	private String password;
	public int getId() {
		return id;
	}
	
	public int isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(int isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public User(int id, String name, String password, int isloggedin) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.isLoggedIn=isloggedin;
	}
	
	public User() {}
	
	 @Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	public static User create(String name, String password) {
	        User coffee = new User();
	        coffee.isLoggedIn=0;
	        coffee.setName(name);
	        coffee.setPassword(password);
	        return coffee;
	}
	
	
}
