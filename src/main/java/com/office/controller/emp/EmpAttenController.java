package com.office.controller.emp;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.entity.Atten;
import com.office.entity.Emp;
import com.office.mapper.AttenMapper;


@Controller
@RequestMapping("/emp")
public class EmpAttenController {

	@Autowired
	private AttenMapper attenMapper;
	


	@RequestMapping("/atten")
	public String atten(Integer pageNum,
			String name,
			Model model,HttpSession session){
		if(pageNum==null) {
			pageNum=1;
		}
		Emp emp = 	(Emp) session.getAttribute("emp");
		Atten atten = new Atten();
		atten.setEmpid(emp.getId());
		//分页并查询
		PageHelper.startPage(pageNum,5);
		List<Atten> attens = attenMapper.findList(atten);
		System.out.println(attens);
		PageInfo pageInfo = new PageInfo<Atten>(attens, 5);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "emp/atten/list";
	}
	
	
	///emp/downAtten
	@RequestMapping(value = "downAtten", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response,HttpSession session) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        Emp emp = 	(Emp) session.getAttribute("emp");
		Atten atten = new Atten();
		atten.setEmpid(emp.getId());
        
		List<Atten> attens = attenMapper.findList(atten);
        
        String fileName = "atten"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;
        String[] headers = { "员工姓名", "上班打卡", "下班打卡", "是否有效"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (Atten attendb  : attens) {
        	//用户行完成
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(attendb.getEmpname());
            row1.createCell(1).setCellValue(attendb.getUp_time());
            row1.createCell(2).setCellValue(attendb.getDown_time());
            row1.createCell(3).setCellValue(attendb.getIs_use());         
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
	
	
	/*
	 * 打卡
	 */
	@RequestMapping(value="/addAtten")
	public String addAtten(HttpSession session,HttpServletRequest request) {
		Emp emp = 	(Emp) session.getAttribute("emp");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08
		
		
		Atten attendb = attenMapper.findByUpTime(formatStr,emp.getId());
		if(attendb!=null) {
			request.setAttribute("info", "您今天已经打过卡了！！！");
			return "emp/atten/info";
		}
		
		
		
		Atten atten = new  Atten();
		atten.setEmpid(emp.getId());
		atten.setUp_time(formatStr);
		atten.setIs_use("否");
		
		System.out.println("atten:"+atten);  
		attenMapper.insert(atten);
		request.setAttribute("info", "上班打卡成功！！！");
		return "emp/atten/info";
	}
	
	
	
	
	@RequestMapping(value="/editAtten")
	public String editAtten(HttpSession session,HttpServletRequest request) {
		Emp emp = 	(Emp) session.getAttribute("emp");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		String formatStr =formatter.format(new Date());
		
		System.out.println(formatStr);//2017-九月-15 13:17:08:355
		Atten attendb = attenMapper.findByUpTime(formatStr,emp.getId());
		
		if(attendb!=null) {//上午打过卡了
			//去数据库修改
			attendb.setDown_time(formatStr);
			attendb.setIs_use("是");
			attenMapper.update(attendb);
			request.setAttribute("info", "下班打卡成功！！！");
			return "emp/atten/info";
		}else {
			Atten atten = new  Atten();
			atten.setEmpid(emp.getId());
			atten.setDown_time(formatStr);
			atten.setIs_use("否");
			attenMapper.insert(atten);
			request.setAttribute("info", "下班打卡成功！！！");
			return "emp/atten/info";
		}
		
	}
   
}