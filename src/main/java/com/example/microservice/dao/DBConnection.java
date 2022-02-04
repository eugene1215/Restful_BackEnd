package com.example.microservice.dao;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class DBConnection {

	ConnectionString connectionString = new ConnectionString(
			"mongodb+srv://123:90493298@cluster0.ebdqh.mongodb.net/RestaurantMicroserviceProject?retryWrites=true&w=majority");
	MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
	MongoClient mongoClient = MongoClients.create(settings);
	MongoDatabase database = mongoClient.getDatabase("RestaurantMicroserviceProject");
	public MongoCollection<Document> collection = database.getCollection("RestaurantInfo");

}
