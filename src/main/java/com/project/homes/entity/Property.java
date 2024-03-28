package com.project.homes.entity;

import lombok.Data;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = House.class, name = "House"),
    @JsonSubTypes.Type(value = Apartment.class, name = "Apartment")
})

@Data
public abstract class Property {
    @Id
    private String id;
    private PropertyType type;
    private Double area;
    private BigDecimal pricing;
    private Address address;
    private int numOfRooms;
    private int numOfBaths;
    private String pictureUrl;
    private String ownerId;


    public Property(PropertyType type, Double area, BigDecimal pricing, Address address, int numOfRooms, int numOfBaths, String pictureUrl, String ownerId) {
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
