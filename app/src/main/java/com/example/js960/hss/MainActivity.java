package com.example.js960.hss;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBuyProduct;
    private Button mCustomizeUser;
    private Button mSearchRisk;
    private Button mControlDevice;

    private TextView mUserName;
    private TextView mUserAge;
    private TextView mUserColdFreq;

    public static final String User ="User";
    public static final int REQUEST_CODE_CUSTOMIZE=1;

    User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = (TextView) findViewById(R.id.main_TextView_UserName);
        mUserAge = (TextView) findViewById(R.id.main_TextView_UserAge);
        mUserColdFreq = (TextView) findViewById(R.id.main_TextView_UserColdFreq);

        mUserName.setText(mUser.getName());
        mUserAge.setText(""+mUser.getAge());
        mUserColdFreq.setText(mUser.getColdFreq());
        /******************set Button link***********************************/
        mBuyProduct = (Button) findViewById(R.id.main_button_buyProduct);
        mCustomizeUser = (Button) findViewById(R.id.main_button_customize);
        mSearchRisk = (Button) findViewById(R.id.main_button_searchRisk);
        mControlDevice = (Button) findViewById(R.id.main_button_controlDevice);
        /*************************************************************************/

        mBuyProduct.setOnClickListener(this);
        mCustomizeUser.setOnClickListener(this);
        mSearchRisk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.main_button_buyProduct) {
            Intent intent = new Intent(getApplicationContext(), BuyProductActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.main_button_customize){
            Intent intent = new Intent(getApplicationContext(), CustomizeUserActivity.class);
            startActivityForResult(intent, REQUEST_CODE_CUSTOMIZE);

        }
        else if(id== R.id.main_button_searchRisk){
            Intent intent = new Intent(getApplicationContext(), SearchRiskActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.main_button_controlDevice){



        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_CUSTOMIZE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                
                mUser = (User)data.getSerializableExtra(User);
                if(mUser!=null) {

                    mUserName.setText(mUser.getName());
                    mUserAge.setText(""+mUser.getAge());
                    mUserColdFreq.setText(mUser.getColdFreq());
                    Toast.makeText(this, R.string.Customizing_Finished, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, R.string.Error_Setting_User_Info, Toast.LENGTH_SHORT).show();
                }
            }
            if(resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(this, R.string.Error_Setting_User_Info, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
