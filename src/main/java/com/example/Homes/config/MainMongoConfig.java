//package com.example.Homes.config;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//
//@Configuration
//public class MainMongoConfig extends AbstractMongoClientConfiguration {
//
//    @Value("${spring.data.mongodb.uri}")
//    private String mongoUri;
//
//    @Override
//    protected String getDatabaseName() {
//        // Extract database name from URI
//        return MongoClients.create(mongoUri).getDatabase("Homes").getName();
//    }
//
//    @Override
//    public MongoClient mongoClient() {
//        return MongoClients.create(mongoUri);
//    }
//}