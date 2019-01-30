package com.company.employeeMicroservice;

import com.company.employeeMicroservice.security.TokenAuthenticationFilter;
import com.company.employeeMicroservice.security.TokenAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
public class EmployeeMicroserviceApplication
		extends WebSecurityConfigurerAdapter
		implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private VacationRepository vacationRepository;

	public static void main(String[] args) {

		SpringApplication.run(EmployeeMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//ArrayList<Employee> emps = new ArrayList<>();
		//emps.add(new Employee("Вася","Пупкин", "Антонович", organisationRepository.findByOrganisationName("ООО СКС").getId()));
		//emps.add(new Employee("Илья","Лисецкий", "Ильич", organisationRepository.findByOrganisationName("ООО СКС").getId()));
		//emps.add(new Employee("Ваня","Хуюпкин", "Александрович", organisationRepository.findByOrganisationName("ООО СКС").getId()));
		departmentRepository.deleteAll();
		employeeRepository.deleteAll();
		departmentRepository.save(new Department("ООО СКС", null));
		employeeRepository.save(new Employee("Вася","Пупкин", "Антонович", departmentRepository.findByDepartmentName("ООО СКС").getId()));
		employeeRepository.save(new Employee("Илья","Лисецкий", "Ильич", departmentRepository.findByDepartmentName("ООО СКС").getId()));
		employeeRepository.save(new Employee("Ваня","Хуюпкин", "Александрович", departmentRepository.findByDepartmentName("ООО СКС").getId()));
		ArrayList<Employee> emps = new ArrayList<>();
		emps.addAll(employeeRepository.findAll());
		int j = 0;
		for (Employee i :
				emps
			 ) {
			vacationRepository.save(new Vacation(i.getId(), new Date(2019,0,7+j), 15));
			j+=15;
		}
		for (Department i:
				departmentRepository.findAll()
			 ) {
			System.out.println(i.toString() + "\n");
		}

		for (Employee i:
				emps
		) {
			System.out.println(i.toString() + "\n    " + vacationRepository.findByEmployeeId(i.getId()).toString());
		}

	}

	@Autowired
	TokenAuthenticationManager tokenAuthenticationManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.headers().frameOptions().sameOrigin()
				.and()
				.addFilterAfter(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/*").authenticated();

	}

	@Bean(name = "tokenAuthenticationFilter")
	public TokenAuthenticationFilter restTokenAuthenticationFilter() {
		TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter();
		tokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager);
		return tokenAuthenticationFilter;
	}
}

