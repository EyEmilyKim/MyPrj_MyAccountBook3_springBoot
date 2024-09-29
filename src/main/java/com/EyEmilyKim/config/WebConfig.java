package com.EyEmilyKim.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.EyEmilyKim.interceptor.LoginInterceptor;
import com.EyEmilyKim.interceptor.OperatingHoursInterceptor;
import com.EyEmilyKim.interceptor.UserIdInterceptor;
import com.EyEmilyKim.interceptor.WantedUrlInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	OperatingHoursInterceptor operatingHoursInterceptor;
	
	@Autowired
	UserIdInterceptor userIdInterceptor;
	
	@Autowired
	WantedUrlInterceptor wantedUrlInterceptor;
	@Autowired
	LoginInterceptor loginInterceptor;
	
	
	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 모든 경로에 UserIdInterceptor 추가
		registry.addInterceptor(userIdInterceptor).addPathPatterns("/**");
		// 로그인 필요한 경로에 wantedUrlInterceptor 추가
		registry.addInterceptor(wantedUrlInterceptor).addPathPatterns(
				"/tran/**", 
				"/plan/**", 
				"/set/**"
				);
		// 로그인 필요한 경로에 LoginInterceptor 추가
		registry.addInterceptor(loginInterceptor).addPathPatterns(
			"/tran/**", 
			"/plan/**", 
			"/set/**"
		);
		
		if(!"dev".equals(activeProfile)) {
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
