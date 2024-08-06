package com.sist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("com.sist.mapper")
public class JwtServerSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtServerSecondApplication.class, args);
	}

}
