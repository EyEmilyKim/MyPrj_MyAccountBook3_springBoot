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

import com.EyEmilyKim.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Profile("!prod") // 배포 환경에서는 비활성화
@RequestMapping("/api/test/v1/")
@RestController
@Tag(name = "API Test", description = "자잘한 작동 확인용 엔드포인트")
public class ApiTestController {
	
	private final Logger logger = LoggerFactory.getLogger(ApiTestController.class);
	private final String message = "Hello ApiTest!";
	
	@GetMapping("echo")
	@Operation(summary = "클라이언트에서 전달한 메세지 응답하기")
	public String echo(
			@Parameter(name = "msg", description = "클라이언트에서 전달하는 메시지", example = "아아 마잌쳌 마잌쳌", required = true)
			@RequestParam String msg) {
		LogUtil.printWithTimestamp("ApiTestController > echo() : "+msg);
		return message+"\n"+msg;
	}
	
	@PostMapping("log")
	@Operation(summary = "서버 콘솔에 테스트 로그 출력시키기")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "요청 성공. 반환 데이터 없음"),
			@ApiResponse(responseCode = "400", description = "잘못된 요청"),
	})
	public ResponseEntity<String> log(
			@Parameter(name = "level", 
				description = "출력할 테스트 로그 레벨 - all / trace / debug / info / warn / error", 
				example = "all")
			@RequestParam String level) {
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
		if(isOK) return new ResponseEntity<>("정상적으로 처리되었습니다.", HttpStatus.NO_CONTENT);
		else return new ResponseEntity<>("파라미터가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
	}

}
