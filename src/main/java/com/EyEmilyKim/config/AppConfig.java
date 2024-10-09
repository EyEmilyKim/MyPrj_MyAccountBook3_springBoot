package com.EyEmilyKim.config;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Component
public class AppConfig {
	
	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	@Value("${app.version}")
	private String appVersion;
	
	
	/* --- JSP 에서 변수 사용할 수 있도록 ServletContext 에 저장 --- */
	
	private final ServletContext servletContext;
	
	public AppConfig(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@PostConstruct
	public void init() {
		// JSP 에서 사용할 수 있도록 ServletContext 에 값 저장
		servletContext.setAttribute("appVersion", appVersion);
	}
	
}
