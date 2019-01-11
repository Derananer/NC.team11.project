package com.company.UserInteraface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class UserInterafaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserInterafaceApplication.class, args);
		RestTemplate restTemplate = new RestTemplate();
		Employee[] emp = restTemplate.getForObject("http://localhost:6969/getEmployees", Employee[].class);
		for (Employee i:
				emp
			 ) {
			System.out.println(i.toString());
		}

	}

}

