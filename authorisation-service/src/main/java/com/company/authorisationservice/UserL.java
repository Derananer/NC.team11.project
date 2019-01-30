package com.company.authorisationservice;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserL {
    public String userName;
    public String password;
    public String token;
}
