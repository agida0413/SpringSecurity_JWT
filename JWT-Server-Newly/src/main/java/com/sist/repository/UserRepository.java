package com.sist.repository;




import com.sist.entity.UserEntity;

public interface UserRepository {
	public UserEntity findByUserName(String username);
	public void save(UserEntity dto);
	public UserEntity findFirst();
	
}
