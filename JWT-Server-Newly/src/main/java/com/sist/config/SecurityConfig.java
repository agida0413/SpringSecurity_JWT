package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sist.jwt.JWTUtil;
import com.sist.jwt.LoginFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationConfiguration authenticationConfiguration;
	private final JWTUtil jtwUtil;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder(); 
        
        
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		
		
		
			  http
	          .csrf((auth) -> auth.disable());

						//From 로그인 방식 disable
			  http
			          .formLogin((auth) -> auth.disable());
			
						//http basic 인증 방식 disable
			  http
			          .httpBasic((auth) -> auth.disable());
			
						//경로별 인가 작업
			  http
			          .authorizeHttpRequests((auth) -> auth
			                  .requestMatchers("/login", "/", "/join").permitAll()
														.requestMatchers("/admin").hasRole("ADMIN")
			                  .anyRequest().authenticated());
			
						//세션 설정
			  http
			          .sessionManagement((session) -> session
			                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			
			http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jtwUtil), UsernamePasswordAuthenticationFilter.class); 		
					
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
