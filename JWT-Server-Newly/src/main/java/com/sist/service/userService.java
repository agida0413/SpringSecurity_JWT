package com.sist.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.entity.UserEntity;
import com.sist.repository.MybatisUserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class userService implements UserDetailsService{

	
	private final MybatisUserRepository mybatisUserRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String save(UserEntity entity) {
		String username=entity.getUsername();
		UserEntity findUser=mybatisUserRepository.findByUserName(username);
		if(findUser==null) {
			entity.setRole("USER");
			entity.setPassword(passwordEncoder.encode(entity.getPassword()));
			mybatisUserRepository.save(entity);
		}
		
		
	
	
		return "ok";
	}

}
