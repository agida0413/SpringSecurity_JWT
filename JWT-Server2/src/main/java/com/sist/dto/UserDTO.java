package com.sist.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import lombok.Data;

@Data
public class UserDTO {
private long id;
private String username;
private String password;
private String role;


public List<String> getRoleList(){
	if (this.role.length() > 0) {
		return Arrays.asList((this.role.split(","))) ;
	}
	else {
		return new ArrayList<>();
	}
}
}
