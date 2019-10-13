package com.office.controller.admin;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.office.entity.Msg;
import com.office.mapper.MsgMapper;
import com.office.utils.StringTools;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminMsgController {

	@Autowired
	private MsgMapper msgMapper;
	

	@Value("${file.path}")
	private String fileBasePath;
	

	@RequestMapping("/msg")
	public String msg(Integer pageNum,
			String name,
			Model model){
		if(pageNum==null) {
			pageNum=1;
		}
		Msg msg = new Msg();
		msg.setName(name);
		//分页并查询
		PageHelper.startPage(pageNum,5);
		List<Msg> msgs = msgMapper.findList(msg);
		System.out.println(msgs);
		PageInfo pageInfo = new PageInfo<Msg>(msgs, 5);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "admin/msg/list";
	}
	
	
	@RequestMapping(value="/gotoAddMsg")
	public String gotoAddMsg(Model model){
		return "admin/msg/add";
	}
	
	
	/*
	 * 上传
	 */
	@RequestMapping(value="/addMsg", method = {RequestMethod.POST})
	public ModelAndView addVideo(Msg msg,BindingResult bindingResult,
			@RequestParam("upload") MultipartFile upload) {
		
		
		//获取文件的保存路径
		String path=fileBasePath+"msg";
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
		
		msg.setPath(path + "/" + tempFileName);
		
		
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08:355

		msg.setCreate_time(formatStr);
		
		msgMapper.insert(msg);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/msg");
		return modelAndView;
	}
	
	
	
	/**
	 */
	@RequestMapping(value="/msgDel")
	public ModelAndView deleteEmp(Integer id){
		msgMapper.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/msg");
		return modelAndView;
	}
	
	//下载
	//@{/admin/gotoDownLoadMsg(path=${pa.path})}
	@RequestMapping(value="/gotoDownLoadMsg")
	public String newGuideDownLoad(String path,HttpServletResponse response){
		
		File file = new File(path);
	    System.out.println(path);
	    log.info("path:"+path+"正在下载");
	    String filename=path.substring(path.lastIndexOf("/"));
		
		if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file); 
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
	}
	
	
	
	
    
}