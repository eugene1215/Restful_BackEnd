package com.example.microservice.services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.example.microservice.model.Restaurant;

@Component
public class RestaurantService {

	private SecureRandom random = new SecureRandom();
	


	public Restaurant createRestaurant(int id, Restaurant restaurant) {
//		Restaurant restaurant = new Restaurant("", 0, "", 0);
//		restaurant.setName(name);
//		restaurant.setId(id);
//		restaurant.setDescription(description);
//		restaurant.setRate(rate);
		int randomId = new BigInteger(130, random).intValue();
		restaurant.setId(randomId);

		
		return restaurant;
	}
	public ArrayList<Restaurant> retrieveAllRestaurant() {
		ArrayList<Restaurant> restaurants = new ArrayList<>();
//		restaurants.add(null)
		return restaurants;
	}
	
//	private static ArrayList<Restaurant> restaurants = new ArrayList<>();
////static {
//	Restaurant rest = new Restaurant("", 01, "", 5);
//	restaurants.add(rest);
////}
	
	
}
