package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order {
	private int id;
	@Id
	private String username;
	private String coffeename;
	private int amount;
	public String getUsername() {
		return username;
	}
	public Order(int id, String coffeename, String username, int amount) {
		super();
		this.id = id;
		this.coffeename = coffeename;
		this.username = username;
		this.amount = amount;
	}
	public Order() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCoffeename() {
		return coffeename;
	}
	public void setCoffeename(String coffeename) {
		this.coffeename = coffeename;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Order [username=" + username + ", coffeename=" + coffeename + ", amount=" + amount + "]";
	}
	
}
