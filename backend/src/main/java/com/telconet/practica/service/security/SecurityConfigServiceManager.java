package com.telconet.practica.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.telconet.practica.controller.security.filter.JWTAuthenticationFilter;
import com.telconet.practica.controller.security.filter.JWTAuthorizationFilter;
import com.telconet.practica.service.auth.UserAuthDetailsService;

@Service
public class SecurityConfigServiceManager implements SecurityConfigService {

    @Autowired
	private UserAuthDetailsService userDetailsService;
    
    @Override
    public SecurityConfigServiceManager loadSessionManagment(HttpSecurity http) throws Exception {
        http.cors().and()
			.csrf().disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		return this;
    }

    @Override
    public SecurityConfigServiceManager loadAcls(HttpSecurity http) throws Exception {
        http.authorizeRequests()
			//.antMatchers(HttpMethod.GET, "/").permitAll()
			.antMatchers("/auth/*").permitAll()
			.anyRequest().authenticated();
        return this;
    }

    @Override
    public SecurityConfigServiceManager loadFilters(HttpSecurity http, AuthenticationManager authManager,
            ApplicationContext appContext) throws Exception {
        
        http.addFilter( new JWTAuthenticationFilter(authManager, appContext) )
            .addFilter( new JWTAuthorizationFilter(authManager, appContext) );

        return this;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Override
    public SecurityConfigService loadAuthenticationService(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService)
            .passwordEncoder(passwordEncoder());
        return this;
    }
}
