package com.EyEmilyKim.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

import com.EyEmilyKim.config.properties.OperatingHoursProperties;
import com.EyEmilyKim.dto.UserSessionDto;
import com.EyEmilyKim.interceptor.LoginInterceptor;
import com.EyEmilyKim.service.UserService;

@WebMvcTest(HomeController.class)
class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private OperatingHoursProperties operatingHoursProperties;
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
	
	/*-------- 로그인 --------*/
	
	@Test
	@DisplayName("로그인 Get")
	void testLoginGet() throws Exception {
		mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.login"));
	}
	
	@Test
	@DisplayName("로그인 Post - 성공 > 알림창")
	void testLoginPost_success() throws Exception {
		String userId = "testUserId";
		String password = "password";
		
		when(userService.login(userId, password))
			.thenReturn(new UserSessionDto(777, "test-user", null));
		
		mockMvc.perform(post("/login")
				.param("LID", userId)
				.param("PWD", password))
			.andExpect(model().attribute("MSG", "로그인에 성공했습니다. \\n환영합니다~ test-user님~!"))
			.andExpect(model().attribute("URL", "/index"))
			.andExpect(status().isOk())
			.andExpect(view().name("redirect"));
	}
	
	@Test
	@DisplayName("로그인 Post - 사용자 없음 > 알림창")
	void testLoginPost_userNotFound() throws Exception {
		String userId = "nonExistingUser";
		String password = "wrongPassword";
		
		when(userService.login(userId, password))
			.thenThrow(new Exception("사용자를 찾을 수 없습니다."));
		
		mockMvc.perform(post("/login")
				.param("LID", userId)
				.param("PWD", password))
			.andExpect(model().attribute("MSG", "사용자를 찾을 수 없습니다."))
			.andExpect(model().attribute("URL", "/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("redirect"));
	}
	
	@Test
	@DisplayName("로그인 Post - 비밀번호 불일치 > 알림창")
	void testLoginPost_wrongPassword() throws Exception {
		String userId = "existingUser";
		String password = "wrongPassword";
		
		when(userService.login(userId, password))
			.thenThrow(new Exception("비밀번호가 일치하지 않습니다."));
		
		mockMvc.perform(post("/login")
				.param("LID", userId)
				.param("PWD", password))
			.andExpect(model().attribute("MSG", "비밀번호가 일치하지 않습니다."))
			.andExpect(model().attribute("URL", "/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("redirect"));
	}
	
}
