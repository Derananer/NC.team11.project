package group_service.rules;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RuleRepository extends MongoRepository<Rule, String> {

    Rule findByRuleName(String ruleName);
    Rule findByRuleNumber(int ruleNumber);
}
