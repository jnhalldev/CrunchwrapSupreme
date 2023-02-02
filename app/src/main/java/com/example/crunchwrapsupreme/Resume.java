package com.example.crunchwrapsupreme;

import java.util.ArrayList;
import java.util.List;

public class Resume {
    private List<WorkExperience> experienceList;
    private List<Education> educationList;
    private List<Skill> skillList;
    private List<Reference> referenceList;

    Resume() {
        experienceList = new ArrayList<WorkExperience>();
        educationList = new ArrayList<Education>();
        skillList = new ArrayList<Skill>();
        referenceList = new ArrayList<Reference>();
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

    public Education getEducationAtIndex(int index) {
        return educationList.get(index);
    }

    public void removeEducationAtIndex(int index) {
        this.educationList.remove(index);
    }

    public void removeEducation(Education education) {
        this.educationList.remove(education);
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public void addSkillToList(Skill skill) {
        this.skillList.add(skill);
    }

    public void removeSkillAtIndex(int index) {
        this.skillList.remove(index);
    }

    public void removeSkill(Skill skill) {
        this.skillList.remove(skill);
    }

    public List<Reference> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(List<Reference> referenceList) {
        this.referenceList = referenceList;
    }

    public void addReferenceToList(Reference reference) {
        this.referenceList.add(reference);
    }

    public void removeReferenceFromList(Reference reference) {
        this.referenceList.remove(reference);
    }

    public void RemoveReferenceAtIndex(int index) {
        this.referenceList.remove(index);
    }
}
