package com.EyEmilyKim.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.EyEmilyKim.config.AppConfig;
import com.EyEmilyKim.config.properties.OperatingHoursProperties;
import com.EyEmilyKim.interceptor.LoginInterceptor;

@SpringBootTest
@AutoConfigureMockMvc
class PlanControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OperatingHoursProperties operatingHoursProperties;
	
	@MockBean
	private LoginInterceptor loginInterceptor;
	
	@MockBean
	private AppConfig appConfig;
	
	
	@BeforeEach
	void setup() throws Exception {
		// LoginInterceptor 가 true 반환했을 때만 호출됨
		when(loginInterceptor.preHandle(any(HttpServletRequest.class), any(HttpServletResponse.class), any()))
			.thenReturn(true);
		// context-path 사전 설정
		when(appConfig.getContextPath()).thenReturn("/mab3"); 
	}

	@AfterEach
	void verifyLoginInterceptor() throws Exception {
		// 검증 : LoginInterceptor 가 정확히 1번 호출되었는지
		verify(loginInterceptor, times(1))
			.preHandle(any(HttpServletRequest.class), any(HttpServletResponse.class), any());
	}
	
	/*-------- 계획하기 --------*/
  
	@Test
	@DisplayName("계획하기 Get > 준비중")
	void testGetPlan_whenLoggedIn() throws Exception {
		// when & then
		mockMvc.perform(get("/plan"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.comingSoon"));
	}
	
}
