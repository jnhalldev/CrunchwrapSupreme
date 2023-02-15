package com.example.crunchwrapsupreme;


import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.Job;

public class UserProfile {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phone;
    private String password;
    private String profilePicture;
    private Resume resume;
    private String bio;
    private List<JobPosting> helpWantedPostingKeys;
    private List<Message> inbox;
    private List<Message> sentBox;

    UserProfile() {
    }
    UserProfile(String fN, String lN, String e, String pN, String p) {
        firstName = fN;
        lastName = lN;
        emailAddress = e;
        phone = pN;
        password = p;
        profilePicture = "";
        bio = "";
        helpWantedPostingKeys = new ArrayList<JobPosting>();
        resume = new Resume();
    }

    public void setFirstName(String s) {
        firstName = s;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String s) {
        lastName = s;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String s) {
        emailAddress = s;
    }

    public String getEmail() {
        return emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String pN) {
        phone = pN;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        password = p;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public void addToResume(WorkExperience workExperience) {
        if (resume == null) { resume = new Resume();}
        this.resume.addWorkExperience(workExperience);
    }

    public WorkExperience getWorkExperienceFromIndex(int index) {
        if (resume == null) { resume = new Resume();}
        return this.resume.getWorkExperienceAtIndex(index);
    }

    public void removeWorkExperience(WorkExperience workExperience) {
        if (resume == null) { resume = new Resume();}
        this.resume.removeWorkExperience(workExperience);
    }

    public void addToEducation(Education education) {
        if (resume == null) { resume = new Resume();}
        this.resume.addEducation(education);
    }

    public Education getEducationFromIndex(int index) {
        if (resume == null) { resume = new Resume();}
        return this.resume.getEducationAtIndex(index);
    }

    public void removeEducation(Education education) {
        if (resume == null) { resume = new Resume();}
        this.resume.removeEducation(education);
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void addToSkills(Skill skill) {
        resume.addSkillToList(skill);
    }

    public void removeSkill(Skill skill) {
        resume.removeSkill(skill);
    }

    public void addToReferences(Reference reference) {
        this.resume.addReferenceToList(reference);
    }

    public void removeReference(Reference reference) {
        this.resume.removeReferenceFromList(reference);
    }

    public void addHelpPostingToList(JobPosting jobPosting) {
        if (helpWantedPostingKeys == null) { helpWantedPostingKeys = new ArrayList<JobPosting>();}
        helpWantedPostingKeys.add(jobPosting);
    }

    public boolean removeHelpPostingFromList(JobPosting jobPosting) {
        if (helpWantedPostingKeys == null) { helpWantedPostingKeys = new ArrayList<JobPosting>();}
        return helpWantedPostingKeys.remove(jobPosting);
    }

    public List<JobPosting> getHelpPostingList() {
        return helpWantedPostingKeys;
    }

    public List<Message> getMessagesReceived() {
        return inbox;
    }

    public void addMessageToInbox(Message message) {
        if (this.inbox == null) this.inbox = new ArrayList<Message>();
        this.inbox.add(message);
    }

    public boolean removeMessageFromInbox(Message message) {
        return this.inbox.remove(message);
    }

    public void removeMessageFromInboxAtIndex(int index) {
        if (this.inbox == null) this.inbox = new ArrayList<Message>();
        else if (index < this.inbox.size() - 1) {
            this.inbox.remove(index);
        }
    }

    public void setMessagesReceived(List<Message> messagesReceived) {
        this.inbox = messagesReceived;
    }

    public List<Message> getSentBox() {
        return sentBox;
    }

    public void addMessageToSent(Message message) {
        if(this.sentBox == null) {this.sentBox = new ArrayList<Message>();}
        this.sentBox.add(message);
    }

    public boolean removeMessageFromSent(Message message) {
        if(this.sentBox == null) {this.sentBox = new ArrayList<Message>();}
        return this.sentBox.remove(message);
    }

    public void removeMessageFromSentAtIndex(int index) {
        if(this.sentBox == null) {this.sentBox = new ArrayList<Message>();}
        if (index < this.sentBox.size() - 1) {
            this.sentBox.remove(index);
        }
    }

    public void setSentBox(List<Message> sentBox) {
        this.sentBox = sentBox;
    }
}
