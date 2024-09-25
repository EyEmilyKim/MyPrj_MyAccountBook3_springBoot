package com.EyEmilyKim.config.messages;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageFileReaderTest {
	
	private MessageFileReader errorMessages;
	
	@BeforeEach
	void setUp() throws IOException {
		errorMessages = new MessageFileReader(); // 객체 생성, 파일 읽어오기
	}

	@Test
	void testGetMessage_404() {
		String message = errorMessages.getMessage("msg.error.404");
		assertThat(message).isEqualTo("존재하지 않는 페이지입니다.");
	}
	
	@Test
	void testGetMessage_500() {
		String message = errorMessages.getMessage("msg.error.500");
		assertThat(message).isEqualTo("서버 내부 오류가 발생했습니다.");
	}
	
	@Test
	void testGetMessage_else() {
		String message = errorMessages.getMessage("msg.error.else");
		assertThat(message).isEqualTo("알 수 없는 오류가 발생했습니다.");
	}

}
