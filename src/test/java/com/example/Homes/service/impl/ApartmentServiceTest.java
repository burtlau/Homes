package com.example.Homes.service.impl;

import com.example.Homes.TestConstants;
import com.example.Homes.entity.Apartment;
import com.example.Homes.entity.House;
import com.example.Homes.entity.Property;
import com.example.Homes.entity.PropertyType;
import com.example.Homes.repo.ApartmentRepository;
import com.example.Homes.repo.HouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ApartmentServiceTest {
    private Apartment apartment1 = TestConstants.APARTMENT_1;
    private Apartment apartment2 = TestConstants.APARTMENT_2;
    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddApartment() {
        when(apartmentRepository.save(apartment1)).thenReturn(apartment1);
        Property savedHouse = propertyService.addProperty(apartment1);
        assertEquals(apartment1, savedHouse);
    }

    @Test
    void testDeleteApartment() {
        String id = "someId";
        when(apartmentRepository.findById(id)).thenReturn(Optional.ofNullable(apartment1));
        Property deletedProperty = propertyService.deleteProperty(id, PropertyType.APARTMENT);
        assertEquals(apartment1, deletedProperty);
        verify(apartmentRepository, times(1)).delete(apartment1);
    }
    @Test
    void updateApartment() {
        String id = "someId";
        when(apartmentRepository.findById(id)).thenReturn(Optional.of(apartment1));
        when(apartmentRepository.save(eq(apartment1))).thenReturn(apartment2);
        Apartment updatedResult = propertyService.updateProperty(id, apartment2);
        verify(apartmentRepository, times(1)).save(eq(apartment1));
        assertEquals(apartment2, updatedResult);
    }

}
