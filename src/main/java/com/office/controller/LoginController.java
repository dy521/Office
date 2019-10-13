package com.office.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.office.entity.Admin;
import com.office.entity.Emp;
import com.office.mapper.EmpMapper;
import com.office.service.AdminService;
import com.office.service.impl.InfoServiceImpl;



@Controller
public class LoginController {
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private EmpMapper empMapper;
	
	@Autowired
	private InfoServiceImpl infoServiceImpl;
	
	
	@RequestMapping(value="/login", method = {RequestMethod.POST})
	@ResponseBody
	public String login(Admin admin,String type,HttpSession session) {
		if(type.equals("admin")) {
			String username= admin.getUsername();
			String password = admin.getPassword();
			Admin resultUser = adminService.findAdminByNameAndPwd(username, password);
			if(resultUser!=null) {
				session.setAttribute("admin", resultUser);
				System.out.println("登录成功");
				return "success";
			}
		}else if(type.equals("emp")){
			String username= admin.getUsername();
			String password = admin.getPassword();
			Emp resultUser = empMapper.findAdminByNameAndPwd(username, password,"员工");
			if(resultUser!=null) {
				session.setAttribute("emp", resultUser);
				System.out.println("登录成功");
				return "success";
			}
		}else {
			String username= admin.getUsername();
			String password = admin.getPassword();
			Emp resultUser = empMapper.findAdminByNameAndPwd(username, password,"经理");
			if(resultUser!=null) {
				session.setAttribute("mag", resultUser);
				System.out.println("登录成功");
				return "success";
			}
		}

		return "fail";
	}
	
	 @RequestMapping(value="/resetPwd", method = {RequestMethod.POST})
	   	@ResponseBody
	   	public String resetPwd(String username,String password) {
	       	 Integer result  = infoServiceImpl.resetPwd(username, password);
	         if(result>0) {
	        	 return "success";
	         }else {
	        	 return "fail";
	         }
	   	}
	
	
	 @RequestMapping("/gotoFindPwdEnd")
	    public String gotoFindPwdEnd(String username,Model model) {
	    	model.addAttribute("username", username);
	        return "main/find_pwd_end";
	    }

	   @RequestMapping(value="/findPwd", method = {RequestMethod.POST})
	   	@ResponseBody
	   	public String findPwd(String username,String code) {
	       	 boolean result  = infoServiceImpl.findPwd(username, code);
	         if(result) {
	        	 return username;
	         }else {
	        	 return "fail";
	         }
	   	}
	
	
	@RequestMapping(value="/getCode", method = {RequestMethod.POST})
	@ResponseBody
	public String getCode(String username) {
	   Admin user1 = adminService.findAdminByName(username);
    if (user1 == null) {
        return "fail";
    }
    
     boolean result = infoServiceImpl.sendMsg(username);
		if(result) {
			return "success";
		}
		return "send_fail";
	}
	
	/**
	 * 退出登录
	 */
	@RequestMapping(value="loginOut")
	public String userOut(HttpSession session){
		//设置session
		session.invalidate();
		return "main/login";
	}
	
	
	
}
