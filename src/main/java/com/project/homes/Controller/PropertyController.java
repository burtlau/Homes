package com.project.homes.Controller;

import com.project.homes.entity.*;
import com.project.homes.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

  @Autowired
  private PropertyService propertyService;

  private Property getPropertyByType(Property property) {
    if (property.getType() == PropertyType.HOUSE) {
      return (House) property;
    } else if (property.getType() == PropertyType.APARTMENT) {
      return (Apartment) property;
    } else {
      return property;
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Property createProperty(@RequestBody Property property) {
    return getPropertyByType(propertyService.addProperty(property));
  }

  @PutMapping("/{id}")
  public Property modifyProperty(@PathVariable String id, @RequestBody Property property) {
    return getPropertyByType(propertyService.updateProperty(id, property));
  }

  @DeleteMapping("/{id}")
  public int deleteProperty(@PathVariable String id, @RequestBody Property property) {
    return propertyService.deleteProperty(id, property);
  }

  /* @GetMapping
  public List<? extends Property> findAllProperties() {
    return propertyService.getAllProperties();
  } */

  // getPropertyById and getPropertiesByName not implemented yet in servicce
  /* @GetMapping("/{id}")
  public <T extends Property> findPropertyById(@PathVariable String id) {
    return propertyService.getPropertyById(id);
  }

  @GetMapping("/name/{name}")
  public List<? extends Property> findPropertiesByName(@PathVariable String name) {
    return propertyService.getPropertiesByName(name);
  } */
}
