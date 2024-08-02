package com.sist.repository;

import org.springframework.stereotype.Repository;



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
	public void save(UserEntity dto) {
		// TODO Auto-generated method stub
		accountMapper.save(dto);
	}

	@Override
	public UserEntity findFirst() {
		// TODO Auto-generated method stub
		return accountMapper.findFirst();
	}

	
	

	
	
}
