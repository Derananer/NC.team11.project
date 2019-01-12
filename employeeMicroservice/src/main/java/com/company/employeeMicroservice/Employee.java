package com.company.employeeMicroservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String secondName;
    private String organisationId;

    Employee(String firstName, String lastName, String secondName, String organisationId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.organisationId = organisationId;
    }
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOrganisationId() {
        return organisationId;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setOrganisationId(String organisationId) {
        this.organisationId = organisationId;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString(){
        return String.format(
                "Employee[id=%s, firstName='%s', lastName='%s', secondName='%s', organisationId='%s']",
                id, firstName, lastName, secondName, organisationId);
    }
}
