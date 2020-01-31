package com.cemerci.samplelogin.data.model;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-29.
 */
public class User {
    private String id;
    private String email;
    private String name;
    private String lastName;
    private String userPhoto;
    private boolean removeStatus;


    public User() {
    }

    public User(String id, String email, String name, String lastName, String userPhoto, boolean removeStatus) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.userPhoto = userPhoto == null ? "" : userPhoto;
        this.removeStatus = removeStatus;
    }

    public boolean getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(boolean removeStatus) {
        this.removeStatus = removeStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
}
