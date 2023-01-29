package com.example.crunchwrapsupreme;

import java.util.List;

public class Skills {
    List<String> skills;

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public void removeSkillAtIndex(int index) {
        this.skills.remove(index);
    }
}
