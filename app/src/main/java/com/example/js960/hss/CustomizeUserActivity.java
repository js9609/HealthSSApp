package com.example.js960.hss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CustomizeUserActivity extends AppCompatActivity {

    Spinner mSpinnerAge;
    Spinner mSpinnerColdFreq;
    ArrayAdapter<CharSequence> mAgeAdapter;
    ArrayAdapter<CharSequence> mColdFreqAdapter;

    EditText mName;
    EditText mSerialNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_user);

        mSpinnerAge = (Spinner) findViewById(R.id.customize_Spinner_age);
        mSpinnerColdFreq = (Spinner) findViewById(R.id.customize_Spinner_coldFreq);
        mAgeAdapter = ArrayAdapter.createFromResource(this, R.array.Spinner_age, android.R.layout.simple_spinner_item);
        mColdFreqAdapter = ArrayAdapter.createFromResource(this, R.array.Spinner_ColdFreq, android.R.layout.simple_spinner_item);

        setSpinnerAdapter();



    }

    private void setSpinnerAdapter()
    {
        mSpinnerAge.setAdapter(mAgeAdapter);
        mSpinnerColdFreq.setAdapter(mColdFreqAdapter);
    }
}
