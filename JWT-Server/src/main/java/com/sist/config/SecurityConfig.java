package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
private final CorsFilter corsFilter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.csrf(csrf->
			csrf.disable());
			
			
			http.sessionManagement(sm ->
			sm
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			
			http.addFilter(corsFilter); //@CrossOrigin(인증x) , filter(인증이 있는 요청)	
			
			http.formLogin(formlogin->
			formlogin
			.disable());
			
			http.httpBasic(httpbasic->
			httpbasic
			.disable());
			
			http.authorizeHttpRequests(authre->
			authre
			.requestMatchers("/api/v1/user/**").hasAnyRole("USER","ADMIN","MANAGER")
			.requestMatchers("/api/v1/manager/**").hasAnyRole("USER","MANAGER")
			.requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll());
			
			
			
			
		return http.build();

	}
}
