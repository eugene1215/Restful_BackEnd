package com.example.microservice.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.microservice.dao.DBConnection;
import com.example.microservice.model.RestaurantInfo;
import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;

@Component
public class RestaurantService {
	@Autowired
//	private RestaurantService restaurantService;
	private DBConnection dbConnection;

	Gson gson = new Gson();

	public RestaurantInfo addRestaurant(RestaurantInfo rest) {
		RestaurantInfo restaurant = new RestaurantInfo();
		restaurant.setRestName(rest.getRestName());
		restaurant.setRestId(rest.getRestId());
		restaurant.setRestDescription(rest.getRestDescription());
		restaurant.setRestRate(rest.getRestRate());

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

}
