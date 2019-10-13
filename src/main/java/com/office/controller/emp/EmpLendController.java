package com.office.controller.emp;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.entity.Emp;
import com.office.entity.Lend;
import com.office.entity.Obj;
import com.office.mapper.LendMapper;
import com.office.mapper.ObjMapper;


@Controller
@RequestMapping("/emp")
public class EmpLendController {

	@Autowired
	private LendMapper lendMapper;
	

	@Autowired
	private ObjMapper objMapper;
	
	@RequestMapping("/lend")
	public String lend(Integer pageNum,
			String name,
			Model model,HttpSession session){
		Emp emp = 	(Emp) session.getAttribute("emp");
		
		if(pageNum==null) {
			pageNum=1;
		}
		Lend lend = new Lend();
		lend.setEmpid(emp.getId());
		//分页并查询
		PageHelper.startPage(pageNum,5);
		List<Lend> lends = lendMapper.findList(lend);
		System.out.println(lends);
		PageInfo pageInfo = new PageInfo<Lend>(lends, 5);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "emp/lend/list";
	}
	
	
	
	
	@RequestMapping(value="/gotoLendObj")
	public String gotoLendObj(Integer id,HttpServletRequest request,HttpSession session){
		Emp emp = 	(Emp) session.getAttribute("emp");
		
		Obj obj = objMapper.findById(id);
		String status = obj.getStatus();
		
		if(status.equals("已借出")) {
			request.setAttribute("info", "此物品已借出！！！");
			return "emp/lend/info";
		}
		//未借出
		//1.借
		Lend lend = new Lend();
		lend.setEmpid(emp.getId());
		lend.setObjid(id);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08:355
		lend.setTime(formatStr);
		lendMapper.insert(lend);
		
		
		
		//2.修改物品状态
		obj.setStatus("已借出");
		objMapper.update(obj);	
		request.setAttribute("info", "借物品成功！！！");
		return "emp/lend/info";
	}
	
	
	//@{/emp/gotoReturnObj(id=${pa.id})}
	
	@RequestMapping(value="/gotoReturnObj")
	public String gotoReturnObj(Integer id,HttpServletRequest request,HttpSession session){
		Emp emp = 	(Emp) session.getAttribute("emp");
		
		
		
		Lend lend = lendMapper.findById(id);
		Integer objId = lend.getObjid();
		Obj obj = objMapper.findById(objId);
		obj.setStatus("未借出");
		objMapper.update(obj);
		
		
		Integer result = lendMapper.delete(id);
		
		if(result>0) {
			request.setAttribute("info", "归还成功！！！");
			return "emp/lend/return_info";
		}
		request.setAttribute("info", "归还失败！！！");
		return "emp/lend/return_info";
	}    
}