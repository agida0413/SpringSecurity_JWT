package com.sist.entity;

import lombok.Data;

@Data
public class RefreshEntity {

	private Long id;
	private String username;
	private String refresh;
	private String expiration;
	
	
}
