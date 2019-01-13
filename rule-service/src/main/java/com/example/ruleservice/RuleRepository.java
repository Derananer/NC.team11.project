package com.example.ruleservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RuleRepository extends MongoRepository<Rule, String> {

    Rule findByRuleName(String ruleName);
}
