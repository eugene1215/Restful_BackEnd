package com.example.microservice.model;

public class Restaurant {

	private String name;
	private int id;
	private String description;
	private int rate;
	

	public Restaurant(String name, int id, String description, int rate) {
		super();
		this.name = name;
		this.id = id;
		this.description = description;
		this.rate = rate;
	}
//	public Task() {
//		// TODO Auto-generated constructor stub
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", id=" + id + ", description=" + description + ", rate=" + rate + "]";
	}
	
	
	
}
