package com.project.homes;

import com.project.homes.entity.*;

public class TestConstants {
    public static final Address ADDRESS = new Address(
            "Canada",
            "Toronto",
            "M5V 0R1");


    public static final House HOUSE_1 = new House(
            PropertyType.HOUSE,
            20.7,
            300000.0,
            ADDRESS,
            2,
            2,
            "",
            "1",
            true,
            2
    );

    public static final House HOUSE_2 = new House(
            PropertyType.HOUSE,
            20.7,
            400000.0,
            ADDRESS,
            2,
            2,
            "",
            "1",
            true,
            2
    );

    public static final Apartment APARTMENT_1 = new Apartment(
            PropertyType.APARTMENT,
            20.7,
            300000.0,
            ADDRESS,
            2,
            2,
            "",
            "1",
            "1514",
            true,
            true,
            9000.0
    );

    public static final String APARTMENT_JSON_1 = "{" +
        "\"type\":\"APARTMENT\"," +
        "\"area\":20.7," +
        "\"pricing\":300000.0," +
        "\"address\":{" +
                "\"country\":\"Canada\"," +
                "\"city\":\"Toronto\"," +
                "\"postCode\":\"M5V 0R1\"" +
        "}," +
        "\"numOfRooms\":2," +
        "\"numOfBaths\":2," +
        "\"pictureUrl\":\"\"," +
        "\"ownerId\":\"1\"," +
        "\"unit\":\"1514\"," +
        "\"hasParkingLot\":true," +
        "\"hasGym\":true," +
        "\"managementFee\":9000.0" +
        "}";

    public static final Apartment APARTMENT_2 = new Apartment(
            PropertyType.APARTMENT,
            20.7,
            500000.0,
            ADDRESS,
            2,
            2,
            "",
            "1",
            "1514",
            true,
            true,
            10000.0
    );
    
    public static final String MONGOURI_TEST = "mongodb://localhost:27017/HomesTest";

    public static final User USER_1 = new User(
            "burtliu",
            "password",
            "Zhongliu",
            "Liu",
            "bert.lau@outlook.com"
    );
}
