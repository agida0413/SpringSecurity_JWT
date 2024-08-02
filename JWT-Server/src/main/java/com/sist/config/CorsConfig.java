package com.sist.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Bean
	public CorsFilter corsFilter(){
	UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
	CorsConfiguration config= new CorsConfiguration();
	config.setAllowCredentials(true);//내  서버가 응답할때 json을 자바스크립트에서 처리할 수있게 할지를 설정하는것
	config.addAllowedOrigin("*");//모든 아이피 응답을 허용
	config.addAllowedHeader("*");//모든 header의 응답을 허용하겠다.
	config.addAllowedMethod("*");//모든 post,get,delete,patch 요청을 허용 하겠다.
	source.registerCorsConfiguration("/api/**", config);
	return new CorsFilter(source);
}
}
