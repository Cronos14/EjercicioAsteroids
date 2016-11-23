package com.asteroids.asteroids.data;

import org.json.JSONObject;

/**
 * Created by Tadeo-developer on 22/11/16.
 */

public class Response extends ObjectGeneral {

    public Response(JSONObject jsonObject){
        addAttributesOfJson(jsonObject);
    }

}
