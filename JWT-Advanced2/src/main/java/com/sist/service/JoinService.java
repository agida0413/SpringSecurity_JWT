package com.sist.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.dto.JoinDTO;
import com.sist.entity.UserEntity;
import com.sist.repository.MybatisUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {

	
private final MybatisUserRepository mybatisUserRepository;
private final BCryptPasswordEncoder bCryptPasswordEncoder;

public String joinProcess(JoinDTO dto) {
	String username=dto.getUsername();
	String password=dto.getPassword();
	String result="";
	UserEntity ett = new UserEntity();
	ett= mybatisUserRepository.findByUserName(username);
	
	if(ett==null) {
		UserEntity entity= new UserEntity();
		entity.setUsername(username);
		entity.setPassword(bCryptPasswordEncoder.encode(password));
		entity.setRole("ROLE_ADMIN");
	
		mybatisUserRepository.save(entity);
		result="OK";
	}else {
		result="NO";
	}
	
	return result;
}
}
