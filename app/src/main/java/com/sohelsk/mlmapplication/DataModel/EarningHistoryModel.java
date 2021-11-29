package com.sohelsk.mlmapplication.DataModel;

public class EarningHistoryModel {
    double amount;
    String text,date;

    public EarningHistoryModel(double amount, String text, String date) {
        this.amount = amount;
        this.text = text;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
