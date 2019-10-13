package com.office.controller.admin;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.entity.Dept;
import com.office.entity.Emp;
import com.office.mapper.DeptMapper;
import com.office.mapper.EmpMapper;


@Controller
@RequestMapping("/admin")
public class AdminEmpController {

	@Autowired
	private EmpMapper empMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	

	@RequestMapping("/emp")
	public String emp(Integer pageNum,
			String name,
			Model model){
		if(pageNum==null) {
			pageNum=1;
		}
		Emp emp = new Emp();
		emp.setName(name);
		//分页并查询
		PageHelper.startPage(pageNum,10);
		List<Emp> emps = empMapper.findList(emp);
		System.out.println(emps);
		PageInfo pageInfo = new PageInfo<Emp>(emps, 10);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "admin/emp/list";
	}
	
	
	@RequestMapping(value="/gotoAddEmp")
	public String gotoAddEmp(Model model){
		List<Dept> list = deptMapper.findList(null);
		model.addAttribute("depts", list);
		return "admin/emp/add";
	}
	
	/*
	 * 上传
	 */
	@RequestMapping(value="/addEmp", method = {RequestMethod.POST})
	public ModelAndView addEmp(Emp emp) {
		System.out.println("emp:"+emp);  
		empMapper.insert(emp);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/emp");
		return modelAndView;
	}
	
	
	
	/**
	 */
	@RequestMapping(value="/empDel")
	public ModelAndView deleteEmp(Integer id){
		empMapper.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/emp");
		return modelAndView;
	}
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(value="/gotoEditEmp")
	public String gotoEditHotelType(Integer id,Map<String, Object> map){
		List<Dept> list = deptMapper.findList(null);
		map.put("depts", list);
		Emp emp =empMapper.findById(id);
		map.put("pa", emp);
		return "admin/emp/edit";
	}
	
	@RequestMapping(value="/editEmp", method = {RequestMethod.POST})
	public ModelAndView editEmp(Emp emp) {
		System.out.println("emp:"+emp); 
		empMapper.update(emp);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/emp");
		return modelAndView;
	}

	
	
	
    
}