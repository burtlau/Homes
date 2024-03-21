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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Property createProperty(@RequestBody Property property) {
    return propertyService.addProperty(property);
  }

  @PutMapping("/{id}")
  public Property modifyProperty(@PathVariable String id, @RequestBody Property property) {
    return propertyService.updateProperty(id, property);
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
