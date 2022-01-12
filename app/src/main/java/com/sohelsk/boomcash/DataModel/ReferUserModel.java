package com.sohelsk.boomcash.DataModel;

public class ReferUserModel {
    String name,phone,levelName;
    int userId,myReferCode,referCode;


    public ReferUserModel(String name, String phone,String levelName, int userId, int myReferCode, int referCode) {
        this.name = name;
        this.phone = phone;
        this.userId = userId;
        this.myReferCode = myReferCode;
        this.referCode = referCode;
        this.levelName=levelName;
    }


    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMyReferCode() {
        return myReferCode;
    }

    public void setMyReferCode(int myReferCode) {
        this.myReferCode = myReferCode;
    }

    public int getReferCode() {
        return referCode;
    }

    public void setReferCode(int referCode) {
        this.referCode = referCode;
    }
}
