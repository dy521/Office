package com.office.controller.emp;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.entity.Obj;
import com.office.mapper.ObjMapper;


@Controller
@RequestMapping("/emp")
public class EmpObjController {

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
		return "emp/obj/list";
	} 
}