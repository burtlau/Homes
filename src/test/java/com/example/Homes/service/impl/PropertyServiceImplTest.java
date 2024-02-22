package com.example.Homes.service.impl;

import com.example.Homes.TestConstants;
import com.example.Homes.entity.Apartment;
import com.example.Homes.entity.House;
import com.example.Homes.entity.Property;
import com.example.Homes.repo.ApartmentRepository;
import com.example.Homes.repo.HouseRepository;
import com.example.Homes.service.PropertyService;
import com.mongodb.client.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;


@SpringBootTest
@ActiveProfiles("test")
public class PropertyServiceImplTest {
    private static final String MONGOURI = TestConstants.MONGOURI_TEST;
    private static final House HOUSE_1 = TestConstants.HOUSE_1;
    private static final House HOUSE_2 = TestConstants.HOUSE_2;

    private static final Apartment APARTMENT_1 = TestConstants.APARTMENT_1;
    private static final Apartment APARTMENT_2 = TestConstants.APARTMENT_2;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private PropertyService propertyService;

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    @BeforeAll
    public static void initializeDB(){
        mongoClient = MongoClients.create(MONGOURI);
        database = mongoClient.getDatabase("HomesTest");
    }

    @AfterAll
    public static void shutdownDB() throws InterruptedException {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    @AfterEach
    public void cleanup() {
        houseRepository.deleteAll();
        apartmentRepository.deleteAll();
    }

    @Test
    public void AddPropertyTest()
    {
        houseRepository.saveAll(List.of(HOUSE_1, HOUSE_2));
        apartmentRepository.saveAll(List.of(APARTMENT_1, APARTMENT_2));
        List<? extends Property> allProperties = propertyService.getAllProperties();
        int expectedSize = houseRepository.findAll().size() + apartmentRepository.findAll().size();
        assertEquals(expectedSize, allProperties.size());
    }

}
