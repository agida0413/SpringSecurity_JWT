package com.sist.repository;

import org.springframework.stereotype.Repository;

import com.sist.dto.JoinDTO;
import com.sist.entity.UserEntity;

import com.sist.mapper.userMapper;

import lombok.RequiredArgsConstructor;



@Repository
@RequiredArgsConstructor
public class MybatisUserRepository implements UserRepository {

	private final userMapper accountMapper;
	
	@Override
	public UserEntity findByUserName(String username) {
		// TODO Auto-generated method stub
		return accountMapper.findByUserName(username);
	}

	@Override
	public void save(UserEntity entity) {
		// TODO Auto-generated method stub
		accountMapper.save(entity);;
	}

	@Override
	public UserEntity findFirst() {
		// TODO Auto-generated method stub
		return accountMapper.findFirst();
	}

	
	

	
	
}
