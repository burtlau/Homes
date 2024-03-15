package com.example.Homes.service.impl;

import com.example.Homes.TestConstants;
import com.example.Homes.Web.UserDto;
import com.example.Homes.entity.User;
import com.example.Homes.repo.UserRepository;
import com.example.Homes.service.UserService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String MONGOURI = TestConstants.MONGOURI_TEST;

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
        userRepository.deleteAll();
    }

    @Test
    public void saveRegistrationTest() {
        UserDto userDto = new UserDto();
        userDto.setUserName("burtliu");
        userDto.setPassword("password");
        userDto.setFirstName("Burt");
        userDto.setLastName("Liu");
        userDto.setEmail("burt.liu@outlook.com");
        userService.save(userDto);

        User savedUser = userRepository.findByUsername("burtliu");
        assertNotNull(savedUser);
        assertEquals("Burt", savedUser.getFirstName());
        assertEquals("Liu", savedUser.getLastName());
        assertEquals("burt.liu@outlook.com", savedUser.getEmail());
        assertTrue(passwordEncoder.matches("password", savedUser.getPassword()));
    }

}