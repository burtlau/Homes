package com.example.Homes.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@Document(collection = "Apartments")
public class Apartment extends Property{
    private String unit;
    private Boolean hasParkingLot;
    private Boolean hasGym;
    private Double managementFee;

    public Apartment(PropertyType type, Double area, Double pricing, Address address, int numOfRooms,
                     int numOfBaths, String pictureUrl, String ownerId, String unit, boolean hasParkingLot,
                     boolean hasGym, Double managementFee)
    {
        super(type, area, pricing, address, numOfRooms, numOfBaths, pictureUrl, ownerId);
        this.unit = unit;
        this.hasParkingLot = hasParkingLot;
        this.hasGym = hasGym;
        this.managementFee = managementFee;
    }

    public String getUnit() {
        return unit;
    }

    public Boolean getHasParkingLot() {
        return hasParkingLot;
    }

    public Boolean getHasGym() {
        return hasGym;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setHasParkingLot(Boolean hasParkingLot) {
        this.hasParkingLot = hasParkingLot;
    }

    public void setHasGym(Boolean hasGym) {
        this.hasGym = hasGym;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Apartment)) return false;
        if (!super.equals(obj)) return false;
        var apartment = (Apartment) obj;
        return unit.equals(apartment.unit) &&
                hasParkingLot == apartment.hasParkingLot &&
                hasGym == apartment.hasGym &&
                Objects.equals(managementFee, apartment.managementFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unit, hasParkingLot, hasGym, managementFee);
    }
}
