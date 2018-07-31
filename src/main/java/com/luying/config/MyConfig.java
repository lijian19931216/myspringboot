package com.luying.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.luying.filter.MyFilter;
import com.luying.interceptor.LoginInterceptor;
import com.luying.listener.MyListener;
import com.luying.service.TestService;
import com.luying.servlet.MyServlet;

@Configuration
@SuppressWarnings("deprecation")
public class MyConfig extends WebMvcConfigurerAdapter {

	@Bean
	public TestService helloService() {
		return new TestService();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/luying").setViewName("success");
		;
	}

	@Bean // 将组件注册在容器
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {

		WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {

			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("login");
				registry.addViewController("/index.html").setViewName("login");
				registry.addViewController("/main.html").setViewName("dashboard");
			}

			// 注册拦截器
			/*
			 * @Override public void addInterceptors(InterceptorRegistry registry) { //
			 * super.addInterceptors(registry); // 静态资源； *.css , *.js //
			 * SpringBoot已经做好了静态资源映射 registry.addInterceptor(new
			 * LoginInterceptor()).addPathPatterns("/**")
			 * .excludePathPatterns("/index.html", "/", "/login"); }
			 */
		};
		return adapter;
	}

	@Bean
	public ServletRegistrationBean myServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(), "/myservlet");
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean myFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new MyFilter());
		registrationBean.setUrlPatterns(Arrays.asList("/hello","/myservlet"));
		
		return registrationBean;
	}
	@Bean
	public ServletListenerRegistrationBean myListener() {
		ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<MyListener>(new MyListener());
		
		return registrationBean;
	}

	/*@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {

			// 定制嵌入式的Servlet容器相关的规则
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setPort(8083);
			}
		};
	}*/

}
