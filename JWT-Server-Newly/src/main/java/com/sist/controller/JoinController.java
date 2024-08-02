package com.sist.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.entity.UserEntity;
import com.sist.service.userService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JoinController {

	private final userService userService;
	
	@PostMapping("/join")
	public String joinPorocess(UserEntity entity) {
		System.out.println(entity.getUsername());
		
		return userService.save(entity);
	}
}
