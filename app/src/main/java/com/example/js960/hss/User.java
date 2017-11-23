package com.example.js960.hss;

import java.io.Serializable;

/**
 * Created by js960 on 2017-11-23.
 */

public class User implements Serializable{

    private String Name = "No Data";
    private int Age = -1;
    private String ColdFreq = "No Data";
    private String SerialNumber = "No Data";
    public User(){}
    public User(String Name, String SerialNumber, int age, String ColdFreq)
    {
        this.Name = Name;
        this.SerialNumber=SerialNumber;
        this.Age=age;
        this.ColdFreq=ColdFreq;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }





    public int getAge() {
        return Age;
    }

    public void setAge(int mAge) {
        this.Age = mAge;
    }

    public String getColdFreq() {
        return ColdFreq;
    }

    public void setColdFreq(String mColdFreq) {
        this.ColdFreq = mColdFreq;
    }
}
