package com.sist.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sist.entity.RefreshEntity;

@Mapper
public interface RefreshMapper {

public int	findRefresh(String refresh);
	
public	void deleteRefresh(String refresh);
	
public	void save(RefreshEntity entity);
}
