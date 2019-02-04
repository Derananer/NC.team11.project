package com.example.ruleservice;

public class RuledGroup {

    Group group;
    String ruleName;

    public RuledGroup(Group group, String ruleName) {
        this.group = group;
        this.ruleName = ruleName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public Group getGroup() {
        return group;
    }
}
