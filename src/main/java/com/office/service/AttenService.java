package com.office.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Admin;
import com.office.entity.Atten;

public interface AttenService {
	
	Atten findByUpTime(String up_time,Integer empid);
	
    List<Atten> findList(Atten atten);
	
	Integer delete(Integer id);
	
	Atten findById(Integer id);
	
	Integer insert(Atten atten);
	
	Integer update(Atten atten);

}
