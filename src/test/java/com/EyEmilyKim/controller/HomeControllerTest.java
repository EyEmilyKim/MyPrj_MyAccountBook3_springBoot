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

import com.EyEmilyKim.config.OperatingHoursConfig;
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