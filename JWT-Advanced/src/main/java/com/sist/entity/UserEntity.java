package com.sist.entity;

import lombok.Data;

@Data
public class UserEntity {

	private int id;
	private String username;
	private String password;
	private String role;
}
