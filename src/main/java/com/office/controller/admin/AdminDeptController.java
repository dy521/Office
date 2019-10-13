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
import com.office.mapper.DeptMapper;

@Controller
@RequestMapping("/admin")
public class AdminDeptController {

	@Autowired
	private DeptMapper deptMapper;
	


	@RequestMapping("/dept")
	public String dept(Integer pageNum,
			String name,
			Model model){
		if(pageNum==null) {
			pageNum=1;
		}
		Dept dept = new Dept();
		dept.setName(name);
		//分页并查询
		PageHelper.startPage(pageNum,5);
		List<Dept> depts = deptMapper.findList(dept);
		System.out.println(depts);
		PageInfo pageInfo = new PageInfo<Dept>(depts,5);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "admin/dept/list";
	}
	
	
	@RequestMapping(value="/gotoAddDept")
	public String gotoAddDept(Model model){
		return "admin/dept/add";
	}
	

	@RequestMapping(value="/addDept", method = {RequestMethod.POST})
	public ModelAndView addDept(Dept dept) {
		System.out.println("dept:"+dept);  
		deptMapper.insert(dept);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/dept");
		return modelAndView;
	}
	
	
	
	/**
	 */
	@RequestMapping(value="/deptDel")
	public ModelAndView deleteDept(Integer id){
		deptMapper.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/dept");
		return modelAndView;
	}
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(value="/gotoEditDept")
	public String gotoEditDeptType(Integer id,Map<String, Object> map){
		
		Dept dept =deptMapper.findById(id);
		map.put("pa", dept);
		return "admin/dept/edit";
	}
	
	@RequestMapping(value="/editDept", method = {RequestMethod.POST})
	public ModelAndView editDept(Dept dept) {
		System.out.println("dept:"+dept); 
		deptMapper.update(dept);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/dept");
		return modelAndView;
	}

}