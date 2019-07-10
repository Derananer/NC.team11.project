package group_service;

import group_service.groups.model.GroupRepository;
import group_service.rules.model.Rule;
import group_service.rules.model.RuleRepository;
import group_service.rules.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GroupServiceApplication
		implements CommandLineRunner
{

	@Autowired
	RuleRepository ruleRepository;

	@Autowired
    GroupRepository groupRepository;

	public static void main(String[] args) {
		SpringApplication.run(GroupServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        ruleRepository.deleteAll();

        ruleRepository.save(new Rule(
                        "onlyOneEmp",
                        "не могут пойти в отпуск вместе"
                )
        );

        ruleRepository.save(new Rule(
                        "standard",
                        ""
                )
        );
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

