package com.example.crunchwrapsupreme;

import java.util.Calendar;

public class WorkExperience {
    private String organizationName;
    private String organizationURL;
    private String city;
    private String state;
    private String positionTitle;
    private Calendar beginDate;
    private Calendar endDate;
    private String[] skillsUtilized;
    boolean isEmployerVerified;

    WorkExperience(String orgName,String orgURL, String city, String state, String position, Calendar begin, Calendar end, String[] skills) {
        organizationName = orgName;
        organizationURL = orgURL;
        this.city = city;
        this.state = state;
        positionTitle = position;
        beginDate = begin;
        endDate = end;
        skillsUtilized = skills;
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

    public Calendar getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Calendar beginDate) {
        this.beginDate = beginDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String[] getSkillsUtilized() {
        return skillsUtilized;
    }

    public void setSkillsUtilized(String[] skillsUtilized) {
        this.skillsUtilized = skillsUtilized;
    }

    public boolean isEmployerVerified() {
        return isEmployerVerified;
    }

    public void setEmployerVerified(boolean employerVerified) {
        isEmployerVerified = employerVerified;
    }
}
