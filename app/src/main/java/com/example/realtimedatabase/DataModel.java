package com.example.realtimedatabase;

public class DataModel {

    String name,address,mobile;
    DataModel(String name,String address,String mobile) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
    }
    DataModel(){}
public String getName() {return name;}
    public void setName(String name){this.name = name;}
    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}
    public String getMobile(){return mobile;}
    public void setMobile(String mobile){this.mobile = mobile;}

}
