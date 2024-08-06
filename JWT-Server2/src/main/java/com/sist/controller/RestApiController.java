package com.sist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.auth.AccountService;
import com.sist.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestApiController {
	
private final AccountService accountService;
	@GetMapping("/home")
	public String home() {
		
		return "<h1>home</h1>";
	}
	
	@PostMapping("/token")
	public String token() {
		System.out.println("접근함");
		return "<h1>token</h1>";
	}
	
	@PostMapping("/join")
	public String join(UserDTO dto) {
		
		return accountService.save(dto);
	}
}
