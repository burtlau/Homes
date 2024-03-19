package com.project.homes.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
  public static String convertPropertyToJson(Property property) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(property);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}