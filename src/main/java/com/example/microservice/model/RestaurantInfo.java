package com.example.microservice.model;

import org.springframework.data.annotation.Id;

public class RestaurantInfo {
//	@Id private String id; // for mongob object id
	
	private String restName;
	private int restId;
	private String restDescription;
	private int restRate;
	

	public RestaurantInfo(String restName, int restId, String restDescription, int restRate) {
		super();
		this.restName = restName;
		this.restId = restId;
		this.restDescription = restDescription;
		this.restRate = restRate;
	}
public RestaurantInfo() {
		// TODO Auto-generated constructor stub
	}
	//	public Task() {
//		// TODO Auto-generated constructor stub
//	}
	public String getrestName() {
		return restName;
	}
	public void setrestName(String restName) {
		this.restName = restName;
	}
	public int getrestId() {
		return restId;
	}
	public void setrestId(int restId) {
		this.restId = restId;
	}
	public String getDescription() {
		return restDescription;
	}
	public void setDescription(String restDescription) {
		this.restDescription = restDescription;
	}
	
	public int getRate() {
		return restRate;
	}
	public void setRate(int restRate) {
		this.restRate = restRate;
	}
	@Override
	public String toString() {
		return "Restaurant [restName=" + restName + ", restId=" + restId + ", restDescription=" + restDescription + ", restRate=" + restRate + "]";
	}
	
	
	
}
