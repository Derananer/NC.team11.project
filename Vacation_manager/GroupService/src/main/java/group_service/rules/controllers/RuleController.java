package group_service.rules.controllers;


import group_service.rules.manager.RuleManager;
import group_service.rules.model.Rule;
import group_service.rules.model.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rules")
public class RuleController {

    @Autowired
    RuleManager ruleManager;

    @RequestMapping(value = "/group_service/rules", method = RequestMethod.GET)
    public Rule getRule(
            @RequestParam String id
    ){
        return ruleManager.getRule(id);
    }

    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    public Rule[] getRules(){
        return ruleManager.getRules();
    }
}

