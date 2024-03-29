package com.project.homes.entity;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Address {
    private String country;
    private String city;
    private String postCode;

    public Address(String country, String city, String postCode) {
        this.country = country;
        this.city = city;
        this.postCode = postCode;
    }
}
