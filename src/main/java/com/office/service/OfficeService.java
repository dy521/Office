package com.office.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Admin;
import com.office.entity.Office;

public interface OfficeService {
	
    List<Office> findList(Office office);
	
	Integer delete(Integer id);
	
	Office findById(Integer id);
	
	Integer insert(Office office);
	
	Integer update(Office office);

}
