package com.example.Homes;

import com.example.Homes.entity.*;

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

    public static final String MONGOURI = "mongodb://localhost:27017/Homes";
    public static final String MONGOURI_TEST = "mongodb://localhost:27017/HomesTest";
}
