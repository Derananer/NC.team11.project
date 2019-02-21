package com.example.ruleservice.rule;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rules")
public class RuleController {

    @Autowired
    RuleRepository ruleRepository;

    @RequestMapping(value = "/rule", method = RequestMethod.GET)
    public Rule getRule(
            @RequestParam String id
    ){
        return ruleRepository.findById(id).get();
    }

    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    public Rule[] getRules(){
        List<Rule> rules = ruleRepository.findAll();
        return rules.toArray(new Rule[rules.size()]);
    }
}

