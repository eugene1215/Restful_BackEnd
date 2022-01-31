package com.example.microservice.controller;

import com.example.microservice.model.RestaurantInfo;
import com.example.microservice.services.RestaurantService;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;

import java.util.concurrent.ThreadLocalRandom;


import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;

//import com.mongodb.client.

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Configuration
@ComponentScan
@RestController
public class RestaurantController {
	 int min = 01;
	 int max = 1000;
	private static final Log log = LogFactory.getLog(RestaurantController.class) ;

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping("/addRest")
	
//	@ResponseBody hello(@RequestParam(value = "name", defaultValue = "World") String name)
	public void AddRestaurant() {
		ConnectionString connectionString = new ConnectionString("mongodb+srv://123:90493298@cluster0.ebdqh.mongodb.net/RestaurantMicroserviceProject?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("RestaurantMicroserviceProject");
//		database.
		log.info(database.listCollectionNames());
		log.info(database.getCollection("RestaurantInfo", RestaurantInfo.class));
//		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
//	    MongoClientURI uri  = new MongoClientURI("mongodb://localhost:27017/test"); 
//        MongoClient client = new MongoClient(uri);
//        MongoDatabase db = client.getDatabase(uri.getDatabase());
//		MongoOperations ops = new MongoTemplate(MongoClients.create(),"RestaurantMicroserviceProject");
//
//				ops.insert(new RestaurantInfo("dbtest1", randomNum, "nice", randomNum));
//		log.info(ops.findOne(new Query(where("RestName").is("dbtest1")), RestaurantInfo.class));
	}

	@GetMapping("/")
//	@ResponseBody
	public void index() {
		MongoOperations ops = new MongoTemplate(MongoClients.create(),"RestaurantMicroserviceProject");
//		MongoDatabase db = mongoClient.getDatabase("RestaurantMicroserviceProject");
		MongoCollection<Document> restList = ops.getCollection("RestaurantInfo");
		log.info(restList);
		
//		return restList.find();
//		log.info(ops.getCollectionNames());
//		log.info(ops.getCollection("restaurant"));
		//return restaurantService.retrieveAllRestaurant();
	}



	@PostMapping("/restaurant/{id}")
//	@ResponseBody
	public ResponseEntity<Void> registerRastaurant(@PathVariable int id, @RequestBody RestaurantInfo newRestaurant) {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");    
		RestaurantInfo restaurant = restaurantService.createRestaurant(id, newRestaurant);

		if (restaurant == null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(restaurant.getrestId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
