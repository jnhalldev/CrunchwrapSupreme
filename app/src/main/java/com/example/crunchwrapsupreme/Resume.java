package com.example.crunchwrapsupreme;

import java.util.ArrayList;
import java.util.List;

public class Resume {
    private List<WorkExperience> experienceList;
    private List<Education> educationList;

    Resume() {
        experienceList = new ArrayList<WorkExperience>();
        educationList = new ArrayList<Education>();
    }

    public void addWorkExperience(WorkExperience experience) {
        this.experienceList.add(experience);
    }

    public List<WorkExperience> getExperienceList() {
        return experienceList;
    }

    public WorkExperience getWorkExperienceAtIndex(int index) {
        return this.experienceList.get(index);
    }

    public void removeWorkExperienceAtIndex(int index) {
        this.experienceList.remove(this.experienceList.get(index));
    }

    public void removeWorkExperience(WorkExperience experience) {
        this.experienceList.remove(experience);
    }

    public void addEducation(Education education) {
        this.educationList.add((education));
    }
}
