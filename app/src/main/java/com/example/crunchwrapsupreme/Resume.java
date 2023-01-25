package com.example.crunchwrapsupreme;

import java.util.ArrayList;
import java.util.List;

public class Resume {
    private String bio;
    private List<WorkExperience> experience;

    Resume() {
        this.bio = "";
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void addWorkExperience(WorkExperience experience) {
        this.experience.add(experience);
    }

    public List<WorkExperience> getExperienceList() {
        return experience;
    }

    public WorkExperience getWorkExperienceAtIndex(int index) {
        return this.experience.get(index);
    }

    public void removeWorkExperienceAtIndex(int index) {
        this.experience.remove(this.experience.get(index));
    }

    public void removeWorkExperience(WorkExperience experience) {
        this.experience.remove(experience);
    }
}
