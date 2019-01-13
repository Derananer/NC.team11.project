package com.company.authorisationservice;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String secondName;
    private String permission;
    private String departmentId;
    private String email;
    private String userName;
    private String password;

    public String getId() {
        return id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPermission() {
        return permission;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}

