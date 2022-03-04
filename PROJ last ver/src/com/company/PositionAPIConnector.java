package com.company;

import org.json.JSONArray;

import java.io.IOException;
import java.text.MessageFormat;


public class PositionAPIConnector {
    private static String API_URL = "https://api.openweathermap.org/geo/1.0/direct?q=";
    private Object Location;


    public JSONArray getJSONArray(String Location) throws IOException {

        RESTAPIConnector restapiConnector = new RESTAPIConnector();
        return restapiConnector.getJSONArray(API_URL+ Location +"&appid=6d3be0cf8606812a5623b693d6b9197e&units=metric");

    }


    private Location mapResponseToLocation(JSONArray response) {
        return mapResponseToLocation(response.getJSONArray(Integer.parseInt("current")));
    }
}
