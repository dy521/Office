package com.office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.entity.InfoChart;
import com.office.mapper.InfoChartMapper;
import com.office.service.InfoChartService;

@Service
public class InfoChartServiceImpl implements InfoChartService{

	@Autowired
	private InfoChartMapper infoChartMapper;

	public List<InfoChart> findList() {
		// TODO Auto-generated method stub
		List<InfoChart> list = infoChartMapper.findList();
		for (InfoChart infoChart : list) {
			if(infoChart.getType().equals("员工")) {
				infoChart.setValue(infoChart.getValue()*100);
			}else {
				infoChart.setValue(infoChart.getValue()*200);
			}
		}
		return list;
	}
}
