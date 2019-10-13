package com.office.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Msg;



public interface MsgMapper {
	

	List<Msg> findList(Msg msg);
	
	Integer delete(@Param("id") Integer id);
	
	Msg findById(@Param("id") Integer id);
	
	Integer insert(Msg msg);
	
	Integer update(Msg msg);


}
