package com.acs.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

// mark as entity to create a table in sql with these fields
@Entity
// optional adding a custom table name , by default it keeps name as class name
@Table(name = "users_table")
public class User {

    // to maintain unique identifier for each entry
    @Id
    private Integer iUserId;
    // give custom column name
    @Column(name = "first_name")
    private String strFirstName;

    @Column(name = "last_name")
    private String strLastName;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "password")
    private String strPassword;

    private List<Integer> followers = new ArrayList<>();

    private List<Integer> following = new ArrayList<>();;

    private String gender;

    public User(){

    }

    public User(Integer iUserId, String strFirstName, String strLastName, String gmail, String strPassword, List<Integer> followers, List<Integer> following, String gender) {
        this.iUserId = iUserId;
        this.strFirstName = strFirstName;
        this.strLastName = strLastName;
        this.gmail = gmail;
        this.strPassword = strPassword;
        this.followers = followers;
        this.following = following;
        this.gender = gender;
    }

    public Integer getiUserId() {
        return iUserId;
    }

    public void setiUserId(Integer iUserId) {
        this.iUserId = iUserId;
    }

    public String getStrFirstName() {
        return strFirstName;
    }

    public void setStrFirstName(String strFirstName) {
        this.strFirstName = strFirstName;
    }

    public String getStrLastName() {
        return strLastName;
    }

    public void setStrLastName(String strLastName) {
        this.strLastName = strLastName;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
