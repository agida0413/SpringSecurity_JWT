package com.sist.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	
	@GetMapping("/api/test")
	public String test() {
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
