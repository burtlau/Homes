package com.project.homes.service.impl;

import com.project.homes.TestConstants;
import com.project.homes.entity.Apartment;
import com.project.homes.entity.PropertyType;
import com.project.homes.repo.ApartmentRepository;
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
public class ApartmentServiceTest {
    private static final String MONGOURI = TestConstants.MONGOURI_TEST;
    private static final Apartment APARTMENT_1  = TestConstants.APARTMENT_1;
    private static final Apartment APARTMENT_2  = TestConstants.APARTMENT_2;

    @Autowired
    private ApartmentRepository apartmentRepository;

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
        apartmentRepository.deleteAll();
    }

    @Test
    public void AddApartmentsTest()
    {
        Apartment savedApartment = propertyService.addProperty(APARTMENT_1);
        assertNotNull(savedApartment.getId());

        // Check whether this apartment was saved in apartmentRepository
        Optional<Apartment> retrievedApartmentOptional = apartmentRepository.findById(savedApartment.getId());
        assertTrue(retrievedApartmentOptional.isPresent(), "Apartments not found in repository");

        // Compare the properties of the retrieved apartment with the original apartment
        Apartment retrievedApartment = retrievedApartmentOptional.get();
        assertEquals(APARTMENT_1, retrievedApartment);
    }


    @Test
    public void DeleteApartmentTest()
    {
        Apartment savedApartment = propertyService.addProperty(APARTMENT_1);
        int result = propertyService.deleteProperty(savedApartment.getId(), savedApartment);

        // Verify that the apartment was deleted successfully
        assertEquals(0, result);
        Optional<Apartment> deleteApartmentOptional = apartmentRepository.findById(savedApartment.getId());
        assertFalse(deleteApartmentOptional.isPresent(), "Deleted apartment found in repository");
    }

    @Test
    public void UpdateApartmentsTest()
    {
        // Add a apartment to be updated
        Apartment savedApartment = propertyService.addProperty(APARTMENT_1);

        // Update the apartment
        Apartment updatedApartment = propertyService.updateProperty(savedApartment.getId(), APARTMENT_2);

        // Check whether the apartment was updated successfully
        assertNotNull(updatedApartment);

        // Check whether this updated apartment was saved in apartment
        Optional<Apartment> retrievedApartmentOptional = apartmentRepository.findById(updatedApartment.getId());
        assertTrue(retrievedApartmentOptional.isPresent(), "Updated apartment not found in repository");
        Apartment retrievedApartment = retrievedApartmentOptional.get();
        assertEquals(APARTMENT_2, retrievedApartment);
    }
}
