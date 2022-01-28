package com.example.microservice.services;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.example.microservice.model.Restaurant;

@Component
public class RestaurantService {

	private static ArrayList<Restaurant> restaurants = new ArrayList<>();
	static {
		Restaurant rest = new Restaurant("", 01, "", 5);
		restaurants.add(rest);
	}

	public ArrayList<Restaurant> retrieveAllRestaurant() {
		return restaurants;
	}
}
