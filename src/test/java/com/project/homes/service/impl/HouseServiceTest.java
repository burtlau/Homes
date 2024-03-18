package com.project.homes.service.impl;

import com.project.homes.TestConstants;
import com.project.homes.entity.House;
import com.project.homes.entity.PropertyType;
import com.project.homes.repo.HouseRepository;
import com.project.homes.service.PropertyService;
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
    private static final String MONGOURI = TestConstants.MONGOURI_TEST;
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
        assertEquals(HOUSE_1, retrievedHouse);
    }


    @Test
    public void DeleteHouseTest()
    {
        House savedHouse = propertyService.addProperty(HOUSE_1);
        int result = propertyService.deleteProperty(savedHouse.getId(), savedHouse);

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
        System.out.println(HOUSE_2.getId());
        System.out.println(retrievedHouse.getId());
        assertEquals(HOUSE_2, retrievedHouse);
    }


}
