package com.example.microservice.controller;

import java.net.URI;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Loggers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.microservice.model.Restaurant;
import com.example.microservice.services.RestaurantService;

@ComponentScan
@RestController
public class RestaurantController {

//	private final Logger logger = L);

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping("/hello")
//	@ResponseBody
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/")
//	@ResponseBody
	public ArrayList<Restaurant> index() {

		return restaurantService.retrieveAllRestaurant();
	}



	@PostMapping("/restaurant/{id}")
//	@ResponseBody
	public ResponseEntity<Void> registerRastaurant(@PathVariable int id, @RequestBody Restaurant newRestaurant) {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");    
		Restaurant restaurant = restaurantService.createRestaurant(id, newRestaurant);

		if (restaurant == null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(restaurant.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
