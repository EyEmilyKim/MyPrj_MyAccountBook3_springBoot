package com.EyEmilyKim.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageUtilTest {

	@Autowired
	private MessageUtil  messageUtil;
	
  
	@Test
	@DisplayName("파일에 있는 키 : 값 반환")
	void testGetMessage_existingKey() {
		// Given
		String fileName = "message-error";
		String key = "msg.error.404";
		// When & Then
		assertEquals("존재하지 않는 페이지입니다.", messageUtil.getMessage(fileName, key));
	}
	
	@Test
	@DisplayName("파일에 없는 키 : 'Message not found' 반환")
	void testGetMessage_keyNotFound() {
		// Given
		String fileName = "message-error";
		String key = "unknownKey";
		// When & Then
		assertEquals("Message not found", messageUtil.getMessage(fileName, key)); 
	}
	
	@Test
	@DisplayName("없는 파일 : 'Error loading message file' 반환")
	void testGetMessage_fileNotFound() {
		// Given
		String fileName = "nonExistingFile";
		String key = "someKey";
		// When & Then
		assertEquals("Error loading message file", messageUtil.getMessage(fileName, key));
	}
	
}
