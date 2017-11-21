package com.example.js960.hss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBuyProduct;
    private Button mCustomizeUser;
    private Button mSearchRisk;
    private Button mControlDevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /******************set Button link***********************************/
        mBuyProduct = (Button) findViewById(R.id.main_button_buyProduct);
        mCustomizeUser = (Button) findViewById(R.id.main_button_customize);
        mSearchRisk = (Button) findViewById(R.id.main_button_searchRisk);
        mControlDevice = (Button) findViewById(R.id.main_button_controlDevice);
        /*************************************************************************/






    }
}
