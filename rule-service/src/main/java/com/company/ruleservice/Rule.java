package com.company.ruleservice;

import org.springframework.data.annotation.Id;

public class Rule {

    @Id
    private String id;

    @Id
    private String ruleName;

    private String description;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
