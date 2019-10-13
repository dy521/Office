package com.office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.office.entity.Office;
import com.office.mapper.OfficeMapper;
import com.office.service.OfficeService;

@Service
public class OfficeServiceImpl implements OfficeService{

	@Autowired
	private OfficeMapper officeMapper;

	public List<Office> findList(Office office) {
		// TODO Auto-generated method stub
		return officeMapper.findList(office);
	}
	

	@Transactional
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return officeMapper.delete(id);
	}

	public Office findById(Integer id) {
		// TODO Auto-generated method stub
		return officeMapper.findById(id);
	}

	@Transactional
	public Integer insert(Office office) {
		// TODO Auto-generated method stub
		return officeMapper.insert(office);
	}

	@Transactional
	public Integer update(Office office) {
		// TODO Auto-generated method stub
		return officeMapper.update(office);
	}

	
	
	
	

}
