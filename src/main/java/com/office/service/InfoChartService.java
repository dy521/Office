package com.office.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.office.entity.Admin;
import com.office.entity.InfoChart;

public interface InfoChartService {
	
	List<InfoChart> findList();

}
