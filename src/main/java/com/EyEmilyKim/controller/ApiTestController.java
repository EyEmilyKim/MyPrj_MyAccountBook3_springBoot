package com.EyEmilyKim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EyEmilyKim.controller.specification.ApiTestControllerSpecification;
import com.EyEmilyKim.util.LogUtil;

@Profile("!prod") // 배포 환경에서는 비활성화
@RequestMapping("/api/test/v1/")
@RestController
public class ApiTestController implements ApiTestControllerSpecification {
	
	private final Logger logger = LoggerFactory.getLogger(ApiTestController.class);
	private final String message = "Hello ApiTest!";
	
	
	@Override
	@GetMapping("echo")
	public String echo(@RequestParam String msg) {
		LogUtil.printWithTimestamp("ApiTestController > echo() : "+msg);
		return message+"\n"+msg;
	}
	
	@Override
	@PostMapping("log")
	public ResponseEntity<String> log(@RequestParam String level) {
		LogUtil.printWithTimestamp("ApiTestController > log() : "+level);
		
		boolean isOK = true;
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
				
			default: 
				isOK = false;
				break;
		}
		
		if(isOK) {
			return new ResponseEntity<>("정상적으로 처리되었습니다.", HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>("파라미터가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
		}
	}

}
