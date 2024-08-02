package com.sist.filter;

import java.io.IOException;
import java.io.PrintWriter;

import com.auth0.jwt.interfaces.Header;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter1 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		//토큰: cors 이걸 만들어줘야함 . id,pw정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 응답
		//요청할때마다 header에 Authorization에 value값으로 토큰을 가지고옴
		//그때 토큰이 넘어오면 이 토큰이 내가만든 토큰이 맞는지만 검증(RSA, HS256)
		if (req.getMethod().equals("POST")) {
			System.out.println("POST요청됌");
			String headerAuth = req.getHeader("Authorization");
			System.out.println(headerAuth);
			
			if (headerAuth.equals("cors")) {
				chain.doFilter(request, response);
				//인증후 통과 
		}
		else {
				PrintWriter out=res.getWriter();
				out.println("인증안됌");
			}
		
		
		
		
	}

}
	
}
