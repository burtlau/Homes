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

    public void setType(PropertyType type) {
        this.type = type;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setPricing(Double pricing) {
        this.pricing = pricing;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public void setNumOfBaths(int numOfBaths) {
        this.numOfBaths = numOfBaths;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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


    public PropertyType getType() {
        return type;
    }

    public String getId()
    {
        return id;
    }

    public Double getArea() {
        return area;
    }

    public Double getPricing() {
        return pricing;
    }

    public Address getAddress() {
        return address;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public int getNumOfBaths() {
        return numOfBaths;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getOwnerId() {
        return ownerId;
    }

}
