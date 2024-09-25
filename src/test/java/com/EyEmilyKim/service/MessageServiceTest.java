package com.EyEmilyKim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageServiceTest {

	@Autowired
	private MessageService  messageService;
	
	@Test
	@DisplayName("message-error 파일 읽어오기")
	void testGetMessageFromFile_error() {
		// 파일에 존재하는 메시지 검증
		String errorMessage = messageService.getMessage("message-error", "msg.error.404");
		assertEquals("존재하지 않는 페이지입니다.", errorMessage);
		
		// 파일에 존재하지 않는 메시지 검증
		String unknownMessage = messageService.getMessage("message-error", "msg.error.unknown");
		assertEquals("Message not found", unknownMessage); 
	}
}
