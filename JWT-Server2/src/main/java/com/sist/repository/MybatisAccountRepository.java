package com.sist.repository;

import org.springframework.stereotype.Repository;


import com.sist.dto.UserDTO;
import com.sist.mapper.AccountMapper;

import lombok.RequiredArgsConstructor;



@Repository
@RequiredArgsConstructor
public class MybatisAccountRepository implements AccountRepository {

	private final AccountMapper accountMapper;
	
	@Override
	public UserDTO findByUserName(String username) {
		// TODO Auto-generated method stub
		return accountMapper.findByUserName(username);
	}

	@Override
	public void save(UserDTO dto) {
		// TODO Auto-generated method stub
		accountMapper.save(dto);
	}

	@Override
	public UserDTO findFirst() {
		// TODO Auto-generated method stub
		return accountMapper.findFirst();
	}

	
	
}
