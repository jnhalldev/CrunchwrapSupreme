package com.example.crunchwrapsupreme;

import java.text.SimpleDateFormat;

public class Message {
    private String sentUserID;
    private String receivedUserID;
    private String messageSubject;
    private String messageBody;
    private String receivedDateTime;
    private boolean isRead;
    private boolean isReplied;

    public Message() {}
    public Message(String sentUserID, String receivedUserID, String messageSubject, String messageBody) {
        this.sentUserID = sentUserID;
        this.receivedUserID = receivedUserID;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
        this.receivedDateTime = new SimpleDateFormat("MM.dd.yyyy.HH.mm").format(new java.util.Date());
    }

    public String getSentUserID() {
        return sentUserID;
    }

    public void setSentUserID(String sentUserID) {
        this.sentUserID = sentUserID;
    }

    public String getReceivedUserID() {
        return receivedUserID;
    }

    public void setReceivedUserID(String receivedUserID) {
        this.receivedUserID = receivedUserID;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setReceivedDateTime(String receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isReplied() {
        return isReplied;
    }

    public void setReplied(boolean replied) {
        isReplied = replied;
    }
}
