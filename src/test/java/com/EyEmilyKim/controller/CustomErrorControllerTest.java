package com.EyEmilyKim.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.RequestDispatcher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.EyEmilyKim.config.AppConfig;
import com.EyEmilyKim.util.MessageUtil;

@SpringBootTest
@AutoConfigureMockMvc
class CustomErrorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MessageUtil messageUtil;
	
	@MockBean
	AppConfig appConfig;
	
	
	private static final String referer = "/previous-path";
	private static final String errorMessage_404 = "존재하지 않는 페이지입니다.";
	private static final String errorMessage_500 = "서버 내부 오류가 발생했습니다.";
	private static final String errorMessage_else = "알 수 없는 오류가 발생했습니다.";
	
	@BeforeEach
	void setUp() {
		// given common
		when(appConfig.getContextPath()).thenReturn("/mab3");
	}
	
	@Test
	@DisplayName("404 - '존재하지 않는 페이지입니다.' > 이전 경로")
	void testHandleError_404() throws Exception {
		// given 
		when(messageUtil.getMessage("message-error", "msg.error.404")).thenReturn(errorMessage_404);
		
		// when & then
		mockMvc.perform(get("/error")
				.requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 404)
				.header("Referer", referer))
		.andExpect(status().isOk())
		.andExpect(view().name("redirect"))
		.andExpect(model().attribute("MSG", errorMessage_404))
		.andExpect(model().attribute("URL", referer));
		
	}
	
	@Test
	@DisplayName("500 - '서버 내부 오류가 발생했습니다.' > 이전 경로")
	void testHandleError_500() throws Exception {
		// given 
		when(messageUtil.getMessage("message-error", "msg.error.500")).thenReturn(errorMessage_500);
		
		// when & then
		mockMvc.perform(get("/error")
				.requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 500)
				.header("Referer", referer))
		.andExpect(status().isOk())
		.andExpect(view().name("redirect"))
		.andExpect(model().attribute("MSG", errorMessage_500))
		.andExpect(model().attribute("URL", referer));
		
	}
	
	@Test
	@DisplayName("기타 - '알 수 없는 오류가 발생했습니다.' > 이전 경로")
	void testHandleError_else() throws Exception {
		// given 
		when(messageUtil.getMessage("message-error", "msg.error.else")).thenReturn(errorMessage_else);
		
		// when & then
		mockMvc.perform(get("/error")
				.requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 777)
				.header("Referer", referer))
		.andExpect(status().isOk())
		.andExpect(view().name("redirect"))
		.andExpect(model().attribute("MSG", errorMessage_else))
		.andExpect(model().attribute("URL", referer));
		
	}

}
