package com.office.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Office;



public interface OfficeMapper {
	

	List<Office> findList(Office office);
	
	Integer delete(@Param("id") Integer id);
	
	Office findById(@Param("id") Integer id);
	
	Integer insert(Office office);
	
	Integer update(Office office);


}
