package com.acs.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    private String strEmailId;

    @Column(name = "password")
    private String strPassword;

    public User(){

    }

    public User(String strFirstName, Integer iUserId, String strLastName, String strEmailId, String strPassword) {
        super();
        this.strFirstName = strFirstName;
        this.iUserId = iUserId;
        this.strLastName = strLastName;
        this.strEmailId = strEmailId;
        this.strPassword = strPassword;
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

    public String getStrEmailId() {
        return strEmailId;
    }

    public void setStrEmailId(String strEmailId) {
        this.strEmailId = strEmailId;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }
}
