package com.asteroids.asteroids.data;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.asteroids.asteroids.ws.WebServices;

import org.json.JSONObject;

/**
 * Created by Tadeo-developer on 22/11/16.
 */

public class AsteroidLoader extends AsyncTaskLoader<Response> {

    private String url;
    private String startDate;
    private String endDate;
    private String apiKey;
    public AsteroidLoader(Context context, String startDate, String endDate, String apiKey) {
        super(context);
        this.startDate = startDate;
        this.endDate = endDate;
        this.apiKey = apiKey;
    }

    public AsteroidLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public Response loadInBackground() {

        Log.e("PruebaLoader","loadInBackground: "+startDate);
        JSONObject jsonResponse;
        if(url!=null){
            jsonResponse = WebServices.serviceAsteroids(url);
        }else{
            jsonResponse = WebServices.serviceAsteroids(startDate,endDate,apiKey);
        }

        Response response = new Response(jsonResponse);

        return response;
    }

}
