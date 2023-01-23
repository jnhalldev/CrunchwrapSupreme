package com.example.crunchwrapsupreme;

import java.util.ArrayList;

public class Resume {
    private String bio;
    private ArrayList<WorkExperience> experience;

    Resume(String bio, ArrayList<WorkExperience> experience) {
        this.bio = bio;
        this.experience = experience;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setExperience(ArrayList<WorkExperience> experience) {
        this.experience = experience;
    }

    public ArrayList<WorkExperience> getExperience() {
        return experience;
    }

    public void removeWorkExperienceAtIndex(int index) {
        experience.remove(index);
    }
}
