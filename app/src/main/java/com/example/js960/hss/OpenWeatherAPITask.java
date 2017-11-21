package com.example.js960.hss;

import android.os.AsyncTask;

/**
 * Created by js960 on 2017-11-22.
 */

public class OpenWeatherAPITask extends AsyncTask<Double,Void, Weather>{

    @Override
    protected Weather doInBackground(Double... params) {

        OpenWeatherAPIClient client = new OpenWeatherAPIClient();

        Double lat = params[0];
        Double lon = params[1];
        //API 호출
        Weather w = client.getWeather(lat ,lon);
        return w;
    }
}
