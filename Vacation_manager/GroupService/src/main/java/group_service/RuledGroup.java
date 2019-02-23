package group_service;

public class RuledGroup {

    private String groupId;
    private String ruleName;
    private String ruleDescription;
    private int ruleNumber;

    public RuledGroup(String groupId, String ruleName, String ruleDescription, int ruleNumber) {
        this.groupId = groupId;
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
        this.ruleNumber = ruleNumber;
    }

    public int getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(int ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public RuledGroup() {
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

    @Override
    public String toString() {
        return "RuledGroup{" +
                "groupId='" + groupId + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleDescription='" + ruleDescription + '\'' +
                ", ruleNumber=" + ruleNumber +
                '}';
    }
}
