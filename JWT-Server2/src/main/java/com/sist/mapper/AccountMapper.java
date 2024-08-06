package com.sist.mapper;

import org.apache.ibatis.annotations.Mapper;


import com.sist.dto.UserDTO;

@Mapper
public interface AccountMapper {
	public UserDTO findByUserName(String username);
	public void save(UserDTO dto);
	public UserDTO findFirst();
}
