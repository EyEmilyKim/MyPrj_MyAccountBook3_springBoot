package com.EyEmilyKim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EyEmilyKim.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/v1/")
@RestController
@Tag(name = "API Test", description = "자잘한 작동 확인용 엔드포인트")
public class ApiTestController {
	
	private final Logger logger = LoggerFactory.getLogger(ApiTestController.class);
	private final String message = "Hello logbackTest!";
	
	@GetMapping("/echo")
	@Operation(summary = "RestController 작동 확인")
	public String echo(
			@Parameter(name = "msg", description = "클라이언트에서 전달하는 메시지", example = "아아 마잌쳌 마잌쳌", required = true)
			@RequestParam String msg) {
		LogUtil.printWithTimestamp("ApiTestController > echo() : "+msg);
		return message+"\n"+msg;
	}
	
	@GetMapping("/log")
	@Operation(summary = "서버 콘솔에 테스트 로그 출력")
	public void log(
			@Parameter(name = "level", 
				description = "출력할 테스트 로그 레벨 - all / trace / debug / info / warn / error", 
				example = "all")
			@RequestParam String level) {
		LogUtil.printWithTimestamp("ApiTestController > log() : "+level);
		switch(level) {
			case "all" : 
				logger.trace("Trace Log");
				logger.debug("Debug Log");
				logger.info("Info Log");
				logger.warn("Warn Log");
				logger.error("Error Log");
				break;
			case "trace" : 
				logger.trace("Trace Log");
				break;
			case "debug" : 
				logger.debug("Debug Log");
				break;
			case "info" : 
				logger.info("Info Log");
				break;
			case "warn" : 
				logger.warn("Warn Log");
				break;
			case "error" : 
				logger.error("Error Log");
				break;
		}
	
	}

}
