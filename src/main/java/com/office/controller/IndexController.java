package com.office.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.office.entity.InfoChart;
import com.office.service.InfoChartService;



@Controller
public class IndexController {

	@Autowired
	private InfoChartService infoChartService;
	
	

	
	
	@RequestMapping(value="/")
	public String gotoAdminLogin(){
		return "main/login";
	}
	
	///gotoFindPwd
	@RequestMapping(value="/gotoFindPwd")
	public String gotoFindPwd(){
		return "main/find_pwd";
	}
	
	


	
	@RequestMapping(value="/main/emp_main")
	public String emp_main(){
		return "main/emp_main";
	}

	@RequestMapping(value="/main/index")
	public String index(){
		return "main/index";
	}
	
	///gotoChart
	@RequestMapping(value="/gotoChart")
	public String gotoChart(){
		return "admin/chart/list";
	}
	
	//json
	@RequestMapping(value="/getChart")
	@ResponseBody
	public List<InfoChart> getChart(){
		return infoChartService.findList();
	}
	
}
