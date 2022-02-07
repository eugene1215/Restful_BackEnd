package com.example.microservice.controller;

import com.example.microservice.dao.DBConnection;
import com.example.microservice.model.RestaurantInfo;
import com.example.microservice.services.RestaurantService;
import com.google.gson.Gson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;

import com.mongodb.client.result.InsertOneResult;

@Configuration
@ComponentScan
@RestController
public class RestaurantController {
	private static final Log log = LogFactory.getLog(RestaurantController.class);

	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private DBConnection dbConnection;

	@PostMapping("/createRest")
	public String createRestaurant(@NonNull @RequestBody RestaurantInfo newaddRestaurant) {

		if (restaurantService.checkRestInfo(newaddRestaurant)) {

			try {
				Gson gson = new Gson();
				String json = gson.toJson(restaurantService.addRestaurant(newaddRestaurant));
				Document doc = Document.parse(json);
				InsertOneResult result = dbConnection.collection.insertOne(doc);

				log.info("*** Insert successful" + result);
			}

			catch (Exception e) {
				log.info("*** Insert failed!!!" + e);
			}
		}

		return "Done";
	}

	@GetMapping("/")
	public List<RestaurantInfo> index() {

		return restaurantService.retrieveAllRestaurant();
	}

}
