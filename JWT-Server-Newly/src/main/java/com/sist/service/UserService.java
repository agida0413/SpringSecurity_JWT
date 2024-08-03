package com.sist.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.dto.JoinDTO;
import com.sist.entity.UserEntity;
import com.sist.jwt.CustomUserDetails;
import com.sist.repository.MybatisUserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

	
	private final MybatisUserRepository mybatisUserRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
UserEntity account=mybatisUserRepository.findByUserName(username);
		
		if(account==null) {
					throw new UsernameNotFoundException(username);
		}
		
		
		// TODO Auto-generated method stub
		return new CustomUserDetails(account);
	}
	public String save(JoinDTO entity) {
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
