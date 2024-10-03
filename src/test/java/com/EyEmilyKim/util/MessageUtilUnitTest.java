package com.EyEmilyKim.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MessageUtilUnitTest {	

@InjectMocks
private MessageUtil messageUtil;

@Mock
private TextFileReader textFileReader;

private static final String path = "/custom_messages/"; 

	@BeforeEach
	public void setUp() {
		// Mock 객체 초기화
	  MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("파일에 있는 키 : 값 반환")
	public void testGetMessage_existingKey() throws IOException {
		// Given
		String fileName = "message-error";
		String key = "msg.error.404";
		Map<String, String> mockMessages = new HashMap<>();
		mockMessages.put(key, "존재하지 않는 페이지입니다.");
		when(textFileReader.loadData(path, fileName)).thenReturn(mockMessages);
		
		// When & Then
		assertEquals("존재하지 않는 페이지입니다.", messageUtil.getMessage(fileName, key));
	}

	@Test
	@DisplayName("파일에 없는 키 : 'Message not found' 반환")
	public void testGetMessage_keyNotFound() throws IOException {
		// Given
		String fileName = "message-error";
		String key = "unknownKey";
		Map<String, String> mockMessages = new HashMap<>();
		when(textFileReader.loadData(path, fileName)).thenReturn(mockMessages);
		
		// When & Then
		assertEquals("Message not found", messageUtil.getMessage(fileName, key));
	}

	@Test
	@DisplayName("없는 파일 : 'Error loading message file' 반환")
	public void testGetMessage_fileNotFound() throws IOException {
	  // Given
		String fileName = "nonExistingFile";
		String key = "someKey";
		when(textFileReader.loadData(path, fileName)).thenThrow(new IOException("File not found"));
		
		// When & Then
		assertEquals("Error loading message file", messageUtil.getMessage(fileName, key));
	}

}
