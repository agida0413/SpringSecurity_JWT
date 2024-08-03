package com.sist.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MainController {
@GetMapping("/")
public String main() {
	System.out.println(	SecurityContextHolder.getContext().getAuthentication().getName());
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	Iterator<? extends GrantedAuthority> iter = authorities.iterator();
	GrantedAuthority auth = iter.next();
	String role = auth.getAuthority();

	 System.out.println(role);
    return "main";
}

}
