package com.sist.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sist.dto.UserDTO;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class PrincipalDetails  implements UserDetails{

	private UserDTO userDTO;

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> authorities= new ArrayList<>();
		userDTO.getRoleList().forEach(r->{
			authorities.add(()->r);
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userDTO.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userDTO.getUsername();
	}

	public PrincipalDetails(UserDTO userDTO) {
		super();
		this.userDTO = userDTO;
	}
}
