package com.example.microservice.controller;

import com.example.microservice.model.RestaurantInfo;
import com.example.microservice.services.RestaurantService;
import com.google.gson.Gson;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;

//import com.mongodb.client.

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
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
	Gson gson = new Gson();

	@GetMapping("/addRest")
	public void AddRestaurant() {
		try {
// *** Method 1
//			InsertOneResult insert = collection.insertOne(new Document()
//					.append("_id",  new ObjectId())
//					.append("RestName", "abc")
//					.append("RestId", 03)
//					.append("RestDescription", "abcd")
//					.append("RestRate", 1));
			;
// *** Method 2
			RestaurantInfo info = new RestaurantInfo(null, 0, null, 0);
			info.setrestId(21);
			info.setrestName("Rest21");
			info.setDescription("ok!!");
			info.setRate(21);

			String json = gson.toJson(info);
			Document doc = Document.parse(json);

			InsertOneResult result = collection.insertOne(doc);
			log.info("insert successful: " + gson.toJson(result));

		} catch (MongoException e) {
			log.error(e);
		}

	}

	@GetMapping("/")
	public List<RestaurantInfo> index() {
		Bson bson = Projections.fields(Projections.include("_id", "restId", "restName", "restDescription", "restRate"));
		MongoCursor<Document> cursor = collection.find().projection(bson).iterator();
		RestaurantInfo info = new RestaurantInfo();
		List<RestaurantInfo> list = new ArrayList <RestaurantInfo>();
		try {
			while (cursor.hasNext()) {
				info = gson.fromJson(cursor.next().toJson(), RestaurantInfo.class);
				list.add(info);
			}

		} finally {
			cursor.close();
		}
//		log.info(list);
		return list;
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
