package com.office.utils;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class MsgUtil {

	
	//获取验证码
		public static String getCode() {
			Random rand =new Random();
			String code = "";
			for(int i=0;i<6;i++) {
				code += rand.nextInt(10);
			}
			return code;
		}
				
	//发送短信
		public static  void  sendMsg(String phone,String code) throws HttpException, IOException {
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/");
			post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
			NameValuePair[] data ={ 
					new NameValuePair("Uid", "一一一三"),// 注册的用户名  
					new NameValuePair("Key", "d41d8cd98f00b204e980"),// 注册成功后，登录网站后得到的密钥  
					new NameValuePair("smsMob",phone),// 手机号码  
					new NameValuePair("smsText","验证码："+code)};// 短信内容  
			post.setRequestBody(data);
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:"+statusCode);
			for(Header h : headers)
			{
				System.out.println("-----"+h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("utf8"));
			System.out.println(result); //打印返回消息状态
			post.releaseConnection();
		}		
}
