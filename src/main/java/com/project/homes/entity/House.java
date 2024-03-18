package com.project.homes.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof House)) return false;
//        if (!super.equals(obj)) return false;
        var house = (House) obj;
        return hasBasement == house.hasBasement && numOfFloors == house.numOfFloors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasBasement, numOfFloors);
    }
}
