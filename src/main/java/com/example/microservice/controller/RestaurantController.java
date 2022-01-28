package com.example.microservice.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

import com.example.microservice.model.Restaurant;
import com.example.microservice.services.RestaurantService;

@ComponentScan
@RestController
public class RestaurantController {

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

	@GetMapping("/abc")
	@ResponseBody
	public String abc() {

		return "abc";
	}

}
