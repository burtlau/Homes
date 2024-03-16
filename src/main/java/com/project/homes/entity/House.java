package com.project.homes.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Houses")
public class House extends Property {
    private Boolean hasBasement;
    private int numOfFloors;

    public House(PropertyType type, Double area, Double pricing, Address address, int numOfRooms, int numOfBaths,
                 String pictureUrl, String ownerId, Boolean hasBasement, int numOfFloors) {
        super(type, area, pricing, address, numOfRooms, numOfBaths, pictureUrl, ownerId);
        this.hasBasement = hasBasement;
        this.numOfFloors = numOfFloors;
    }
}
