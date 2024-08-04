package com.sist.repository;

import org.springframework.security.web.csrf.CsrfFilter;


import com.sist.dto.UserDTO;

public interface AccountRepository {
	public UserDTO findByUserName(String username);
	public void save(UserDTO dto);
	public UserDTO findFirst();
	
}
