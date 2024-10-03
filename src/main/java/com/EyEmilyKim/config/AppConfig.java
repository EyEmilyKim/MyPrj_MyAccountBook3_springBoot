package com.EyEmilyKim.config;

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
	
}
