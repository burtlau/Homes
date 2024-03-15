package com.project.homes.service.impl;

import com.project.homes.entity.House;
import com.project.homes.entity.Property;
import com.project.homes.entity.PropertyType;
import com.project.homes.repo.HouseRepository;
import com.project.homes.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HouseAPITest {
    private House house1 = TestConstants.HOUSE_1;
    private House house2 = TestConstants.HOUSE_2;
    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddHouse() {
        when(houseRepository.save(house1)).thenReturn(house1);
        Property savedHouse = propertyService.addProperty(house1);
        assertEquals(house1, savedHouse);
    }

    @Test
    void testDeleteHouse() {
        String id = "someId";
        when(houseRepository.existsById(id)).thenReturn(true);
        int deleteReturn = propertyService.deleteProperty(id, PropertyType.HOUSE);
        assertEquals(0, deleteReturn);
        verify(houseRepository, times(1)).deleteById(id);
    }
    @Test
    void updateHouse() {
        String id = "someId";
        when(houseRepository.findById(id)).thenReturn(Optional.of(house1));
        when(houseRepository.save(eq(house1))).thenReturn(house2);
        House updatedResult = propertyService.updateProperty(id, house2);
        verify(houseRepository, times(1)).save(eq(house1));
        assertEquals(house2, updatedResult);
    }
}
