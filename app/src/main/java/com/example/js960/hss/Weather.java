package com.example.js960.hss;

/**
 * Created by js960 on 2017-11-22.
 */

public class Weather {

    private static int mWeatherLength = 8;

    public double lat;
    public double lon;

    public String[] mWeather = new String[mWeatherLength];

/*
    [0] = Highest Temperature
    [1] = Lowest Temperature
    [2] = Daily Temperature Difference
    [3] = Humidity
    [4] = Finedust
    [5] = Precipitation
    [6] = Pressure
    [7] = Cloud
*/


    public void setLat(double lat){ this.lat = lat;}
    public void setIon(double ion){ this.lon = ion;}

    public void setWeather(String[] weather)
    {
        int index ;
        for(index =0; index<mWeatherLength; index++)
        {
            mWeather[index] = weather[index];
        }
    }


    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }


    public String[] getmWeather() {
        return mWeather;
    }

}
