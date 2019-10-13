package com.office.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Admin;
import com.office.entity.Msg;

public interface MsgService {
	
    List<Msg> findList(Msg msg);
	
	Integer delete(Integer id);
	
	Msg findById(Integer id);
	
	Integer insert(Msg msg);
	
	Integer update(Msg msg);

}
