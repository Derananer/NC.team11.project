package com.example.ruleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RuleServiceApplication
		implements CommandLineRunner
{

	@Autowired
	RuleRepository ruleRepository;

	@Autowired
	GroupElementRepository groupElementRepository;

	@Autowired
	GroupRepository groupRepository;

	public static void main(String[] args) {
		SpringApplication.run(RuleServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ruleRepository.deleteAll();

		ruleRepository.save(new Rule(
				1,
				"onlyOneEmp",
				"не могут пойти в отпуск вместе"
				)
		);
	}

}

