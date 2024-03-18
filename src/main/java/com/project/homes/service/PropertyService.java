package com.project.homes.service;

import com.project.homes.entity.Property;
import com.project.homes.entity.PropertyType;

import java.util.*;

public interface PropertyService {

    public List<? extends Property> getAllProperties();

    public<T extends Property> T addProperty(Property property);

    public int deleteProperty(String id, Property property);
    public<T extends Property> T updateProperty(String id, Property property);

    /**
     * @return get Properties which should be none.
     * This method is for test purpose only!!
     */
    public void deleteHouseProperties();

};

