package group_service.rules.model;

import org.springframework.data.annotation.Id;

public class Rule {

    @Id
    private String id;
    private int ruleNumber;
    private String ruleName;
    private String description;

    public Rule(int ruleNumber, String ruleName, String description) {
        this.ruleNumber = ruleNumber;
        this.ruleName = ruleName;
        this.description = description;
    }

    public int getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(int ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public Rule(){}

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

    @Override
    public String toString() {
        return "Rule{" +
                "id='" + id + '\'' +
                ", ruleNumber=" + ruleNumber +
                ", ruleName='" + ruleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
