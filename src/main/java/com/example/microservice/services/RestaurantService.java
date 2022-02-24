package com.example.microservice.services;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.microservice.controller.RestaurantController;
import com.example.microservice.dao.DBConnection;
import com.example.microservice.model.RestaurantInfo;
import com.google.gson.Gson;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;

@Component
public class RestaurantService {
	private static final Log log = LogFactory.getLog(RestaurantController.class);

	@Autowired
	private DBConnection dbConnection;

	Gson gson = new Gson();

	public boolean checkRestInfo(RestaurantInfo newaddRestaurant) {
		if ( newaddRestaurant.getRestName() == "" || newaddRestaurant.getRestRate() < 0) {
			log.error("Misssing Info... " + newaddRestaurant);
			return false;
		}
		return true;
	}

	public void insertDB(RestaurantInfo newaddRestaurant) {
		
		try {
			// convertJson2Doc
			String json = gson.toJson(addRestaurant(newaddRestaurant));
			Document doc = Document.parse(json);
			dbConnection.collection.insertOne(doc);
//			InsertOneResult result = dbConnection.collection.insertOne(doc);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public RestaurantInfo addRestaurant(RestaurantInfo rest) {
		Random random = new Random();
		int number = random.nextInt(100000);
		RestaurantInfo restaurant = new RestaurantInfo();
		restaurant.setRestName(rest.getRestName());
		restaurant.setRestId(Integer.toString(number));
		restaurant.setRestDescription(rest.getRestDescription());
		restaurant.setRestRate(rest.getRestRate());
		log.info(restaurant);
		return restaurant;
	}

	public List<RestaurantInfo> retrieveAllRestaurant() {
		Bson bson = Projections.fields(Projections.include("_id", "restId", "restName", "restDescription", "restRate"));
		MongoCursor<Document> cursor = dbConnection.collection.find().projection(bson).iterator();
		RestaurantInfo info = new RestaurantInfo();
		List<RestaurantInfo> list = new ArrayList<RestaurantInfo>();
		try {
			while (cursor.hasNext()) {
				info = gson.fromJson(cursor.next().toJson(), RestaurantInfo.class);
				list.add(info);
			}

		} finally {
			cursor.close();
		}
		return list;
	}

	public Document retrieveRestaurant(String id) {
		Bson bson = Projections.fields(Projections.include("restId", "restName", "restDescription", "restRate"));
		Document doc = dbConnection.collection.find(eq("restId", id)).projection(bson).first();
		if (doc == null) {
			log.info("* restId can not be null or item not exist!!! " + doc);
//			ResponseEntity.notFound();
		}
		log.info("*** doc " + doc);
		return doc;

	}
	
	public void deleteRestaurant(String id) {
		Bson bson = eq("restId", id);
		 try {
             DeleteResult result = dbConnection.collection.deleteOne(bson);
             log.info("Deleted document count: " + result.getDeletedCount() + "\n" + "** " + bson);
         } catch (MongoException me) {
             log.info("Unable to delete due to an error: " + me);
         }
		
	}

}
