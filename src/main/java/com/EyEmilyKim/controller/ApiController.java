package com.EyEmilyKim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EyEmilyKim.config.properties.ApiProperties;
import com.EyEmilyKim.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/v1/")
@RestController
@Tag(name = "API", description = "")
public class ApiController {
	
	@Autowired
	private ApiProperties config; 
	
	@GetMapping("server-path")
	@Operation(summary = "서버 URL 정보 얻기")
	public String getServerPath() {
		LogUtil.printWithTimestamp("ApiController > getServerPath()");
		return config.getServer_path();
	}

}
