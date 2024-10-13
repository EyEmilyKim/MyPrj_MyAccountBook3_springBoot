package com.EyEmilyKim.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.EyEmilyKim.filter.SwaggerTestFilter;

@Configuration
@Profile("!dev")
public class FilterConfig_NOTdev {
	
	@Autowired
	private SwaggerTestFilter swaggerTestFilter;

 
	@Bean
	public FilterRegistrationBean<SwaggerTestFilter> swaggerTestFilterRegistration() {
		FilterRegistrationBean<SwaggerTestFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(swaggerTestFilter);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
	
}
