package com.example.microservice.inft;

import java.util.List;

import org.bson.Document;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.microservice.model.RestaurantInfo;

@RequestMapping("/default")
public interface RestaurantOperation {
	
	@PostMapping("/createRest")
	String createRestaurant(@NonNull @RequestBody RestaurantInfo newaddRestaurant);
	
	@GetMapping("/allRest")
	List<RestaurantInfo> listAllRestaurant();
	
	@PostMapping("/rest/{id}")
	Document retrieveRestaurant(@RequestBody RestaurantInfo newaddRestaurant);
	
	@PostMapping("/deleteRest/{id}")
	String deleteRestaurant(@RequestBody RestaurantInfo newaddRestaurant);

	
	
}
