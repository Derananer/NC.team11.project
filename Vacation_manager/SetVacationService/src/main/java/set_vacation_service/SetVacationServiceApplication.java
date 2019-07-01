package set_vacation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SetVacationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SetVacationServiceApplication.class, args);
	}

	@Bean(name = "restTemplate")
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}

