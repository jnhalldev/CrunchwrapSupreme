package com.example.crunchwrapsupreme;

public class JobPosting {
    String postingID;
    String userID;
    String company;
    String position;
    String location;
    String shift;
    String address;
    String description;
    String compensationAmount;
    String compensationUnit;

    public JobPosting() {

    }
    public JobPosting(String postingID, String userID, String company, String position, String location, String shift, String address, String description, String compensationAmount, String compensationUnit) {
        this.postingID = postingID;
        this.userID = userID;
        this.company = company;
        this.position = position;
        this.location = location;
        this.shift = shift;
        this.address = address;
        this.description = description;
        this.compensationAmount = compensationAmount;
        this.compensationUnit = compensationUnit;
    }

    public String getNameOfCompanyText() {
        return company;
    }

    public void setNameOfCompanyText(String nameOfCompanyText) {
        company = nameOfCompanyText;
    }

    public String getPositionText() {
        return position;
    }

    public void setPositionText(String positionText) {
        position = positionText;
    }

    public String getLocationText() {
        return location;
    }

    public void setLocationText(String locationText) {
        location = locationText;
    }

    public String getShiftText() {
        return shift;
    }

    public void setShiftText(String shiftText) {
        shift = shiftText;
    }

    public String getAddressText() {
        return address;
    }

    public void setAddressText(String addressText) {
        address = addressText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompensationAmount() {
        return compensationAmount;
    }

    public void setCompensationAmount(String compensationAmount) {
        this.compensationAmount = compensationAmount;
    }

    public String getCompensationUnit() {
        return compensationUnit;
    }

    public void setCompensationUnit(String compensationUnit) {
        this.compensationUnit = compensationUnit;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
