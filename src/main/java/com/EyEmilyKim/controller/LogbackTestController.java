package com.EyEmilyKim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EyEmilyKim.util.LogUtil;

@RequestMapping("/logbackTest/")
@RestController
public class LogbackTestController {
	
	private final Logger logger = LoggerFactory.getLogger(LogbackTestController.class);
	private final String message = "Hello logbackTest!";
	
	@GetMapping("/hello")
	public String logbackTest() {
		LogUtil.printWithTimestamp(message);
		return message;
	}
	
	@GetMapping("/log")
	public void log() {
		LogUtil.printWithTimestamp(message+"/log");
		logger.trace("Trace Log");
		logger.debug("Debug Log");
		logger.info("info Log");
		logger.warn("warn Log");
		logger.error("error Log");
	}

}
