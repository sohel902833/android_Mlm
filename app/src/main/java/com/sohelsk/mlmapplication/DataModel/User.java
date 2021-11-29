package com.sohelsk.mlmapplication.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("secretCode")
    @Expose
    private String secretCode;
    @SerializedName("currentLevel")
    @Expose
    private String currentLevel;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("totalRevenue")
    @Expose
    private Double totalRevenue;
    @SerializedName("dayIncome")
    @Expose
    private Double dayIncome;
    @SerializedName("taskRevenue")
    @Expose
    private Double taskRevenue;
    @SerializedName("referralIncome")
    @Expose
    private Double referralIncome;
    @SerializedName("teamWorkIncome")
    @Expose
    private Double teamWorkIncome;
    @SerializedName("availableTask")
    @Expose
    private Integer availableTask;
    @SerializedName("joiningDate")
    @Expose
    private String joiningDate;
    @SerializedName("myReferCode")
    @Expose
    private Integer myReferCode;
    @SerializedName("referCode")
    @Expose
    private Integer referCode;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("todayDone")
    @Expose
    private Integer todayDone;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("currentMembership")
    @Expose
    private CurrentMembership currentMembership;
    private String withdrawAddress="";

    public User(){}
    public User(Integer userId, String name, String password, String phone, String secretCode, String currentLevel, Double balance, Double totalRevenue, Double dayIncome, Double taskRevenue, Double referralIncome, Double teamWorkIncome, Integer availableTask, String joiningDate, Integer myReferCode, Integer referCode, Integer id, Integer todayDone, String time, CurrentMembership currentMembership,String withdrawAddress) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.secretCode = secretCode;
        this.currentLevel = currentLevel;
        this.balance = balance;
        this.totalRevenue = totalRevenue;
        this.dayIncome = dayIncome;
        this.taskRevenue = taskRevenue;
        this.referralIncome = referralIncome;
        this.teamWorkIncome = teamWorkIncome;
        this.availableTask = availableTask;
        this.joiningDate = joiningDate;
        this.myReferCode = myReferCode;
        this.referCode = referCode;
        this.id = id;
        this.todayDone = todayDone;
        this.time = time;
        this.currentMembership = currentMembership;
        this.withdrawAddress=withdrawAddress;
    }


    public String getWithdrawAddress() {
        return withdrawAddress;
    }

    public void setWithdrawAddress(String withdrawAddress) {
        this.withdrawAddress = withdrawAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(Double dayIncome) {
        this.dayIncome = dayIncome;
    }

    public Double getTaskRevenue() {
        return taskRevenue;
    }

    public void setTaskRevenue(Double taskRevenue) {
        this.taskRevenue = taskRevenue;
    }

    public Double getReferralIncome() {
        return referralIncome;
    }

    public void setReferralIncome(Double referralIncome) {
        this.referralIncome = referralIncome;
    }

    public Double getTeamWorkIncome() {
        return teamWorkIncome;
    }

    public void setTeamWorkIncome(Double teamWorkIncome) {
        this.teamWorkIncome = teamWorkIncome;
    }

    public Integer getAvailableTask() {
        return availableTask;
    }

    public void setAvailableTask(Integer availableTask) {
        this.availableTask = availableTask;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Integer getMyReferCode() {
        return myReferCode;
    }

    public void setMyReferCode(Integer myReferCode) {
        this.myReferCode = myReferCode;
    }

    public Integer getReferCode() {
        return referCode;
    }

    public void setReferCode(Integer referCode) {
        this.referCode = referCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTodayDone() {
        return todayDone;
    }

    public void setTodayDone(Integer todayDone) {
        this.todayDone = todayDone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CurrentMembership getCurrentMembership() {
        return currentMembership;
    }

    public void setCurrentMembership(CurrentMembership currentMembership) {
        this.currentMembership = currentMembership;
    }

}