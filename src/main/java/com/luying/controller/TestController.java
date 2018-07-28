package com.luying.controller;


import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.util.StringUtil;
import com.luying.exception.UserNotExistException;
@Controller
public class TestController {
	Logger logger = LoggerFactory.getLogger(getClass());
//	"classpath:/templates/"
	@RequestMapping("/success")
	public String success(Map<String,Object> map) {
		map.put("name", "李剑");
		map.put("persons", Arrays.asList("张三","李四","王五"));
		return "success";
	}
	@RequestMapping({"/","/index.html"})
	public String index() {
		
		return "login";
	}
	@RequestMapping({"/login"})
	public String login(String username,String password,Map<String, String> map,
			HttpSession session) {
		logger.info(username);
		if (!StringUtil.isEmpty(username) && "123".equals(password)) {
			session.setAttribute("loginuser", username);
			return "redirect:/main.html";
			
		}else {
			map.put("msg", "登录失败");
			return "login";
		}
		
	}
	@RequestMapping("/hello")
	public String hello(String str) {
		if ("123".equals(str)) {
			throw new UserNotExistException();
		}
		
		return "success";
		
		
	}
}
