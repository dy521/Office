package com.office.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String username;

	public void sendEmail(String to,String content)  {
		    SimpleMailMessage message = new SimpleMailMessage();
	        //读取邮件服务主机并设置
		    message.setFrom(username);
	        //设置接收方
	        message.setTo(to);
	        //设置邮件标题
	        message.setSubject("测试");
	        message.setText(content);
	        javaMailSender.send(message);
	}

	
}