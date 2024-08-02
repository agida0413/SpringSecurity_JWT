package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

import com.sist.auth.AccountService;
import com.sist.filter.MyFilter1;
import com.sist.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private AccountService accountService;
private final CorsFilter corsFilter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http.addFilterBefore(new MyFilter1(),
		SecurityContextPersistenceFilter.class);
		//시큐리티 필터  우선순위가 커스텀보다 높다. 먼저 동작하게 하고
//		//싶으면 Before , jwt 인증은 시큐리티필터보다 먼저 등록
		
		
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
			http.addFilter(new JwtAuthenticationFilter( authenticationManager(http)));
			http.authorizeHttpRequests(authre->
			authre
			.requestMatchers("/api/v1/user/**").hasAnyRole("USER","ADMIN","MANAGER")
			.requestMatchers("/api/v1/manager/**").hasAnyRole("USER","MANAGER")
			.requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll());
			
			
			
			
		return http.build();

	}
	@Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(accountService)
                   .passwordEncoder(passwordEncoder()) // Ensure to define a password encoder bean
                   .and()
                   .build();
    }
	
	
	
	
	
	 
}
