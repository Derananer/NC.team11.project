package com.company.authorisationservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreation {
    public String firstName;
    public String lastName;
    public String secondName;
    public String departmentId;
    public String email;
    public String username;
    public String password;
}
