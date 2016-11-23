package com.asteroids.asteroids.ws;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Tadeo-developer on 22/11/16.
 */

public class WebServices {

    public static final String URL = "https://api.nasa.gov/neo/rest/v1/feed";

    public static JSONObject serviceAsteroids(String startDate,String endDate,String apiKey){

        HashMap<String,String> params = new HashMap<>();
        params.put("start_date",startDate);
        params.put("end_date",endDate);
        params.put("api_key",apiKey);

        return Request.request(URL,Request.convertParametersToGet(params),false,"GET");
    }

    public static JSONObject serviceAsteroids(String url){

        return Request.request(url,null,false,"GET");
    }


}
