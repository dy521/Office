package com.office.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Admin;
import com.office.entity.Dept;

public interface DeptService {
	
    List<Dept> findList(Dept dept);
	
	Integer delete(Integer id);
	
	Dept findById(Integer id);
	
	Integer insert(Dept dept);
	
	Integer update(Dept dept);

}
