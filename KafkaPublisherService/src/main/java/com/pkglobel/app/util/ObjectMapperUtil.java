package com.pkglobel.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {


  private ObjectMapperUtil() {}

  /**
   * Common method is used to convert java object to json
   * 
   * @param input
   * @return
   */
  public static String convertJavaObjectToJson(Object input) {

    ObjectMapper mapper = new ObjectMapper();
    String jsonString = "";
    try {
      jsonString = mapper.writeValueAsString(input);
    } catch (JsonProcessingException e) {
      jsonString = e.getMessage();
    }
    return jsonString;
  }
}
