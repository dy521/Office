package com.office.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.office.entity.Admin;
import com.office.mapper.AdminMapper;
import com.office.utils.MsgUtil;

@Service
public class InfoServiceImpl {

	 @Autowired
	 private StringRedisTemplate redisTemplate;
	

		@Autowired
		private AdminMapper adminMapper;

	 
	 
	 public Integer resetPwd(String username, String password) {
			// TODO Auto-generated method stub
		 Admin admin = adminMapper.findAdminByName(username);
		 admin.setPassword(password);
		 //去数据库修改
		 
		 Integer result = adminMapper.update(admin);
		 if(result>0) {
			 return 1;
		 }
		 
			return 0;
		}
	 
	public boolean sendMsg(String phone) {
		//创建验证码
		String code = MsgUtil.getCode();
		try {
			MsgUtil.sendMsg(phone, code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		//存redis
		redisTemplate.opsForValue().set(phone, code, 30, TimeUnit.MINUTES);
		return true;
	}
	
	 public boolean findPwd(String username, String code) {
			// TODO Auto-generated method stub
			String usernameInRedis = redisTemplate.opsForValue().get(username);
			if(code.equals(usernameInRedis)) {
				return true;
			}else {
				return false;
			}
		}
	

}
