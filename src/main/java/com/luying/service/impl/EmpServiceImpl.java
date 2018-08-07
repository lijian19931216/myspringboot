package com.luying.service.impl;

import com.luying.cache.Employee;
import com.luying.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: lijian
 * @create: 2018-08-05
 **/
@Service
public class EmpServiceImpl {
    @Autowired
    EmployeeDao empdao;

    /**
     * @Description:
     * @Param: [id]
     * @return: com.luying.cache.Employee
     * @Author: lijian
     * @Date: 2018/8/5
     */
    @Cacheable(value = "emp",cacheManager = "myCacheManager")
    public Employee getEmp(Integer id) {
        return empdao.getEmpById(id);
    }

    @CachePut(value = "emp", key = "#emp.id")
    public Employee updateEmp(Employee emp) {
        empdao.updateEmpById(emp);
        return emp;
    }
}
