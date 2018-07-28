package com.luying.service;

import com.github.pagehelper.PageInfo;
import com.luying.bean.User;

public interface UserService {
	PageInfo<User> findAllUser(int pageNum, int pageSize);	
}
