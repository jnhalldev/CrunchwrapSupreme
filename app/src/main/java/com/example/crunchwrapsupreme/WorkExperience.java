package com.example.crunchwrapsupreme;

import java.util.Calendar;

public class WorkExperience {
    private String organizationName;
    private String organizationURL;
    private String city;
    private String state;
    private String positionTitle;
    private String beginDate;
    private String endDate;
    boolean isEmployerVerified;

    WorkExperience() {

    }
    WorkExperience(String orgName, String orgURL, String city, String state, String position, String begin, String end) {
        organizationName = orgName;
        organizationURL = orgURL;
        this.city = city;
        this.state = state;
        positionTitle = position;
        beginDate = begin;
        endDate = end;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationURL() {
        return organizationURL;
    }

    public void setOrganizationURL(String organizationURL) {
        this.organizationURL = organizationURL;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isEmployerVerified() {
        return isEmployerVerified;
    }

    public void setEmployerVerified(boolean employerVerified) {
        isEmployerVerified = employerVerified;
    }
}
