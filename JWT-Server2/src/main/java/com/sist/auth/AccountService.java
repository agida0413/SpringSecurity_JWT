package com.sist.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.dto.UserDTO;
import com.sist.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

//http://localhost:8080/login
@Service

@RequiredArgsConstructor
public class AccountService implements UserDetailsService{

	private final PasswordEncoder passwordEncoder;
	private final AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("로드바이ㄴ");
		UserDTO account=accountRepository.findByUserName(username);

		if(account==null) {
					throw new UsernameNotFoundException(username);
		}
	
		
		
		// TODO Auto-generated method stub
		return new PrincipalDetails(account);
		
	}
	
	public String save(UserDTO account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setRole("USER");
		accountRepository.save(account);
	return "회원가입완료";
	}
	
	
	
	

	
	
}
