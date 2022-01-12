package com.sohelsk.boomcash.DataModel;

public class WithdrawModel {
    Double amount;
    String state;
    String time;

    public WithdrawModel(Double amount, String state, String time) {
        this.amount = amount;
        this.state = state;
        this.time = time;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
}
