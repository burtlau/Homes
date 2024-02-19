package com.example.Homes.service;

import com.example.Homes.entity.Property;
import com.example.Homes.entity.PropertyType;
import org.springframework.stereotype.Service;
import java.util.*;

public interface PropertyService {

    public List<? extends Property> getAllProperties();

    public Property addProperty(Property property);

    public int deleteProperty(String id, PropertyType propertyType);
    public<T extends Property> T updateProperty(String id, Property property);

    /**
     * @return get Properties which should be none.
     * This method is for test purpose only!!
     */
    public void deleteHouseProperties();

};

