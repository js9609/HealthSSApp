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
 * Created by js960 on 2017-11-23.
 */

public class GetRiskClient {

    private static String Risk = "Risk";
    final static String openRiskURL = "http://halarm.tk/mymodel";

    public Risk getRisk(double WT1, double WT2, double WT3, double WT4, double WT5, double WT6, double WT7, double WT8) {
        Risk r = new Risk();
        //String urlString = openWeatherURL + "?lat="+lat+"&lon="+lon;
        String urlString = openRiskURL + "?WT1=" + WT1 + "&WT2=" + WT2 + "&WT3=" + WT3 + "&WT4=" + WT4 + "&WT5=" + WT5 + "&WT6=" + WT6 + "&WT7=" + WT7 + "&WT8=" + WT8;

        try {
            // call API by using HTTPURLConnection
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
//            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            // parse JSON
            r = parseJSON(json);

        } catch (MalformedURLException e) {
            System.err.println("Malformed URL");
            e.printStackTrace();
            return null;

        } catch (JSONException e) {
            System.err.println("JSON parsing error");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("URL Connection failed");
            e.printStackTrace();
            return null;
        }
        // set Risk Object

        return r;
    }

    private Risk parseJSON(JSONObject json) throws JSONException {
        Risk r = new Risk();

        r.setRisk(json.getInt(Risk));

        return r;
    }


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
}
