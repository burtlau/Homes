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
<<<<<<< HEAD:src/main/java/com/project/homes/entity/House.java
=======

    public void setHasBasement(Boolean hasBasement) {
        this.hasBasement = hasBasement;
    }

    public void setNumOfFloors(int numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof House)) return false;
        if (!super.equals(obj)) return false;
        var house = (House) obj;
        return hasBasement == house.hasBasement && numOfFloors == house.numOfFloors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasBasement, numOfFloors);
    }
>>>>>>> d5536fd3002e1216d7889f81d2899e3d7eebc68f:src/main/java/com/example/Homes/entity/House.java
}
