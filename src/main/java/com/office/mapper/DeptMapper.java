package com.office.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Dept;



public interface DeptMapper {
	

	List<Dept> findList(Dept dept);
	
	Integer delete(@Param("id") Integer id);
	
	Dept findById(@Param("id") Integer id);
	
	Integer insert(Dept dept);
	
	Integer update(Dept dept);


}
