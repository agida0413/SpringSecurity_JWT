package com.sist.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sist.entity.UserEntity;

@Mapper
public interface userMapper {
	public UserEntity findByUserName(String username);
	public void save(UserEntity dto);
	public UserEntity findFirst();
}
