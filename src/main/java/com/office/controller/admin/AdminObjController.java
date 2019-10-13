package com.office.controller.admin;



import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.office.entity.Obj;
import com.office.mapper.ObjMapper;


@Controller
@RequestMapping("/admin")
public class AdminObjController {

	@Autowired
	private ObjMapper objMapper;
	


	@RequestMapping("/obj")
	public String obj(Integer pageNum,
			String name,
			Model model){
		if(pageNum==null) {
			pageNum=1;
		}
		Obj obj = new Obj();
		obj.setName(name);
		//分页并查询
		PageHelper.startPage(pageNum,5);
		List<Obj> objs = objMapper.findList(obj);
		System.out.println(objs);
		PageInfo pageInfo = new PageInfo<Obj>(objs, 5);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "admin/obj/list";
	}
	
	
	@RequestMapping(value="/gotoAddObj")
	public String gotoAddObj(Model model){
		return "admin/obj/add";
	}
	
	/*
	 * 上传
	 */
	@RequestMapping(value="/addObj", method = {RequestMethod.POST})
	public ModelAndView addObj(Obj obj) {
		obj.setStatus("未借出");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08:355
		obj.setTime(formatStr);

		
		
		System.out.println("obj:"+obj);  
		objMapper.insert(obj);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/obj");
		return modelAndView;
	}
	
	
	
	/**
	 */
	@RequestMapping(value="/objDel")
	public ModelAndView deleteEmp(Integer id){
		objMapper.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/obj");
		return modelAndView;
	}
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(value="/gotoEditObj")
	public String gotoEditHotelType(Integer id,Map<String, Object> map){
		
		Obj obj =objMapper.findById(id);
		map.put("pa", obj);
		return "admin/obj/edit";
	}
	
	@RequestMapping(value="/editObj", method = {RequestMethod.POST})
	public ModelAndView editObj(Obj obj) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08:355
		obj.setTime(formatStr);
		obj.setStatus("未借出");
		System.out.println("obj:"+obj); 
		objMapper.update(obj);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/obj");
		return modelAndView;
	}

	
	
	
    
}