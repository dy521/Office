package com.office.controller.emp;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/emp")
public class EmpOfficeController {

	@Autowired
	private OfficeMapper officeMapper;
	
	@Autowired
	private EmpMapper empMapper;
	
	@Value("${file.path}")
	private String fileBasePath;
	
	

	@RequestMapping("/office")
	public String office(Integer pageNum,
			String name,
			Model model,HttpSession session){
		Emp emp = 	(Emp) session.getAttribute("emp");
		if(pageNum==null) {
			pageNum=1;
		}
		Office office = new Office();
		office.setName(name);
		office.setEmpid(emp.getId());
		//分页并查询
		PageHelper.startPage(pageNum,5);
		List<Office> offices = officeMapper.findList(office);
		System.out.println(offices);
		PageInfo pageInfo = new PageInfo<Office>(offices, 5);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "emp/office/list";
	}
	
	
	@RequestMapping(value="/gotoAddOffice")
	public String gotoAddOffice(Model model){
		Emp emp = new Emp();
		emp.setType("经理");
		List<Emp> magList = empMapper.findList(emp);
		
		model.addAttribute("mags", magList);
		return "emp/office/add";
	}
	
	
	
	
	
	/*
	 * 上传
	 */
	@RequestMapping(value="/addOffice", method = {RequestMethod.POST})
	public ModelAndView addVideo(Office office,BindingResult bindingResult,
			@RequestParam("upload") MultipartFile upload,HttpSession session) {
		
		Emp emp = 	(Emp) session.getAttribute("emp");
		//获取文件的保存路径
		String path=fileBasePath+"office";
		//获取上传文件的名称
		String filename=upload.getOriginalFilename();
		//获取上传文件的扩展名
		String suffix=filename.substring(filename.lastIndexOf("."));
		//为了防止上传文件同名，需要给上传文件重新命名
		String tempFileName=StringTools.getUUID().toString()+suffix;
		File dest = new File(path + "/" + tempFileName);
		try {
			upload.transferTo(dest); //保存文件
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		office.setPath(path + "/" + tempFileName);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08:355

		office.setCreate_time(formatStr);
		office.setEmpid(emp.getId());
		office.setStatus("已发布");
		
		officeMapper.insert(office);
		ModelAndView modelAndView = new ModelAndView("redirect:/emp/office");
		return modelAndView;
	}
    
}