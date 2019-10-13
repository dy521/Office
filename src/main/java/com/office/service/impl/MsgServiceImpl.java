package com.office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.office.entity.Msg;
import com.office.mapper.MsgMapper;
import com.office.service.MsgService;

@Service
public class MsgServiceImpl implements MsgService{

	@Autowired
	private MsgMapper msgMapper;

	public List<Msg> findList(Msg msg) {
		// TODO Auto-generated method stub
		return msgMapper.findList(msg);
	}
	

	@Transactional
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return msgMapper.delete(id);
	}

	public Msg findById(Integer id) {
		// TODO Auto-generated method stub
		return msgMapper.findById(id);
	}

	@Transactional
	public Integer insert(Msg msg) {
		// TODO Auto-generated method stub
		return msgMapper.insert(msg);
	}

	@Transactional
	public Integer update(Msg msg) {
		// TODO Auto-generated method stub
		return msgMapper.update(msg);
	}

	
	
	
	

}
