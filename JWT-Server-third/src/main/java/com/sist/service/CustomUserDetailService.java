package com.sist.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sist.entity.UserEntity;
import com.sist.repository.MybatisUserRepository;
import com.sist.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{
private final MybatisUserRepository mybatisUserRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(username);
		UserEntity userData=mybatisUserRepository.findByUserName(username);
		
		if(userData!=null) {

			
			return new CustomUserDetails(userData);
		}

		return null;
	}

}
