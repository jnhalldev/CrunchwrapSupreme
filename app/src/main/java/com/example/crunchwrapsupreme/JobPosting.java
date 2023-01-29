package com.example.crunchwrapsupreme;

public class JobPosting {
    String NameOfCompanyText;
    String PositionText;
    String LocationText;
    String ShiftText;
    String AddressText;
    String RequirmentsText;

    public JobPosting(String nameOfCompanyText, String positionText, String locationText, String shiftText, String addressText, String requirmentsText) {
        NameOfCompanyText = nameOfCompanyText;
        PositionText = positionText;
        LocationText = locationText;
        ShiftText = shiftText;
        AddressText = addressText;
        RequirmentsText = requirmentsText;
    }

    public String getNameOfCompanyText() {
        return NameOfCompanyText;
    }

    public void setNameOfCompanyText(String nameOfCompanyText) {
        NameOfCompanyText = nameOfCompanyText;
    }

    public String getPositionText() {
        return PositionText;
    }

    public void setPositionText(String positionText) {
        PositionText = positionText;
    }

    public String getLocationText() {
        return LocationText;
    }

    public void setLocationText(String locationText) {
        LocationText = locationText;
    }

    public String getShiftText() {
        return ShiftText;
    }

    public void setShiftText(String shiftText) {
        ShiftText = shiftText;
    }

    public String getAddressText() {
        return AddressText;
    }

    public void setAddressText(String addressText) {
        AddressText = addressText;
    }

    public String getRequirmentsText() {
        return RequirmentsText;
    }

    public void setRequirmentsText(String requirmentsText) {
        RequirmentsText = requirmentsText;
    }
}
