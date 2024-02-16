package com.example.Homes.service.impl;

import com.example.Homes.entity.Apartment;
import com.example.Homes.entity.House;
import com.example.Homes.entity.Property;
import com.example.Homes.repo.HouseRepository;
import com.example.Homes.repo.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import com.example.Homes.service.PropertyService;
import com.example.Homes.entity.PropertyType;

import java.util.*;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private final HouseRepository houseRepository;
    @Autowired
    private final ApartmentRepository apartmentRepository;

    private final Map<PropertyType, MongoRepository> repositoryMap;

    public PropertyServiceImpl(HouseRepository houseRepo, ApartmentRepository apartmentRepo)
    {
        this.houseRepository = houseRepo;
        this.apartmentRepository = apartmentRepo;

        repositoryMap = new HashMap<>();
        repositoryMap.put(PropertyType.HOUSE, houseRepository);
        repositoryMap.put(PropertyType.APARTMENT, apartmentRepository);
    }

    @Override
    public List<? extends Property> getAllProperties() {
        List<Property> allProperties = new ArrayList<>();
        for (MongoRepository repository : repositoryMap.values()) {
            allProperties.addAll(repository.findAll());
        }
        return allProperties;
    }

    @Override
    public Property addProperty(Property property) {
        MongoRepository<Property, String> repository = repositoryMap.get(property.getType());
        if (repository != null) {
            return repository.save(property);
        } else {
            throw new IllegalArgumentException("No repository found for property type: " + property.getType());
        }
    }

    @Override
    public Property deleteProperty(String id, PropertyType propertyType) {
        MongoRepository repository = repositoryMap.get(propertyType);
        if (repository != null) {
            Optional<? extends Property> propertyOptional = repository.findById(id);
            if (propertyOptional.isPresent()) {
                Property property = propertyOptional.get();
                repository.delete(property);
                return property;
            } else {
                throw new IllegalArgumentException("Property with ID: " + id + " not found in " + propertyType);
            }
        } else {
            throw new IllegalArgumentException("Invalid repository type: " + propertyType);
        }
    }

    @Override
    public <T extends Property> T updateProperty(String id, Property updatedProperty) {
        PropertyType propertyType = updatedProperty.getType();
        MongoRepository repository = repositoryMap.get(propertyType);
        if(repository == null)
        {
            throw new IllegalArgumentException("No repository found for property type: " + propertyType);
        }

        Optional<Property> originalProperty = repository.findById(id);
        if (!originalProperty.isPresent()) {
            throw new IllegalArgumentException("Property with ID: " + id + " not found.");
        }
        Property propertyToUpdate = originalProperty.get();
        updateCommonProperties(propertyToUpdate, updatedProperty);
        switch (propertyType) {
            case HOUSE:
                updateHouseProperties((House) propertyToUpdate, (House) updatedProperty);
                break;
            case APARTMENT:
                updateApartmentProperties((Apartment) propertyToUpdate, (Apartment) updatedProperty);
                break;
        }

        return (T) repository.save(propertyToUpdate);
    }

    private void updateCommonProperties(Property propertyToUpdate, Property updatedProperty) {
        propertyToUpdate.setAddress(updatedProperty.getAddress());
        propertyToUpdate.setArea(updatedProperty.getArea());
        propertyToUpdate.setPricing(updatedProperty.getPricing());
        propertyToUpdate.setNumOfBaths(updatedProperty.getNumOfBaths());
        propertyToUpdate.setNumOfRooms(updatedProperty.getNumOfRooms());
        propertyToUpdate.setPictureUrl(updatedProperty.getPictureUrl());
        propertyToUpdate.setOwnerId(updatedProperty.getOwnerId());
    }

    private void updateHouseProperties(House houseToUpdate, House updatedHouse) {
        houseToUpdate.setHasBasement(updatedHouse.getHasBasement());
        houseToUpdate.setNumOfFloors(updatedHouse.getNumOfFloors());
    }

    private void updateApartmentProperties(Apartment apartmentToUpdate, Apartment updatedApartment) {
        apartmentToUpdate.setHasGym(updatedApartment.getHasGym());
        apartmentToUpdate.setUnit(updatedApartment.getUnit());
        apartmentToUpdate.setHasParkingLot(updatedApartment.getHasParkingLot());
        apartmentToUpdate.setManagementFee(updatedApartment.getManagementFee());
    }


    @Override
    public void deleteHouseProperties() {
        List<House> houses = houseRepository.findAll();
        for (House house: houses) {
            deleteProperty(house.getId(), PropertyType.HOUSE);
        }
    }
}
