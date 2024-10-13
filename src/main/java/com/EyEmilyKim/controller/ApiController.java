package com.EyEmilyKim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EyEmilyKim.config.properties.ApiProperties;
import com.EyEmilyKim.controller.specification.ApiControllerSpecification;
import com.EyEmilyKim.dto.response.EmailToDeveloperResponseDto;
import com.EyEmilyKim.util.LogUtil;

@RequestMapping("/api/v1/")
@RestController
public class ApiController implements ApiControllerSpecification {
	
	@Autowired
	private ApiProperties config; 
	
	
	@Override
	@GetMapping("email-to-developer")
	public EmailToDeveloperResponseDto getEmailToDeveloper() {
		LogUtil.printWithTimestamp("ApiController > getEmailToDeveloper()");
		String address = config.getEmail_to();
		String subject = config.getEmail_subject();
		
		return new EmailToDeveloperResponseDto(address, subject);
	}

}
