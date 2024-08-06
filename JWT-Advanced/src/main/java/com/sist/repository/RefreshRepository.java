package com.sist.repository;

import org.springframework.stereotype.Repository;

import com.sist.entity.RefreshEntity;
import com.sist.mapper.RefreshMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RefreshRepository {

	private final RefreshMapper mapper;
	
public int	findRefresh(String refresh) {
	return  mapper.findRefresh(refresh);
}
	
public	void deleteRefresh(String refresh) {
		mapper.deleteRefresh(refresh);
	}

public void save(RefreshEntity entity) {
	mapper.save(entity);
}
	
	
}
