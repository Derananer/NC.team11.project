package com.company.employeeMicroservice;

public class RuledGroup {

    String groupId;
    String ruleName;
    String ruleDescription;

    public RuledGroup() {
    }

    public RuledGroup(String groupId, String ruleName, String ruleDescription) {
        this.groupId = groupId;
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }
}
