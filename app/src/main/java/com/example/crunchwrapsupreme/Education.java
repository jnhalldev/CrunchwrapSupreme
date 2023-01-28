package com.example.crunchwrapsupreme;

import java.util.Calendar;

public class Education {
    private String institution;
    private String earned;
    private String city;
    private String state;
    private Calendar gradYear;

    public Education(String institution, String earned, String city, String state, Calendar gradYear) {
        this.institution = institution;
        this.earned = earned;
        this.city = city;
        this.state = state;
        this.gradYear = gradYear;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getEarned() {
        return earned;
    }

    public void setEarned(String earned) {
        this.earned = earned;
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

    public Calendar getGradYear() {
        return gradYear;
    }

    public void setGradYear(Calendar gradYear) {
        this.gradYear = gradYear;
    }
}
