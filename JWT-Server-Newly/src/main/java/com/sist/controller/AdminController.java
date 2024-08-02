package com.sist.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AdminController {

	@GetMapping("/admin")
	public String adminP() {
		return "admin Controller";
	}
	
}
