package com.example.js960.hss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchRiskActivity extends AppCompatActivity {

    public Location mLocation; // Lat, Lon

    private Button mCurrentLocation;
    private Button mMap;
    private Button mCity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_risk);

        /******************set Button link***********************************/
        mCurrentLocation = (Button) findViewById(R.id.searchRisk_button_currentLocation);
        mMap = (Button) findViewById(R.id.searchRisk_button_map);
        mCity = (Button) findViewById(R.id.searchRisk_button_city);
        /********************************************************************/


        mCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settLocation();
            }
        });



    }

    private void settLocation()
    {
        double lat = -1;
        double lon = -1;
        /*GPS 기능
        --> LAT, LON 설정 */
        mLocation = new Location(lat, lon);
    }
    private Location getLocation() {return mLocation;}
    private void getWeatherAPI() {}
    private void setWeather(String[] Weather) {}



}
