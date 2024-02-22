package com.example.Homes.service.impl;

import com.example.Homes.TestConstants;
import com.example.Homes.entity.Apartment;
import com.example.Homes.entity.House;
import com.example.Homes.entity.Property;
import com.example.Homes.entity.PropertyType;
import com.example.Homes.repo.ApartmentRepository;
import com.example.Homes.repo.HouseRepository;
import com.example.Homes.service.PropertyService;
import com.mongodb.client.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;


@SpringBootTest
@ActiveProfiles("test")
public class HouseServiceTest {
    private static String localhost = System.getenv("hostName");
    private static final String DB_NAME = "HomesTest";

    private static String mongoTestPort = System.getenv("portNumber");;

    private static String mongoUsername = System.getenv("mongoUsername");

    private static String mongoPassword = System.getenv("mongoPassword");


    private static final House HOUSE_1 = TestConstants.HOUSE_1;
    private static final House HOUSE_2 = TestConstants.HOUSE_2;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private PropertyService propertyService;

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    @BeforeAll
    public static void initializeDB() throws IOException {
        mongoClient = MongoClients.create(String.format("mongodb://%s:%s@%s:%s/%s", mongoUsername, mongoPassword, localhost, mongoTestPort, DB_NAME));
        database = mongoClient.getDatabase(DB_NAME);
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
    }

    @Test
    public void AddHousesTest()
    {
        House savedHouse = propertyService.addProperty(HOUSE_1);
        assertNotNull(savedHouse.getId());

        // Check whether this house was saved in houseRepository
        Optional<House> retrievedHouseOptional = houseRepository.findById(savedHouse.getId());
        assertTrue(retrievedHouseOptional.isPresent(), "House not found in repository");

        // Compare the properties of the retrieved house with the original house
        House retrievedHouse = retrievedHouseOptional.get();
        assertEquals(HOUSE_1.getNumOfFloors(), retrievedHouse.getNumOfFloors());
        assertEquals(HOUSE_1.getPricing(), retrievedHouse.getPricing());
    }


    @Test
    public void DeleteHouseTest()
    {
        House savedHouse = propertyService.addProperty(HOUSE_1);
        int result = propertyService.deleteProperty(savedHouse.getId(), PropertyType.HOUSE);

        // Verify that the house was deleted successfully
        assertEquals(0, result);
        Optional<House> deletedHouseOptional = houseRepository.findById(savedHouse.getId());
        assertFalse(deletedHouseOptional.isPresent(), "Deleted house found in repository");
    }

    @Test
    public void UpdateHousesTest()
    {
        // Add a house to be updated
        House savedHouse = propertyService.addProperty(HOUSE_1);

        // Update the house
        House updatedHouse = propertyService.updateProperty(savedHouse.getId(), HOUSE_2);

        // Check whether the house was updated successfully
        assertNotNull(updatedHouse);

        // Check whether this updated house was saved in houseRepository
        Optional<House> retrievedHouseOptional = houseRepository.findById(updatedHouse.getId());
        assertTrue(retrievedHouseOptional.isPresent(), "Updated house not found in repository");
        House retrievedHouse = retrievedHouseOptional.get();
        assertEquals(HOUSE_2.getPricing(), retrievedHouse.getPricing());
    }


}
