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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.EyEmilyKim.config.OperatingHoursConfig;
import com.EyEmilyKim.interceptor.LoginInterceptor;
import com.EyEmilyKim.service.UserService;

@WebMvcTest(HomeController.class)
class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private OperatingHoursConfig operatingHoursConfig;
	@MockBean
	private LoginInterceptor loginInterceptor;
	
	
	/*-------- 홈 화면 --------*/
	
	@Test
	@DisplayName("홈 Get")
	void testIndexGet() throws Exception {
		mockMvc.perform(get("/index"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.index"));
	}

	/*-------- 시간 외 홈 화면 --------*/
	
	@Test
	@DisplayName("시간외 홈 Get")
	void testOutOfOpHoursGet() throws Exception {
		mockMvc.perform(get("/outOfOpHours"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.outOfOpHours"));
	}
	
	/*-------- 월별 계획 --------*/
	
	@Test
	@DisplayName("월별계획 Get (로그인 O) > 준비중")
	void testPlanGet_whenLoggedIn() throws Exception {
		// LoginInterceptor 가 true 반환했을 때만 호출됨
		when(loginInterceptor.preHandle(
				any(HttpServletRequest.class), any(HttpServletResponse.class), any()))
			.thenReturn(true);
		
		mockMvc.perform(get("/plan"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.comingSoon"));
		
		// 검증 : preHandle 메서드가 정확히 1번 호출되었는지 확인
    verify(loginInterceptor, times(1))
    	.preHandle(any(HttpServletRequest.class), any(HttpServletResponse.class), any());
	}
	
	/*-------- 로그인 --------*/
	
	@Test
	@DisplayName("로그인 Get")
	void testLoginGet() throws Exception {
		mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.login"));
	}
	
}
