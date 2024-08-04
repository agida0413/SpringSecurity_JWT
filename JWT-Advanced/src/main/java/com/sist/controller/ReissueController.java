package com.sist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jwt.JWTUtil;
import com.sist.service.RefreshService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReissueController {
	  private final JWTUtil jwtUtil;
	  private final RefreshService refreshService;

	    public ReissueController(JWTUtil jwtUtil,RefreshService refreshService) {

	        this.jwtUtil = jwtUtil;
	        this.refreshService=refreshService;
	    }

	    
	    //ResponseEntity<T>는 Spring Framework에서 제공하는 클래스로, HTTP 응답을 나타내는 객체입니다. 
	    @PostMapping("/api/reissue")
	    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

	        //get refresh token
	        String refresh = null;
	        Cookie[] cookies = request.getCookies();
	        for (Cookie cookie : cookies) {

	            if (cookie.getName().equals("refresh")) {

	                refresh = cookie.getValue();
	            }
	        }

	        if (refresh == null) {

	            //response status code
	            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
	        }

	        //expired check
	        try {
	            jwtUtil.isExpired(refresh);
	        } catch (ExpiredJwtException e) {

	            //response status code
	            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
	        }

	        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
	        String category = jwtUtil.getCategory(refresh);

	        if (!category.equals("refresh")) {

	            //response status code
	            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
	        }
	        
	        //DB에 저장되어 있는지 확인
			Boolean isExist = refreshService.isExist(refresh);
			if (!isExist) {
			
			    //response body
			    return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
			}
	        

	        String username = jwtUtil.getUsername(refresh);
	        String role = jwtUtil.getRole(refresh);

	      //make new JWT
	        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
	        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

	        
	      //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
			refreshService.deleteRefresh(refresh);
			refreshService.addRefreshEntity(username, newRefresh, 86400000L);
	        //response
	        response.setHeader("access", newAccess);
	        response.addCookie(createCookie("refresh", newRefresh));

	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	    
	    
	    private Cookie createCookie(String key, String value) {

	        Cookie cookie = new Cookie(key, value);
	        cookie.setMaxAge(24*60*60);
	        //cookie.setSecure(true); https
	        //cookie.setPath("/");
	        cookie.setHttpOnly(true); //xss 공격방지 

	        return cookie;
	    }
	
	
}
