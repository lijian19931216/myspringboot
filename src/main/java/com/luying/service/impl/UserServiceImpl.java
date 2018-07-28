package com.luying.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luying.bean.User;
import com.luying.dao.UserDao;
import com.luying.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	 @Autowired
	 private UserDao userDao;//这里会报错，但是并不会影响
	@Override
	public PageInfo<User> findAllUser(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
        List<User> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
	}

}
