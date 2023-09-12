package com.freework.taskmaster.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.freework.taskmaster.exceptions.CustomAccessDeniedHandler;
import com.freework.taskmaster.filter.JwtRequestFilter;
import com.freework.taskmaster.serviceImpl.UserDetailService;


@EnableWebSecurity
@Configuration
public class SecurityConfigurer{
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) 
	  throws Exception {
		return new JwtAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//Allows postman to send requests
		http.csrf().disable();
		
		http
		.authorizeHttpRequests(authorize -> authorize                                  
			.requestMatchers("/auth/**").permitAll() 
			.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
			.anyRequest().authenticated()
		);
		http.sessionManagement(c->{
			 c.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		});
		http.exceptionHandling(c -> c.accessDeniedHandler(customAccessDeniedHandler()));
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
    CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
	




}


