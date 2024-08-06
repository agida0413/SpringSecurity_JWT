package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import com.sist.filter.MyFilter1;

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
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//세션 사용x 
			
			http.addFilter(corsFilter); //@CrossOrigin(인증x) , filter(인증이 있는 요청)	
			
			http.formLogin(formlogin->
			formlogin
			.disable());
			
			http.httpBasic(httpbasic->
			httpbasic
			.disable()); //httpbasic = Id, pwd 를 헤더에 담아보냄 
			//여기서 사용할 방식 : Authorization : 토큰 -> ID PW
			//Bearer 방식 : 토큰방식 - 유효시간 
			
			http.authorizeHttpRequests(authre->
			authre
			.requestMatchers("/api/v1/user/**").hasAnyRole("USER","ADMIN","MANAGER")
			.requestMatchers("/api/v1/manager/**").hasAnyRole("USER","MANAGER")
			.requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll());
			
			
			
			
		return http.build();

	}
}
