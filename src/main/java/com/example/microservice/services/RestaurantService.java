package com.example.microservice.services;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import com.example.microservice.model.RestaurantInfo;

@Component
public class RestaurantService {

	private SecureRandom random = new SecureRandom();
	


	public RestaurantInfo createRestaurant(int id, RestaurantInfo restaurant) {
//		Restaurant restaurant = new Restaurant("", 0, "", 0);
//		restaurant.setName(name);
//		restaurant.setId(id);
//		restaurant.setDescription(description);
//		restaurant.setRate(rate);
		int randomId = new BigInteger(130, random).intValue();
		restaurant.setrestId(randomId);

		
		return restaurant;
	}
//	public String> retrieveAllRestaurant() {
//		ArrayList<Restaurant> restaurants = new ArrayList<>();
////		restaurants.add(null)
//		MongoClient mongoClient
//		return ;
//	}
	
//	private static ArrayList<Restaurant> restaurants = new ArrayList<>();
////static {
//	Restaurant rest = new Restaurant("", 01, "", 5);
//	restaurants.add(rest);
////}
	
	
}
