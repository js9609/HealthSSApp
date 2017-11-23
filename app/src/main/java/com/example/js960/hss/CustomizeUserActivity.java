package com.example.js960.hss;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CustomizeUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    User mUser;

    Spinner mSpinnerAge;
    Spinner mSpinnerColdFreq;

    int mSelectedAge;
    String mSelectedColdFreq;

    ArrayAdapter<CharSequence> mAgeAdapter;
    ArrayAdapter<CharSequence> mColdFreqAdapter;

    EditText mName;
    EditText mSerialNumber;
    String SerialNumber;
    String Name;

    Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_user);
        mSpinnerAge = (Spinner) findViewById(R.id.customize_Spinner_age);
        mSpinnerColdFreq = (Spinner) findViewById(R.id.customize_Spinner_coldFreq);
        mAgeAdapter = ArrayAdapter.createFromResource(this, R.array.Spinner_age, android.R.layout.simple_spinner_item);
        mColdFreqAdapter = ArrayAdapter.createFromResource(this, R.array.Spinner_ColdFreq, android.R.layout.simple_spinner_item);
        mSend = (Button) findViewById(R.id.customize_button_send); 
        mName = (EditText) findViewById(R.id.customize_EditText_name);
        mSerialNumber = (EditText) findViewById(R.id.customize_EditText_serialNumber);
        mSend.setOnClickListener(this);
        setSpinnerAdapter();




    }

    private void setSpinnerAdapter()
    {
        //Setting Adapter to Spinner
        mSpinnerAge.setAdapter(mAgeAdapter);
        mSpinnerColdFreq.setAdapter(mColdFreqAdapter);
        //Setting Click Listener to Adapter
        mSpinnerAge.setOnItemSelectedListener(this);
        mSpinnerColdFreq.setOnItemSelectedListener(this);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int spinId  = parent.getId();

        if ( spinId == R.id.customize_Spinner_age)
        {
            mSelectedAge = Integer.parseInt(mSpinnerAge.getItemAtPosition(position).toString());
            //Toast.makeText(this, "Age : "+ mSelectedAge, Toast.LENGTH_SHORT).show();
        }
        else if(spinId == R.id.customize_Spinner_coldFreq)
        {
            mSelectedColdFreq = mSpinnerColdFreq.getItemAtPosition(position).toString();
            //Toast.makeText(this, "Cold Freqeuncy :  "+ mSelectedColdFreq, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //When Nothing Changed After Click, it Occur
    }


    private boolean checkSerialNumber()
    {
        Name = mName.getText().toString();
       SerialNumber = mSerialNumber.getText().toString();
        //Check Serial Number with Database;

        return true;
    }
    public void setUserInfo()
    {
        mUser = new User(Name,SerialNumber,mSelectedAge, mSelectedColdFreq);
        /*
        Toast.makeText(this, "User Age: "+ mUser.getAge(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "User ColdFreq: "+ mUser.getColdFreq(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "User Name:"+ mUser.getName(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "User SerialNumber:"+ mUser.getSerialNumber(), Toast.LENGTH_SHORT).show();
        */
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.customize_button_send) {
            if(checkSerialNumber()){
                try{setUserInfo();
                    try{
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent();
                        bundle.putSerializable(MainActivity.User, mUser);
                        intent.putExtras(bundle);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }catch (Exception e)
                    {
                        Toast.makeText(this, R.string.Error_putting_User_Object, Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(this, R.string.Error_Setting_User_Info, Toast.LENGTH_SHORT).show();
                }
            }
            else{
                mUser= null;
                Toast.makeText(this, R.string.Invalid_SerialNumber, Toast.LENGTH_SHORT).show();
                //Invalid User (Serial Number)
            }
        }
    }
}
