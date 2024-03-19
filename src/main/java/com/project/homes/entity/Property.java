package com.project.homes.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public abstract class Property {
    @Id
    private String id;
    private PropertyType type;
    private Double area;
    private Double pricing;
    private Address address;
    private int numOfRooms;
    private int numOfBaths;
    private String pictureUrl;
    private String ownerId;

    public Property() {
        
    }

    public Property(PropertyType type, Double area, Double pricing, Address address, int numOfRooms, int numOfBaths, String pictureUrl, String ownerId) {
        this.type = type;
        this.area = area;
        this.pricing = pricing;
        this.address = address;
        this.numOfRooms = numOfRooms;
        this.numOfBaths = numOfBaths;
        this.pictureUrl = pictureUrl;
        this.ownerId = ownerId;
    }
}
