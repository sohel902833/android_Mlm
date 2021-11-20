package com.sohelsk.mlmapplication.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentMembership {

    @SerializedName("levelId")
    @Expose
    private Integer levelId;
    @SerializedName("levelName")
    @Expose
    private String levelName;
    @SerializedName("numOfJobs")
    @Expose
    private Integer numOfJobs;
    @SerializedName("monthlyIncome")
    @Expose
    private Integer monthlyIncome;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("validityDay")
    @Expose
    private Integer validityDay;

    public CurrentMembership(){}

    public CurrentMembership(Integer levelId, String levelName, Integer numOfJobs, Integer monthlyIncome, Integer price, Integer validityDay) {
        this.levelId = levelId;
        this.levelName = levelName;
        this.numOfJobs = numOfJobs;
        this.monthlyIncome = monthlyIncome;
        this.price = price;
        this.validityDay = validityDay;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getNumOfJobs() {
        return numOfJobs;
    }

    public void setNumOfJobs(Integer numOfJobs) {
        this.numOfJobs = numOfJobs;
    }

    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getValidityDay() {
        return validityDay;
    }

    public void setValidityDay(Integer validityDay) {
        this.validityDay = validityDay;
    }

}