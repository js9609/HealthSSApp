package com.example.js960.hss;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by js960 on 2017-11-22.
 */

public class OpenWeatherAPIClient {

    private static String HTemp = "HighestTemperature";
    private static String LTemp = "LowestTemperature";
    private static String DTemp = "DailyTemperatureDifference";
    private static String Humidity = "Humidity";
    private static String Precipitation = "Precipitation";
    private static String Cloud = "Cloud";
    private static String Pressure = "Pressure";
    private static String Finedust = "Finedust";

    private static int mWeatherLength = 8;


    final static String openWeatherURL = "http://192.168.0.15:3000/getAPI";
    public Weather getWeather(double lat,double lon){
        Weather w = new Weather();
        //String urlString = openWeatherURL + "?lat="+lat+"&lon="+lon;
        String urlString = openWeatherURL + "?lat="+lat+"&lon="+lon;

        try {
            // call API by using HTTPURLConnection
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
//            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            // parse JSON
            w = parseJSON(json);
            w.setIon(lon);
            w.setLat(lat);

        }catch(MalformedURLException e){
            System.err.println("Malformed URL");
            e.printStackTrace();
            return null;

        }catch(JSONException e) {
            System.err.println("JSON parsing error");
            e.printStackTrace();
            return null;
        }catch(IOException e){
            System.err.println("URL Connection failed");
            e.printStackTrace();
            return null;
        }

        // set Weather Object

        return w;
    }

    private Weather parseJSON(JSONObject json) throws JSONException {
        Weather w = new Weather();
       // w.setTemprature(json.getJSONObject("main").getInt("temp"));
        //w.setCity(json.getString("name"));
        //w.setCloudy();

        String[] weather = new String[mWeatherLength];
        weather[0] = json.getString(HTemp);
        weather[1] = json.getString(LTemp);
        weather[2] = json.getString(DTemp);
        weather[3] = json.getString(Humidity);
        weather[4] = json.getString(Finedust);
        weather[5] = json.getString(Precipitation);
        weather[6] = json.getString(Pressure);
        weather[7] = json.getString(Cloud);

        w.setWeather(weather);

        return w;

    }







    /********URL 로 부터 JSON 파일을 읽는 부분 ******************/
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
/************--****************************************/





}
