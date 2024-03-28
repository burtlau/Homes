package com.project.homes;

import com.project.homes.entity.*;
import java.math.BigDecimal;

public class TestConstants {
    public static final Address ADDRESS = new Address(
            "Canada",
            "Toronto",
            "M5V 0R1");


    public static final House HOUSE_1 = new House(
            PropertyType.HOUSE,
            20.7,
            new BigDecimal("300000.0"),
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
            new BigDecimal("400000.0"),
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
            new BigDecimal("300000.0"),
            ADDRESS,
            2,
            2,
            "",
            "1",
            "1514",
            true,
            true,
            new BigDecimal("9000.0")
    );

    public static final String APARTMENT_JSON_1 = "{" +
        "\"type\":\"Apartment\"," +
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
            new BigDecimal("500000.0"),
            ADDRESS,
            2,
            2,
            "",
            "1",
            "1514",
            true,
            true,
            new BigDecimal("10000.0")
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
