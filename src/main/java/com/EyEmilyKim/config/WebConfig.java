package com.EyEmilyKim.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.EyEmilyKim.interceptor.LoginInterceptor;
import com.EyEmilyKim.interceptor.OperatingHoursInterceptor;
import com.EyEmilyKim.interceptor.UserInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	OperatingHoursInterceptor operatingHoursInterceptor;
	@Autowired
	UserInterceptor userInterceptor;
	@Autowired
	LoginInterceptor loginInterceptor;
	
	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 모든 경로에 UserInterceptor 추가
		registry.addInterceptor(userInterceptor).addPathPatterns("/**");
		// 로그인 필요한 경로에 LoginInterceptor 추가
		registry.addInterceptor(loginInterceptor).addPathPatterns(
			"/tran/**", 
			"/plan/**", 
			"/set/**"
		);
		
		if(!activeProfile.equals("dev")) {
			// DB 접근 필요한 경로에 OperatingHoursInterceptor 추가
			registry.addInterceptor(operatingHoursInterceptor).addPathPatterns(
				"/tran/**", 
				"/plan/**", 
				"/set/**",
				"/login"
			);
		}
	}
	
	@Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/favicon.ico")
              .addResourceLocations("classpath:/static/");
  }
}
