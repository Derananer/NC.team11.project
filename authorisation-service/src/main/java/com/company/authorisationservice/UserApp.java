package com.company.authorisationservice;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class UserApp {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String secondName;
    private String role;
    private String departmentId;
    private String email;
    private String username;
    private String password;

    public UserApp(
            @NotNull
            String firstName,
            @NotNull
            String lastName,
            String secondName,
            @NotNull
            String departmentId,
            @NotNull
            String email,
            @NotNull
            String username,
            @NotNull
            String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.departmentId = departmentId;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", role='" + role + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

