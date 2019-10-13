package com.office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.office.entity.Dept;
import com.office.mapper.DeptMapper;
import com.office.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService{

	@Autowired
	private DeptMapper deptMapper;

	public List<Dept> findList(Dept dept) {
		// TODO Auto-generated method stub
		return deptMapper.findList(dept);
	}
	

	@Transactional
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return deptMapper.delete(id);
	}

	public Dept findById(Integer id) {
		// TODO Auto-generated method stub
		return deptMapper.findById(id);
	}

	@Transactional
	public Integer insert(Dept dept) {
		// TODO Auto-generated method stub
		return deptMapper.insert(dept);
	}

	@Transactional
	public Integer update(Dept dept) {
		// TODO Auto-generated method stub
		return deptMapper.update(dept);
	}

	
	
	
	

}
