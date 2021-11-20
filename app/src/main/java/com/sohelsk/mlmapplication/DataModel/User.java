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
    private Integer balance;
    @SerializedName("totalRevenue")
    @Expose
    private Integer totalRevenue;
    @SerializedName("dayIncome")
    @Expose
    private Integer dayIncome;
    @SerializedName("taskRevenue")
    @Expose
    private Integer taskRevenue;
    @SerializedName("referralIncome")
    @Expose
    private Integer referralIncome;
    @SerializedName("teamWorkIncome")
    @Expose
    private Integer teamWorkIncome;
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

    public User(){}
    public User(Integer userId, String name, String password, String phone, String secretCode, String currentLevel, Integer balance, Integer totalRevenue, Integer dayIncome, Integer taskRevenue, Integer referralIncome, Integer teamWorkIncome, Integer availableTask, String joiningDate, Integer myReferCode, Integer referCode, Integer id, Integer todayDone, String time, CurrentMembership currentMembership) {
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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Integer totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Integer getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(Integer dayIncome) {
        this.dayIncome = dayIncome;
    }

    public Integer getTaskRevenue() {
        return taskRevenue;
    }

    public void setTaskRevenue(Integer taskRevenue) {
        this.taskRevenue = taskRevenue;
    }

    public Integer getReferralIncome() {
        return referralIncome;
    }

    public void setReferralIncome(Integer referralIncome) {
        this.referralIncome = referralIncome;
    }

    public Integer getTeamWorkIncome() {
        return teamWorkIncome;
    }

    public void setTeamWorkIncome(Integer teamWorkIncome) {
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