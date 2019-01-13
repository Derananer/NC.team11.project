package com.company.ruleservice;

import org.springframework.data.annotation.Id;

public class Group {

    @Id
    private String id;

    private String organisationId;

    private String ruleId;

    public Group(){}

    public Group(String organisationId, String ruleId){
        this.organisationId = organisationId;
        this.ruleId = ruleId;
    }

    public String getOrganisationId() {
        return organisationId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public String getId() {
        return id;
    }

    public void setOrganisationId(String organisationId) {
        this.organisationId = organisationId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public void setId(String id) {
        this.id = id;
    }
}
