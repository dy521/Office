package com.office.service;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Admin;

public interface AdminService {
	
	 //根据username和password查询用户
	public Admin findAdminByNameAndPwd(String username,String password);
	public Admin findAdminByName(String username);

}
