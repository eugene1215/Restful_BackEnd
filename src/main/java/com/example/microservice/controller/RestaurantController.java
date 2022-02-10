package com.example.microservice.controller;

import com.example.microservice.inft.RestaurantOperation;
import com.example.microservice.model.RestaurantInfo;
import com.example.microservice.services.RestaurantService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;

//@Configuration
//@ComponentScan
@RestController
@RequestMapping("/")
public class RestaurantController implements RestaurantOperation {
	private static final Log log = LogFactory.getLog(RestaurantController.class);

	@Autowired
	private RestaurantService restaurantService;

//	@PostMapping("/createRest")
	@Override
	public String createRestaurant(RestaurantInfo newaddRestaurant) {
		if (restaurantService.checkRestInfo(newaddRestaurant)) {
			restaurantService.insertDB(newaddRestaurant);
		}

		return "Done";
	}

//	@GetMapping("/")
	@Override
	public List<RestaurantInfo> listAllRestaurant() {
		return restaurantService.retrieveAllRestaurant();
	}

//	@PostMapping("/rest/{id}")
	@Override
	public Document retrieveRestaurant(@RequestBody RestaurantInfo newaddRestaurant) {
//		@PathVariable Long id
		return restaurantService.retrieveRestaurant(newaddRestaurant.getRestId());
	}

//	@PostMapping("/deleteRest/{id}")
	@Override
	public String deleteRestaurant(@RequestBody RestaurantInfo newaddRestaurant) {
		restaurantService.deleteRestaurant(newaddRestaurant.getRestId());
		return "Done";
	}

}
