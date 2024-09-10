package com.EyEmilyKim.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
	
}
