package com.svenruppert.demo.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class QueryParser {

  public Map<String, String> parseQuery(String query)
      throws UnsupportedEncodingException {
    Map<String, String> result = new HashMap<>();
    if (query == null || query.isEmpty()) return result;
    for (String param : query.split("&")) {
      String[] pair = param.split("=", 2);
      if (pair.length == 2) {
        result.put(URLDecoder.decode(pair[0], "UTF-8"), URLDecoder.decode(pair[1], "UTF-8"));
      }
    }
    return result;
  }
}