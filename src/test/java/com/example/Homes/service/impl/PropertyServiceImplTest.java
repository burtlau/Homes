package com.example.Homes.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.example.Homes.TestConstants;
import com.example.Homes.entity.*;
import com.example.Homes.repo.HouseRepository;
import com.example.Homes.repo.ApartmentRepository;
import com.example.Homes.service.impl.PropertyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PropertyServiceImplTest {

    @Mock
    private HouseRepository houseRepository;
    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    private House house1 = TestConstants.HOUSE_1;
    private House house2 = TestConstants.HOUSE_2;
    private Apartment apartment1 = TestConstants.APARTMENT_1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProperties() {
        when(houseRepository.findAll()).thenReturn(List.of(house1));
        when(apartmentRepository.findAll()).thenReturn(List.of(apartment1));
        List<? extends Property> allProperties = propertyService.getAllProperties();
        verify(houseRepository, times(1)).findAll();
        verify(apartmentRepository, times(1)).findAll();
        assertTrue(allProperties.contains(house1));
        assertTrue(allProperties.contains(apartment1));
    }

}
