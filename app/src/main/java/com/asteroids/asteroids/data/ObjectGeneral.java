package com.asteroids.asteroids.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tadeo-developer on 22/11/16.
 */

public class ObjectGeneral implements Serializable{
    private HashMap<String,Object> attributes;

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void addAttribute(String key, Object value){
        if(attributes==null){
            attributes = new HashMap<>();
        }

        attributes.put(key,value);
    }

    public void addAttributesOfJson(JSONObject jsonObject){

        JSONArray names = jsonObject.names();

        for(int i = 0;i<names.length();i++){

            String name = names.optString(i);

            Object element = jsonObject.opt(name);

            if(element!=null && (!(element instanceof JSONObject) && !(element instanceof JSONArray))){
                addAttribute(name,jsonObject.opt(name));
            }else if(element!=null && element instanceof JSONObject){
                ObjectGeneral objectGeneralHijo = new ObjectGeneral();
                objectGeneralHijo.addAttributesOfJson((JSONObject) element);
                addAttribute(name,objectGeneralHijo);
            }else if(element!=null && element instanceof JSONArray){

                JSONArray elements = (JSONArray)element;
                List<ObjectGeneral> objectsArray = new ArrayList<>();
                for(int j = 0;j<elements.length();j++){
                    JSONObject elementHijo = elements.optJSONObject(j);
                    ObjectGeneral objectGeneral = new ObjectGeneral();
                    objectGeneral.addAttributesOfJson(elementHijo);
                    objectsArray.add(objectGeneral);
                }
                addAttribute(name,objectsArray);

            }

        }
    }
}
