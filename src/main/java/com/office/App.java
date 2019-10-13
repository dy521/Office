package com.office;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//指定mapper的包路径，省去@Mapper注解
@MapperScan("com.office.mapper")
@Configuration 
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //文件最大  
        //KB,MB
        factory.setMaxFileSize("102400MB");
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("1024000MB");  
        return factory.createMultipartConfig();  
    }
	
}
