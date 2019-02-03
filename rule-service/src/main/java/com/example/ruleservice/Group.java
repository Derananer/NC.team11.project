package com.example.ruleservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "groups")
public class Group {

    @Id
    private String id;

    private String departmentId;

    private String ruleId;

    public Group(){}

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Group(String departmentId, String ruleId) {
        this.departmentId = departmentId;
        this.ruleId = ruleId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format(
                "Group[id=%s, departmentId='%s', ruleId='%s']",
                id, departmentId, ruleId);
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public void setId(String id) {
        this.id = id;
    }
}
