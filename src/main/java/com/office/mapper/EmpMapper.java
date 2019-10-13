package com.office.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Admin;
import com.office.entity.Emp;



public interface EmpMapper {
	

	 //根据username和password查询用户
	Emp findAdminByNameAndPwd(@Param("username") String username,@Param("password") String password,@Param("type") String type);

	
	List<Emp> findList(Emp emp);
	
	Integer delete(@Param("id") Integer id);
	
	Emp findById(@Param("id") Integer id);
	
	Integer insert(Emp emp);
	
	Integer update(Emp emp);


}
