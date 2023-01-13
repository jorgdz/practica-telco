package com.telconet.practica.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.telconet.practica.service.security.SecurityConfigService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private SecurityConfigService securityConfigService;

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		this.securityConfigService
			.loadSessionManagment(http)
			.loadAcls(http)
			.loadFilters(
				http, authenticationManager(), getApplicationContext());
	}

    @Override
	protected void configure(AuthenticationManagerBuilder auth) 
		throws Exception {
        this.securityConfigService.loadAuthenticationService(auth);
	}
}
