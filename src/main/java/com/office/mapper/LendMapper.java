package com.office.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Lend;



public interface LendMapper {
	

	List<Lend> findList(Lend lend);
	
	Integer delete(@Param("id") Integer id);
	
	Lend findById(@Param("id") Integer id);
	
	Integer insert(Lend lend);
	
	Integer update(Lend lend);


}
