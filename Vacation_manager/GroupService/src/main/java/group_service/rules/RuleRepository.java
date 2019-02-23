package group_service.rules;

import com.example.ruleservice.rule.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RuleRepository extends MongoRepository<Rule, String> {

    Rule findByRuleName(String ruleName);
    Rule findByRuleNumber(int ruleNumber);
}
