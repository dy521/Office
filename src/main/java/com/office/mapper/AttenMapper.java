package com.office.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Atten;



public interface AttenMapper {
	

	List<Atten> findList(Atten atten);
	
	Integer delete(@Param("id") Integer id);
	
	Atten findById(@Param("id") Integer id);
	
	Atten findByUpTime(@Param("up_time") String up_time,@Param("empid") Integer empid);
	
	Integer insert(Atten atten);
	
	Integer update(Atten atten);


}
