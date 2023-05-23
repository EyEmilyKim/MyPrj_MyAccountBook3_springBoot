package com.EyEmilyKim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/logbackTest/")
@RestController
public class LogbackTestController {
	
	private final Logger logger = LoggerFactory.getLogger(LogbackTestController.class);
	
	@RequestMapping("/hello")
	public String logbackTest() {
		return "Hello logbackTest!";
	}
	
	@RequestMapping("/log")
	public void log() {
		logger.trace("Trace Log");
		logger.debug("Debug Log");
		logger.info("info Log");
		logger.warn("warn Log");
		logger.error("error Log");
	}

}
