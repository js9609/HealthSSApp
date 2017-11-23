 package com.example.js960.hss;

import android.content.Context;
import android.renderscript.Double2;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by js960 on 2017-11-22.
 */

public class Weather {

    private static int mWeatherLength = 8;

    public double lat;
    public double lon;

    private static String TAG = "Weather.class";
    private String stringLocation = "NULL";
    public String[] weather = new String[mWeatherLength];
    public double WT1=0, WT2=0, WT3=0, WT4=0, WT5=0, WT6=0, WT7=0, WT8=0;
/*
    [0] = Lowest Temperature
    [1] = Highest Temperature
    [2] = Daily Temperature Difference
    [3] = Humidity
    [4] = Pressure
    [5] = Finedust
    [6] = Precipitation
    [7] = Cloud
*/


    public void setLat(double lat){ this.lat = lat;}
    public void setIon(double ion){ this.lon = ion;}

    public void setWeather(String[] weather)
    {
        int index ;
            for (index = 0; index < mWeatherLength; index++) {
                this.weather[index] = weather[index];
            }
    }

    public String getStringLocation() {
        return stringLocation;
    }

    public void setStringLocation(String stringLocation) {
        this.stringLocation = stringLocation;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String[] getWeather() {
        return weather;
    }

    public void discretize()
    {
        try{WT1 = Double.parseDouble(weather[0]);}catch (Exception e){WT1 = 0 ;}
        try{WT2 = Double.parseDouble(weather[1]);}catch (Exception e){WT2 = 0 ;}
        try{WT3 = Double.parseDouble(weather[2]);}catch (Exception e){WT3 = 0 ;}
        try{WT4 = Double.parseDouble(weather[3]);}catch (Exception e){WT4 = 0 ;}
        try{WT5 = Double.parseDouble(weather[4]);}catch (Exception e){WT5 = 0 ;}
        try{WT6 = Double.parseDouble(weather[5]);}catch (Exception e){WT6 = 0 ;}
        try{WT7 = Double.parseDouble(weather[6]);}catch (Exception e){WT7 = 0 ;}
        try{ WT8 = Double.parseDouble(weather[7]);}catch (Exception e){WT8 = 0 ;}

        Log.i(TAG, ""+WT1);
        Log.i(TAG, ""+WT2);
        Log.i(TAG, ""+WT3);
        Log.i(TAG, ""+WT4);
        Log.i(TAG, ""+WT5);
        Log.i(TAG, ""+WT6);
        Log.i(TAG, ""+WT7);
        Log.i(TAG, ""+WT8);

        if(WT1<5){WT1=0;}
        else if(WT1>-5 && WT1<5){WT1=1;}
        else if(WT1>=5 && WT1<15){WT1=2;}
        else if(WT1>=15 && WT1<20){WT1=3;}
        else if(WT1>=20 && WT1<25){WT1=4;}
        else if(WT1>=25){WT1=5;}


        if(WT2<5){WT2=0;}
        else if(WT2>=5 && WT2<15){WT2=1;}
        else if(WT2>=15 && WT2<25){WT2=2;}
        else if(WT2>=25 && WT2<35){WT2=3;}
        else if(WT2>=35){WT2=4;}

        if(WT3<10){WT3=0;}
        else if(WT3>=10 && WT3<20){WT3=1;}
        else if(WT3>=20){WT3=2;}

        if(WT4<40){WT4=0;}
        else if(WT4>=40 && WT4<60){WT4=1;}
        else if(WT4>=60 && WT4<80){WT4=2;}
        else if(WT4>=80){WT4=3;}
    //Pressure 기준
        if(WT5<1000){WT5 = 0;}
        else {WT5 = 1;}

        //WT6 미세먼지 기준
        if(WT6<40){WT6=0;}
        else if(WT6<80){WT6=1;}
        else if(WT6<120){WT6=2;}
        else{WT6=3;}


        if(WT7>=0 && WT7<20){WT7=0;}
        else if(WT7>=20 && WT7<40){WT7=1;}
        else if(WT7>=40 && WT7<60){WT7=2;}
        else if(WT7>=80){WT7=3;}
        //Cloud 기준 다시 설정해야함
        WT8 = 2;
    }
}
