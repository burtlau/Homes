package com.example.Homes.service.Config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Arrays;

@TestConfiguration
//@Profile("test")
//@EnableMongoRepositories(basePackages = "com.example.Homes.repo")
//@ComponentScan(basePackages = "com.example.Homes.repo")
//@ComponentScan(basePackages = "com.example.Homes.service")
public class TestMongoConfig extends AbstractMongoClientConfiguration {
    private static final String DATABASE_NAME = "HomesTest";
    @Override
    protected String getDatabaseName() {
        return DATABASE_NAME;
    }
}