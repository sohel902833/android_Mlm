package com.sohelsk.boomcash.DataModel;

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
    private Double monthlyIncome;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("validityDay")
    @Expose
    private Integer validityDay;
    String imageUrl="";
    public CurrentMembership(){}

    public CurrentMembership(Integer levelId, String levelName, Integer numOfJobs, Double monthlyIncome, Double price, Integer validityDay,String imageUrl) {
        this.levelId = levelId;
        this.levelName = levelName;
        this.numOfJobs = numOfJobs;
        this.monthlyIncome = monthlyIncome;
        this.price = price;
        this.validityDay = validityDay;
        this.imageUrl=imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getValidityDay() {
        return validityDay;
    }

    public void setValidityDay(Integer validityDay) {
        this.validityDay = validityDay;
    }

}