package com.project.homes.service.impl;

import com.project.homes.TestConstants;
import com.project.homes.entity.Apartment;
import com.project.homes.entity.Property;
import com.project.homes.entity.PropertyType;
import com.project.homes.repo.ApartmentRepository;
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

public class ApartmentAPITest {
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
        when(apartmentRepository.existsById(id)).thenReturn(true);
        int deleteReturn = propertyService.deleteProperty(id, PropertyType.APARTMENT);
        assertEquals(0, deleteReturn);
        verify(apartmentRepository, times(1)).deleteById(id);
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
