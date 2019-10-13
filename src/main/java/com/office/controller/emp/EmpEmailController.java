package com.office.controller.emp;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.office.entity.Email;
import com.office.entity.Emp;
import com.office.mapper.EmailMapper;
import com.office.service.impl.SendMailService;


@Controller
@RequestMapping("/emp")
public class EmpEmailController {

	@Autowired
	private EmailMapper emailMapper;
	

	@Autowired
	private SendMailService sendMailService;
	
	
	
	
	@RequestMapping(value="/gotoAddEmail")
	public String gotoAddOffice(){
		return "emp/email/add";
	}
	

	@RequestMapping(value="/addEmail")
	public String addEmail(String name,HttpServletRequest request) {
		
		
		List<Email> emails = emailMapper.findList();
		Email email = emails.get(0);
		String value = email.getValue();
		
		sendMailService.sendEmail(value, name);
		
		
		request.setAttribute("info", "邮件发送成功！！！");
		return "emp/email/info";
	}
	
	
	
	
	
	
    
}