package com.luying.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.luying.bean.User;
@Mapper
public interface UserDao {
	List<User> selectUsers();
	
}
