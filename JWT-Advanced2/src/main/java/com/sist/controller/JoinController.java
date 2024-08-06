package com.sist.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dto.JoinDTO;
import com.sist.service.JoinService;

@RestController

public class JoinController {
	 private final JoinService joinService;

	    public JoinController(JoinService joinService) {
	        
	        this.joinService = joinService;
	    }

	    @PostMapping("/api/join")
	    public String joinProcess(JoinDTO joinDTO) {

	      System.out.println("api 실행");

	        return joinService.joinProcess(joinDTO);
	    }
	    @GetMapping("/api/test-2")
	    public String test() {
	    System.out.println("dddd");
	    
	    	return "text";
	    }
}
