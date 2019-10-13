package com.office.controller.admin;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.entity.Dept;
import com.office.entity.Emp;
import com.office.entity.Msg;
import com.office.entity.Office;
import com.office.mapper.EmpMapper;
import com.office.mapper.MsgMapper;
import com.office.mapper.OfficeMapper;
import com.office.utils.StringTools;


@Controller
@RequestMapping("/admin")
public class AdminOfficeController {

	@Autowired
	private OfficeMapper officeMapper;
	
	@Autowired
	private EmpMapper adminMapper;
	
	@Value("${file.path}")
	private String fileBasePath;
	
	@Autowired
	private MsgMapper msgMapper;
	

	@RequestMapping("/office")
	public String office(Integer pageNum,
			String name,
			Model model){
		if(pageNum==null) {
			pageNum=1;
		}
		Office office = new Office();
		office.setStatus("经理通过");
		//分页并查询
		PageHelper.startPage(pageNum,10);
		List<Office> depts = officeMapper.findList(office);
		System.out.println(depts);
		PageInfo pageInfo = new PageInfo<Office>(depts, 10);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "admin/office/list";
	}
	
	
	
	//@{/admin/officePass(id=${pa.id})}
	@RequestMapping(value="/officePass")
	public String officePass(Integer id,Model model,HttpServletRequest request,HttpSession session){
		try {
			Office office = officeMapper.findById(id);
			office.setStatus("管理员通过");
			officeMapper.update(office);
			//插入到公告表
			Msg msg = new Msg();
			msg.setName(office.getName());
			msg.setContent(office.getContent());
			msg.setCreate_time(office.getCreate_time());
			msg.setPath(office.getPath());
			msgMapper.insert(msg);
			
			request.setAttribute("info", "审核成功！！！");
			return "admin/office/info";
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("info", "审核失败！！！");
			return "admin/office/info";
		}				
	}
	
	
	//@{/admin/officeReject(id=${pa.id})}
	@RequestMapping(value="/officeReject")
	public String officeReject(Integer id,Model model,HttpServletRequest request,HttpSession session){
		try {
			Office office = officeMapper.findById(id);
			office.setStatus("管理员不通过");
			officeMapper.update(office);
			request.setAttribute("info", "拒绝成功！！！");
			return "admin/office/info";
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("info", "拒绝失败！！！");
			return "admin/office/info";
		}
		
		
	}
	
	
	
    
}