package com.office.controller.mag;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.office.entity.Emp;
import com.office.entity.Msg;
import com.office.entity.Office;
import com.office.mapper.EmpMapper;
import com.office.mapper.OfficeMapper;
import com.office.utils.StringTools;


@Controller
@RequestMapping("/mag")
public class MagOfficeController {

	@Autowired
	private OfficeMapper officeMapper;
	
	@Autowired
	private EmpMapper magMapper;
	
	@Value("${file.path}")
	private String fileBasePath;
	
	

	@RequestMapping("/office")
	public String office(Integer pageNum,
			String name,
			Model model,HttpSession session){
		Emp mag = 	(Emp) session.getAttribute("mag");
		Integer mid = mag.getId();
		List<Office> officeF = new ArrayList<Office>();
		if(pageNum==null) {
			pageNum=1;
		}
		List<Office> offices = officeMapper.findList(null);
		for (Office office : offices) {
			String mids = office.getEmp_str();
			String[] arr = mids.split(",");
			boolean flag = false;
			for (int i = 0; i < arr.length; i++) {
				if(arr[i].equals(mid+"")) {
					flag = true;
				}
			}
			if(flag) {
				officeF.add(office);
			}
		}
		//分页并查询
		PageHelper.startPage(pageNum,10);

		List<Office> officefs = officeF;
		System.out.println(offices);
		PageInfo pageInfo = new PageInfo<Office>(officefs, 10);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "mag/office/list";
	}
	
	
	
	//@{/mag/officePass(id=${pa.id})}
	@RequestMapping(value="/officePass")
	public String officePass(Integer id,Model model,HttpServletRequest request,HttpSession session){
		try {
			Office office = officeMapper.findById(id);
			Emp mag = 	(Emp) session.getAttribute("mag");
			Integer mid = mag.getId();
			String mids = office.getEmp_str();
			String[] arr = mids.split(",");
			String str = "";
			String strEnd = "";
			for (int i = 0; i < arr.length; i++) {
				if(!arr[i].equals(mid+"")) {
					str+=arr[i]+",";
				}
			}
			
			
			if(str.equals("")) {
				office.setStatus("经理通过");
			}else {
				strEnd = str.substring(0, str.length()-1);
			}
			
			office.setEmp_str(strEnd);
			
			officeMapper.update(office);
			request.setAttribute("info", "审核成功！！！");
			return "mag/office/info";
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("info", "审核拒绝！！！");
			return "mag/office/info";
		}		
	}
		
	//@{/mag/officeReject(id=${pa.id})}
	@RequestMapping(value="/officeReject")
	public String officeReject(Integer id,Model model,HttpServletRequest request,HttpSession session){
		try {
			Office office = officeMapper.findById(id);
			office.setEmp_str("");
			office.setStatus("经理不通过");
			officeMapper.update(office);
			request.setAttribute("info", "拒绝成功！！！");
			return "mag/office/info";
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("info", "拒绝失败！！！");
			return "mag/office/info";
		}		
	}
   
}