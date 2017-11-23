package com.example.js960.hss;

import android.os.AsyncTask;

/**
 * Created by js960 on 2017-11-23.
 */

public class GetRiskTask  extends AsyncTask<Double,Void, Risk> {
    @Override
    protected Risk doInBackground(Double... params) {

        GetRiskClient client = new GetRiskClient();

        double WT1 = params[0];
        double WT2 = params[1];
        double WT3= params[2];
        double WT4= params[3];
        double WT5 = params[4];
        double WT6=params[5];
        double WT7=params[6];
        double WT8=params[7];

        Risk r = client.getRisk(WT1, WT2,WT3,WT4,WT5,WT6,WT7,WT8);
        return r;


    }
}
