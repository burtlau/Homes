package com.example.Homes.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.example.Homes.entity.Address;
import com.example.Homes.entity.House;
import com.example.Homes.entity.Property;
import com.example.Homes.entity.PropertyType;
import com.example.Homes.repo.PropertyRepository;
import com.example.Homes.service.impl.PropertyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PropertyServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetProperties() {
        List<Property> expectedProperties = new ArrayList<>();
        when(propertyRepository.findAll()).thenReturn(expectedProperties);
        List<Property> actualProperties = propertyService.getProperties();
        assertEquals(expectedProperties, actualProperties);
    }

    @Test
    void testAddProperty() {
        Address address = new Address(
                "Canada",
                "Toronto",
                "M5V 0R1");

        Property property = new House(
                PropertyType.House,
                80.7,
                900000.0,
                address,
                2,
                2,
                "",
                "1",
                true,
                2
        );
        // Set up the property

        when(propertyRepository.save(property)).thenReturn(property);
        Property savedProperty = propertyService.addProperty(property);

        assertEquals(property, savedProperty);
    }

    @Test
    void testDeleteProperty() {
        Address address = new Address(
                "Canada",
                "Toronto",
                "M5V 0R1");

        Property property = new House(
                PropertyType.House,
                80.7,
                900000.0,
                address,
                2,
                2,
                "",
                "1",
                true,
                2
        );
        String id = "someId";
        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));
        Property deletedProperty = propertyService.deleteProperty(id);
        assertEquals(property, deletedProperty);
        verify(propertyRepository, times(1)).delete(property);
    }

    @Test
    void testUpdateProperty() {
        // Create a sample property
        String id = "1";
        Address address = new Address(
                "Canada",
                "Toronto",
                "M5V 0R1");

        Property existingProperty = new House(
                PropertyType.House,
                80.7,
                900000.0,
                address,
                2,
                2,
                "",
                "1",
                true,
                2
        );

        when(propertyRepository.findById(id)).thenReturn(Optional.of(existingProperty));

        Property updatedProperty = new House(
                PropertyType.House,
                80.7,
                1000000.0,
                address,
                2,
                2,
                "",
                "1",
                true,
                2
        );

        Property updatedResult = propertyService.updateProperty(id, updatedProperty);

        verify(propertyRepository, times(1)).save(existingProperty);
        assertEquals(existingProperty, updatedResult);
    }

    @Test
    void testDeleteAll() {
        List<Property> properties = new ArrayList<>();
        when(propertyRepository.findAll()).thenReturn(properties);
        List<Property> deletedProperties = propertyService.deleteAll();
        assertEquals(properties, deletedProperties);
        verify(propertyRepository, times(properties.size())).delete(any());
    }
}
