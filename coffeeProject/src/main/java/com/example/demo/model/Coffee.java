package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coffee {
	private int id;
	@Id
	private String name;
	private int howManyLeft;
	private int price;
	
	public Coffee(int id, String name, int howManyLeft, int price) {
		super();
		this.id = id;
		this.name = name;
		this.howManyLeft = howManyLeft;
		this.price = price;
	}
	public Coffee() {}
	
	public int getId() {
		return id;
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
	public int getHowManyLeft() {
		return howManyLeft;
	}
	public void setHowManyLeft(int howManyLeft) {
		this.howManyLeft = howManyLeft;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	 @Override
	 public String toString() {
	        return "Coffee{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", howManyLeft='" + howManyLeft + '\'' +
	                ", price='" + price + '\'' +
	                '}';
	    }
	
	 public static Coffee create(String name, int howManyLeft, int price) {
	        Coffee coffee = new Coffee();
	        coffee.setName(name);
	        coffee.setHowManyLeft(howManyLeft);
	        coffee.setPrice(price);
	        return coffee;
	    }
	
}

