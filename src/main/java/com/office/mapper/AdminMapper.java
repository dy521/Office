package com.office.mapper;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Admin;
import com.office.entity.Atten;

public interface AdminMapper {

	 //根据username和password查询用户
	public Admin findAdminByNameAndPwd(@Param("username") String username,@Param("password") String password);

	public Admin findAdminByName(@Param("username") String username);

	Integer update(Admin admin);

}
