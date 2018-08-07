package com.luying.controller;

import com.luying.cache.Department;
import com.luying.service.impl.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: lijian
 * @create: 2018-08-06
 **/
@Controller
public class DeptController {
    @Autowired
    DeptServiceImpl deptService;
    @RequestMapping("dept/{id}")
    @ResponseBody
    public Department getDept(@PathVariable Integer id){
        return deptService.getDeptById(id);
    }
}
