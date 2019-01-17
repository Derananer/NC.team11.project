package com.company.authorisationservice;

import com.company.authorisationservice.security.TokenAuthenticationFilter;
import com.company.authorisationservice.security.TokenAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AuthorisationServiceApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(AuthorisationServiceApplication.class, args);
	}

    @Autowired
    TokenAuthenticationManager tokenAuthenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .addFilterAfter(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests();
                //.antMatchers("/rest/*").authenticated();
    }

    @Bean(name = "tokenAuthenticationFilter")
    public TokenAuthenticationFilter restTokenAuthenticationFilter() {
        TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter();
        tokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager);
        return tokenAuthenticationFilter;
    }
}

