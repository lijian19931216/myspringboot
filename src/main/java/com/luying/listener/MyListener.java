package com.luying.listener;

import javax.servlet.Filter;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("web 初始化");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("web 销毁");
		
	}

}
