package com.project.homes.Controller;

import com.project.homes.TestConstants;
import com.project.homes.entity.*;
import com.project.homes.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PropertyControllerTest {

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    private MockMvc mockMvc;

    public PropertyControllerTest() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(propertyController).build();
    }

    @Test
    void testCreateProperty() throws Exception {
        String apartmentJson = TestConstants.APARTMENT_JSON_1;

        System.out.println(apartmentJson);

        when(propertyService.addProperty(any(Property.class))).thenReturn(TestConstants.APARTMENT_1);

        mockMvc.perform(post("/properties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(apartmentJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testModifyProperty() throws Exception {
        String apartmentJson = TestConstants.APARTMENT_JSON_1;
        String id = "someId";

        when(propertyService.updateProperty(eq(id), any(Property.class))).thenReturn(TestConstants.APARTMENT_1);

        mockMvc.perform(put("/properties/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(apartmentJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteProperty() throws Exception {
        String apartmentJson = TestConstants.APARTMENT_JSON_1;
        String id = "someId";

        when(propertyService.deleteProperty(eq(id), any(Property.class))).thenReturn(0);

        mockMvc.perform(delete("/properties/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(apartmentJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
