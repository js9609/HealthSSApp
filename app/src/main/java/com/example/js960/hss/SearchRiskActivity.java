package com.example.js960.hss;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchRiskActivity extends AppCompatActivity implements View.OnClickListener{

    public Location mLocation; // Lat, Lon

    private Button mCurrentLocation;
    private ImageButton mMap;
    private Button mSearch;

    //TextView of Weather
    private TextView mHighestT;
    private TextView mLowestT;
    private TextView mDifferenceT;
    private TextView mHumidity;
    private TextView mFinedust;
    private TextView mPrecipitation;
    private TextView mPressure;
    private TextView mCloud;
    private TextView mtxtRisk;


    private FusedLocationProviderClient mFusedLocationClient;
    private TextView mGPS;
    private Weather mWeather;
    private Risk mRisk;
    private boolean isLocation = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_risk);

        /******************set Button link***********************************/
        mCurrentLocation = (Button) findViewById(R.id.searchRisk_button_currentLocation);
        mMap = (ImageButton) findViewById(R.id.searchRisk_button_map);
        mSearch = (Button) findViewById(R.id.searchRisk_button_SearchRisk);
        /********************************************************************/
        mCurrentLocation.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        /*************TextView*****************************************/
        mGPS = (TextView) findViewById(R.id.searchRisk_textView_GPS);
        mHighestT = (TextView) findViewById(R.id.searchRisk_weather_HighestT);
        mLowestT = (TextView) findViewById(R.id.searchRisk_weather_LowestT);
        mDifferenceT = (TextView) findViewById(R.id.searchRisk_weather_DifferenceT);
        mHumidity = (TextView) findViewById(R.id.searchRisk_weather_Humidity);
        mFinedust = (TextView) findViewById(R.id.searchRisk_weather_FineDust);
        mPrecipitation = (TextView) findViewById(R.id.searchRisk_weather_Precipitation);
        mPressure = (TextView) findViewById(R.id.searchRisk_weather_Pressure);
        mCloud = (TextView) findViewById(R.id.searchRisk_weather_Cloud);
        mtxtRisk = (TextView)findViewById(R.id.searchRisk_weather_Risk);
        /******************************************************************/

    }
    private Location getLocation() {return mLocation;}
    //get Weather API is used in setLocation
    private void getWeatherAPI(Location location) {
        OpenWeatherAPITask t= new OpenWeatherAPITask();
        try {
            mWeather = t.execute(location.getLatitude(), location.getLongitude()).get();

            // Set Weather Info attributes here
            //*******************************
            mGPS.setText("LAT: " + location.getLatitude() + " , " + "LON: " + location.getLongitude());
            mLowestT.setText("Lowest Temperature: " + mWeather.weather[0]);
            mHighestT.setText("Highest Temperature: " + mWeather.weather[1]);
            mDifferenceT.setText("Temerature Difference: " + mWeather.weather[2]);
            mHumidity.setText("Humidity: " + mWeather.weather[3]);
            mPressure.setText("Pressure: " + mWeather.weather[4]);
            mFinedust.setText("Finedust: " + mWeather.weather[5]);
            mPrecipitation.setText("Precipitation: " + mWeather.weather[6]);
            mCloud.setText("Cloud: " + mWeather.weather[7]);
            //*********************************
          mWeather.discretize();


        } catch (InterruptedException e) {
            Toast.makeText(SearchRiskActivity.this, R.string.Error_Weather, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (ExecutionException e) {
            Toast.makeText(SearchRiskActivity.this, R.string.Error_Weather, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    @SuppressWarnings("MissingPermission")
    private void setLocation() {
        try{
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(SearchRiskActivity.this);
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                    mLocation = location;
                    getWeatherAPI(location);
            }
        });
    }catch(Exception e)
        {
            Toast.makeText(SearchRiskActivity.this, R.string.Error_GPS, Toast.LENGTH_SHORT).show();
        }

    }
    private void getRisk() {
       GetRiskTask t= new GetRiskTask();
        try{

            mRisk = t.execute(mWeather.WT1, mWeather.WT2, mWeather.WT3, mWeather.WT4, mWeather.WT5, mWeather.WT6, mWeather.WT7, mWeather.WT8).get();
            mtxtRisk.setText("RISK: " + mRisk.getRisk());
        } catch (InterruptedException e) {
            Toast.makeText(this, R.string.Error_Risk , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (ExecutionException e) {
            Toast.makeText(this, R.string.Error_Risk , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
//Get Permission to get Location and get Location Function activated
    private void getPermission()
    {
        //Getting Permission to get GPS Last Location
        Dexter.withActivity(SearchRiskActivity.this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    setLocation();
                    Toast.makeText(SearchRiskActivity.this, "Searched", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.searchRisk_button_currentLocation)
        {
            getPermission();
            isLocation = true;
        }
        else if(id==R.id.searchRisk_button_SearchRisk)
        {
            if(isLocation)
            {
                try{
                    getRisk();
                }
                catch (Exception e) {
                    Toast.makeText(this, R.string.Error_Risk, Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
            else
            {
                Toast.makeText(this, R.string.Search_Location_First, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
