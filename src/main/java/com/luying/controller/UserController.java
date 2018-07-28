package com.luying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.luying.bean.User;
import com.luying.service.UserService;

@RestController
public class UserController {
	@Autowired
    private UserService userService;
    @RequestMapping("/all")
    public Object findAllUser(int pageNum,int pageSize){
        PageInfo<User> findAllUser = userService.findAllUser(pageNum,pageSize);
        return  findAllUser.getList();
    }
}
