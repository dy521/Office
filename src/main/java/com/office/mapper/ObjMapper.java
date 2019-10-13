package com.office.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Obj;



public interface ObjMapper {
	

	List<Obj> findList(Obj obj);
	
	Integer delete(@Param("id") Integer id);
	
	Obj findById(@Param("id") Integer id);
	
	Integer insert(Obj obj);
	
	Integer update(Obj obj);


}
