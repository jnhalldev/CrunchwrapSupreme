package com.example.crunchwrapsupreme;



public class UserProfile {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phone;
    private String password;
    private String profilePicture;

    UserProfile() {

    }
    UserProfile(String fN, String lN, String e, String pN, String p) {
        firstName = fN;
        lastName = lN;
        emailAddress = e;
        phone = pN;
        password = p;
        profilePicture = "";
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
}
