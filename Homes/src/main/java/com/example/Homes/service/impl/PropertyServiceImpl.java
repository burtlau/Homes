package com.example.Homes.service.impl;

import com.example.Homes.entity.House;
import com.example.Homes.entity.Property;
import com.example.Homes.repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Homes.service.PropertyService;
import com.example.Homes.entity.PropertyType;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepo;

    public PropertyServiceImpl(PropertyRepository propertyRepo) {
        this.propertyRepo = propertyRepo;
    }

    @Override
    public List<Property> getProperties() {
        return propertyRepo.findAll();
    }

    @Override
    public Property addProperty(Property property) {
        return propertyRepo.save(property);
    }

    @Override
    public Property deleteProperty(String id) {
        Optional<Property> propertyOptional = propertyRepo.findById(id);
        if (propertyOptional.isPresent()) {
            Property property = propertyOptional.get();
            propertyRepo.delete(property);
            return property;
        } else {
            throw new IllegalArgumentException("Property with ID: " + id + " not found.");
        }
    }

    @Override
    public Property updateProperty(String id, Property property) {
        Property propertyVar = propertyRepo.findById(id).get();
        propertyVar.setType(property.getType());
        propertyVar.setArea(property.getArea());
        propertyVar.setAddress(property.getAddress());
        propertyVar.setPricing(property.getPricing());
        propertyVar.setNumOfBaths(property.getNumOfBaths());
        propertyVar.setNumOfRooms(property.getNumOfRooms());
        propertyVar.setPictureUrl(property.getPictureUrl());
        propertyVar.setOwnerId(property.getOwnerId());
        propertyRepo.save(propertyVar);
        return propertyVar;
    }


    @Override
    public List<Property> deleteAll() {
        List<Property> properties = getProperties();
        for (Property property: properties) {
            deleteProperty(property.getId());
        }
        return getProperties();
    }
}
