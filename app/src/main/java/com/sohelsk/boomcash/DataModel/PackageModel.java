package com.sohelsk.boomcash.DataModel;

public class PackageModel {
    int userId;
    String orderNumber,trxNumber,state,time,levelName;

    public PackageModel(int userId, String orderNumber, String trxNumber, String state, String time, String levelName) {
        this.userId = userId;
        this.orderNumber = orderNumber;
        this.trxNumber = trxNumber;
        this.state = state;
        this.time = time;
        this.levelName = levelName;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTrxNumber() {
        return trxNumber;
    }

    public void setTrxNumber(String trxNumber) {
        this.trxNumber = trxNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
