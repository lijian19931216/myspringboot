package com.luying.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luying.exception.UserNotExistException;


@ControllerAdvice
public class MyExceptionHandler {
	//@ResponseBody
	@ExceptionHandler(UserNotExistException.class)
	public String handler(Exception e,HttpServletRequest request) {
		request.setAttribute("javax.servlet.error.status_code", 400);
		Map<String, String> map = new HashMap<>();
		map.put("code", "001");
		map.put("message", e.getMessage());
		request.setAttribute("ext", map);
		return "forward:/error";
	}
}
