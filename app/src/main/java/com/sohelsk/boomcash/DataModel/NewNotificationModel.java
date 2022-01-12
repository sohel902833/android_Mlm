package com.sohelsk.boomcash.DataModel;

public class NewNotificationModel {
    long notificationId;

    public NewNotificationModel(){}
    public NewNotificationModel(long notificationId) {
        this.notificationId = notificationId;
    }
    public long getNotificationId() {
        return notificationId;
    }
    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }
}
