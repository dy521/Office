package com.office.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.entity.Admin;
import com.office.mapper.AdminMapper;
import com.office.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;

	
	
	public Admin findAdminByNameAndPwd(String username, String password) {
		// TODO Auto-generated method stub
		return adminMapper.findAdminByNameAndPwd(username, password);
	}



	public Admin findAdminByName(String username) {
		// TODO Auto-generated method stub
		return adminMapper.findAdminByName(username);
	}

}
