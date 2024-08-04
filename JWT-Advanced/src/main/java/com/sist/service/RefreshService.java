package com.sist.service;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.sist.entity.RefreshEntity;
import com.sist.mapper.RefreshMapper;
import com.sist.repository.RefreshRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshService {
private final RefreshRepository repository;
	
public Boolean	isExist(String refresh) {
	int count=repository.findRefresh(refresh);
	boolean result=false;
	if(count>0) {
		result=true;
	}
	return result;
}

public	void deleteRefresh(String refresh) {
		
		repository.deleteRefresh(refresh);
	}
	
	
	public void addRefreshEntity(String username, String refresh, Long expiredMs) {

	    Date date = new Date(System.currentTimeMillis() + expiredMs);

	    RefreshEntity refreshEntity = new RefreshEntity();
	    refreshEntity.setUsername(username);
	    refreshEntity.setRefresh(refresh);
	    refreshEntity.setExpiration(date.toString());

	    repository.save(refreshEntity);
	}
}
