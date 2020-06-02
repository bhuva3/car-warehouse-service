package com.sample.cws.connections;

import com.sample.cws.config.MongoDBConnectionConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBConnectionManager.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoDBConnectionConfiguration mongoDBConnectionConfiguration;

    public MongoDBConnectionManager() {
    }

    public MongoDBConnectionManager(MongoDBConnectionConfiguration mongoDBConnectionConfiguration) {
        this.mongoDBConnectionConfiguration = mongoDBConnectionConfiguration;
    }

    public MongoTemplate getMongoTemplate(){
        return mongoTemplate;
    }
}
