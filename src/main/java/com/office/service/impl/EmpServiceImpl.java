package com.office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.office.entity.Emp;
import com.office.mapper.EmpMapper;
import com.office.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired
	private EmpMapper empMapper;

	public List<Emp> findList(Emp emp) {
		// TODO Auto-generated method stub
		return empMapper.findList(emp);
	}
	

	@Transactional
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return empMapper.delete(id);
	}

	public Emp findById(Integer id) {
		// TODO Auto-generated method stub
		return empMapper.findById(id);
	}

	@Transactional
	public Integer insert(Emp emp) {
		// TODO Auto-generated method stub
		return empMapper.insert(emp);
	}

	@Transactional
	public Integer update(Emp emp) {
		// TODO Auto-generated method stub
		return empMapper.update(emp);
	}


	public Emp findAdminByNameAndPwd(String username, String password, String type) {
		// TODO Auto-generated method stub
		return empMapper.findAdminByNameAndPwd(username, password,type);
	}

	
	
	
	

}
