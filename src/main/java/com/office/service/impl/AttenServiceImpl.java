package com.office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.office.entity.Atten;
import com.office.mapper.AttenMapper;
import com.office.service.AttenService;

@Service
public class AttenServiceImpl implements AttenService{

	@Autowired
	private AttenMapper attenMapper;

	public List<Atten> findList(Atten atten) {
		// TODO Auto-generated method stub
		return attenMapper.findList(atten);
	}
	

	@Transactional
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return attenMapper.delete(id);
	}

	public Atten findById(Integer id) {
		// TODO Auto-generated method stub
		return attenMapper.findById(id);
	}

	@Transactional
	public Integer insert(Atten atten) {
		// TODO Auto-generated method stub
		return attenMapper.insert(atten);
	}

	@Transactional
	public Integer update(Atten atten) {
		// TODO Auto-generated method stub
		return attenMapper.update(atten);
	}


	public Atten findByUpTime(String up_time, Integer empid) {
		// TODO Auto-generated method stub
		return attenMapper.findByUpTime(up_time, empid);
	}

	
	
	
	

}
