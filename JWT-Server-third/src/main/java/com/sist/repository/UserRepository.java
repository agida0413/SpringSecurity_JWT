package com.sist.repository;




import com.sist.dto.JoinDTO;
import com.sist.entity.UserEntity;

public interface UserRepository {
	public UserEntity findByUserName(String username);
	public void save(UserEntity entity);
	public UserEntity findFirst();
	
}
