package com.office.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Admin;
import com.office.entity.Dept;
import com.office.entity.Emp;

public interface EmpService {
	
	 //根据username和password查询用户
	Emp findAdminByNameAndPwd(String username,String password, String type);
	
	List<Emp> findList(Emp emp);
	
	Integer delete(Integer id);
	
	Emp findById(Integer id);
	
	Integer insert(Emp emp);
	
	Integer update(Emp emp);

}
