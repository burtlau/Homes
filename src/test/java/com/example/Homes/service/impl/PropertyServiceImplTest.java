package com.example.Homes.service.impl;

import com.example.Homes.DemoApplication;
import com.example.Homes.TestConstants;
import com.example.Homes.entity.House;
import com.example.Homes.repo.HouseRepository;
import com.mongodb.client.*;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.RuntimeConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.extract.UserTempNaming;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Optional;

@SpringBootTest(classes = DemoApplication.class)
public class ServiceIntegrationTest {
    private static final String LOCALHOST = "127.0.0.1";
    private static final String DB_NAME = "HomesTest";
    private static final int MONGO_TEST_PORT = 27028;

    private static final House HOUSE_1 = TestConstants.HOUSE_1;

    @Autowired
    private HouseRepository houseRepository;

    private static MongodProcess mongodProcess;
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    @BeforeAll
    public static void initializeDB() throws IOException {

        RuntimeConfig config = new RuntimeConfig();
        config.setExecutableNaming(new UserTempNaming());

        MongodStarter starter = MongodStarter.getInstance(config);

        MongodExecutable mongoExecutable = starter.prepare(new MongodConfig(Version.V2_2_0, MONGO_TEST_PORT, false));
        mongodProcess = mongoExecutable.start();

        mongoClient = MongoClients.create(String.format("mongodb://adminUser:adminPassword@%s:%s", LOCALHOST, MONGO_TEST_PORT));
        database = mongoClient.getDatabase(DB_NAME);

    }

    @AfterAll
    public static void shutdownDB() throws InterruptedException {
        if (mongoClient != null) {
            mongoClient.close();
        }
        mongodProcess.stop();
    }

    @Test
    public void testAddProperty()
    {
        House savedHouse = houseRepository.save(HOUSE_1);
        Optional<House> gotHouseOptional = houseRepository.findById(savedHouse.getId());
        House gotHouse = gotHouseOptional.orElse(null);
        assertNotNull(gotHouse);
        assertEquals(HOUSE_1.getType(), gotHouse.getType());
    }

}
