package com.sample.cws.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.data.mongodb")
public class MongoDBConnectionConfiguration {

   private String uri;

    public MongoDBConnectionConfiguration() {
    }

    public MongoDBConnectionConfiguration(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
