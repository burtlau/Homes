package com.example.Homes.service;

import com.example.Homes.entity.Property;
import com.example.Homes.repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public interface PropertyService {

    public List<Property> getProperties();

    public Property addProperty(Property property);

    public Property deleteProperty(String id );
    public Property updateProperty(String id, Property property);

    /**
     * @return get Properties which should be none.
     * This method is for test purpose only!!
     */
    public List<Property> deleteAll();

};

