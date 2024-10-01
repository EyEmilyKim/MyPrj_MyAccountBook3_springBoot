package com.EyEmilyKim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EyEmilyKim.config.properties.ApiProperties;
import com.EyEmilyKim.dto.response.EmailToDeveloperResponseDto;
import com.EyEmilyKim.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/v1/")
@RestController
@Tag(name = "API", description = "서버 지정값 전달")
public class ApiController {
	
	@Autowired
	private ApiProperties config; 
	
	@GetMapping("server-path")
	@Operation(summary = "서버 호스트 URL")
	public String getServerPath() {
		LogUtil.printWithTimestamp("ApiController > getServerPath()");
		return config.getServer_path();
	}
	
	@GetMapping("context-path")
	@Operation(summary = "어플리케이션 Context Path")
	public String getContextPath() {
		LogUtil.printWithTimestamp("ApiController > getContextPath()");
		return config.getContext_path();
	}
	
	@GetMapping("email-to-developer")
	@Operation(summary = "email 문의 템플릿")
	public EmailToDeveloperResponseDto getEmailToDeveloper() {
		LogUtil.printWithTimestamp("ApiController > getEmailToDeveloper()");
		String address = config.getEmail_to();
		String subject = config.getEmail_subject();
		
		return new EmailToDeveloperResponseDto(address, subject);
	}

}
