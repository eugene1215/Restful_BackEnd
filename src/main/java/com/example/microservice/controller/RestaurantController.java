package com.example.microservice.controller;

import com.example.microservice.model.RestaurantInfo;
import com.example.microservice.services.RestaurantService;
import com.google.gson.Gson;

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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;

//import com.mongodb.client.

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;

@Configuration
@ComponentScan
@RestController
public class RestaurantController {
	int min = 01;
	int max = 1000;
	private static final Log log = LogFactory.getLog(RestaurantController.class);

	@Autowired
	private RestaurantService restaurantService;

	ConnectionString connectionString = new ConnectionString(
			"mongodb+srv://123:90493298@cluster0.ebdqh.mongodb.net/RestaurantMicroserviceProject?retryWrites=true&w=majority");
	MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
	MongoClient mongoClient = MongoClients.create(settings);
	MongoDatabase database = mongoClient.getDatabase("RestaurantMicroserviceProject");
	MongoCollection<Document> collection = database.getCollection("RestaurantInfo");

	@GetMapping("/addRest")
	public void AddRestaurant() {
		try {
			InsertOneResult insert = collection.insertOne(new Document()
					.append("_id",  new ObjectId())
					.append("RestName", "99Rest")
					.append("RestId", 99)
					.append("RestDescription", "99RestDesc"));
			
			log.info("insert successful" + insert);
			
		} catch (MongoException e) {
			log.error(e);
		}

	}

	@GetMapping("/")
	public String index() {
		Bson bson = Projections.fields(Projections.include("_id", "RestId", "RestName", "RestDescription"));
		Document doc = collection.find().projection(bson).first();
		log.info("*** " + doc.toJson());
		return doc.toJson();
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
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(restaurant.getrestId()).toUri();

		return ResponseEntity.created(location).build();
	}

}
