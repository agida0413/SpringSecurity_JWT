package com.sist.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sist.dto.JoinDTO;
import com.sist.entity.UserEntity;

@Mapper
public interface userMapper {
	public UserEntity findByUserName(String username);
	public void save(UserEntity entity);
	public UserEntity findFirst();
}
