package com.sohelsk.mlmapplication.DataModel;

public class MessageModel {
    String message,messageId,messageType,time,sender,imageUrl;

    public MessageModel(){}

    public MessageModel(String message, String messageId, String messageType, String time, String sender, String imageUrl) {
        this.message = message;
        this.messageId = messageId;
        this.messageType = messageType;
        this.time = time;
        this.sender = sender;
        this.imageUrl = imageUrl;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
