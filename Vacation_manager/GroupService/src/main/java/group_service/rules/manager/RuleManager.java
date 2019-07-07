package group_service.rules.manager;

import group_service.rules.model.Rule;
import group_service.rules.model.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class RuleManager {

    @Autowired
    RuleRepository ruleRepository;


    public Rule getRule(
            String id
    ) {
        return ruleRepository.findById(id).get();
    }


    public Rule[] getRules() {
        List<Rule> rules = ruleRepository.findAll();
        return rules.toArray(new Rule[rules.size()]);
    }
}
