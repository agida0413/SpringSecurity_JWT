package com.sist.jwt;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
// UsernamePasswordAuthenticationFilter  /login 요청해서 username,password를 전송하면 (post)로 요청하면 동작

private final AuthenticationManager authenticationManager;
	//1. username,password 받아서 
	
	//2. 정상인지 로그인 시도 authenticationmanager로 로그인 시도를 하면  principaldetailservice호출
	//principaldetailservice가 호출 loadbyusername함수 실행됌
	//3.principaldetails를 세션에 담고 - > 권한관리
	//jwt토큰을 만들어서 
@Override
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException {
	System.out.println("로그인 시도중");
	
	
	//1.username,password 받아서
	try {
//		BufferedReader reader=request.getReader();
//		
//		String input=null;
//		while((input=reader.readLine())!=null) {
//			System.out.println(input);
//		}
//		
//		System.out.println(request.getInputStream().toString());
	ObjectMapper om=new ObjectMapper();
	UserDTO userDTO=om.readValue(request.getInputStream(), UserDTO.class);
	UsernamePasswordAuthenticationToken authenticationToken=
			new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
	
	Authentication authentication=
			authenticationManager.authenticate(authenticationToken);
			
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// TODO Auto-generated method stub

	return super.attemptAuthentication(request, response);
}


}
