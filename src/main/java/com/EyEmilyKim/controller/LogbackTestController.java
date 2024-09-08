package com.EyEmilyKim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EyEmilyKim.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/logbackTest/")
@RestController
@Tag(name = "LogbackTest", description = "서버 측 로깅 확인용")
public class LogbackTestController {
	
	private final Logger logger = LoggerFactory.getLogger(LogbackTestController.class);
	private final String message = "Hello logbackTest!";
	
	@GetMapping("/hello")
	@Operation(summary = "check RestController", description = "RestController 작동 확인")
	public String logbackTest() {
		LogUtil.printWithTimestamp(message);
		return message;
	}
	
	@GetMapping("/log")
	@Operation(summary = "leave server side log in all level", description = "서버 콘솔에 레벨별 테스트 로그 남기기")
	public void log() {
		LogUtil.printWithTimestamp(message+"/log");
		logger.trace("Trace Log");
		logger.debug("Debug Log");
		logger.info("info Log");
		logger.warn("warn Log");
		logger.error("error Log");
	}

}
