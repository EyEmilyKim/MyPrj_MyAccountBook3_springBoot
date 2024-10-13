package com.EyEmilyKim.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import com.EyEmilyKim.config.AppConfig;
import com.EyEmilyKim.dto.response.LoginResponseDto;
import com.EyEmilyKim.interceptor.OperatingHoursInterceptor;
import com.EyEmilyKim.service.UserService;
import com.EyEmilyKim.util.MessageUtil;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private MessageUtil messageUtil;
	
	@MockBean
	private AppConfig appConfig;
	
	@MockBean
	private OperatingHoursInterceptor operatingHoursInterceptor;
	
	private MockHttpSession mockHttpSession;
	
	
	@BeforeEach
	public void setup() throws IOException {
		// 운영 시간 검증 무조건 true 반환 설정
		when(operatingHoursInterceptor.preHandle(
				any(HttpServletRequest.class),
				any(HttpServletResponse.class),
				any(Object.class))
			).thenReturn(true);
		// context-path 사전 설정
		when(appConfig.getContextPath()).thenReturn("/mab3");
	}
	
	
	/*-------- 홈 화면 --------*/
	
	@Test
	@DisplayName("루트 Get")
	void testGetRoot() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.index"));
	}
	
	@Test
	@DisplayName("홈 Get")
	void testGetIndex() throws Exception {
		mockMvc.perform(get("/index"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/"));
	}

	/*-------- 시간 외 홈 화면 --------*/
	
	@Test
	@DisplayName("시간외 홈 Get - 서버 리다이렉션")
	void testGetOutOfOpHours_byServer() throws Exception {
		// given
		// session 에 attribute 설정 => 서버에 의한 리다이렉션
		mockHttpSession = new MockHttpSession();
		mockHttpSession.setAttribute("redirectedFromInterceptor", true);
		
		// when & then
		mockMvc.perform(get("/outOfOpHours").session(mockHttpSession))
			.andExpect(status().isOk())
			.andExpect(view().name("root.outOfOpHours"));
		
		// session.invalidate() 확인
		assertThrows(IllegalStateException.class, () -> {
			mockHttpSession.getAttribute("USER_ID");
		});
		assertThrows(IllegalStateException.class, () -> {
			mockHttpSession.getAttribute("redirectedFromInterceptor");
		});
	}
	
	@Test
	@DisplayName("시간외 홈 Get - 사용자 직접 요청 > 홈")
	void testGetOutOfOpHours_byUser() throws Exception {
		// given
		// session 에 attribute 설정 없음 => 사용자가 직접 요청
		
		// when & then
		mockMvc.perform(get("/outOfOpHours"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"));
	}
	
	/*-------- 로그인 --------*/
	
	@Test
	@DisplayName("로그인 Get - 로그인 전")
	void testGetLogin_notLoggedIn() throws Exception {
		// given
		// 생성된 session 없음 => 로그아웃 상태
		
		// when & then
		mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.login"));
	}
	
	@Test
	@DisplayName("로그인 Get - 로그인 후 > 홈")
	void testGetLogin_alreadyLoggedIn() throws Exception {
		// given
		// session 에 user 정보 있음 => 로그인 상태
		mockHttpSession = new MockHttpSession();
		mockHttpSession.setAttribute("USER_ID", 1);
		
		// when & then
		mockMvc.perform(get("/login").session(mockHttpSession))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"));
	}
	
	@Test
	@DisplayName("로그인 Post - 성공 > 알림창")
	void testPostLogin_success() throws Exception {
		// given
		String userId = "test1";
		String password = "1test1";
		LoginResponseDto loginResponseDto = new LoginResponseDto(1, "테스트 유저 1", null);
		when(userService.login(userId, password)).thenReturn(loginResponseDto);
		
		when(messageUtil.getMessage("message-response", "msg.login.success")).thenReturn("로그인에 성공했습니다.");
		when(messageUtil.getMessage("message-response", "msg.login.welcome_pre")).thenReturn("반가워요,");
		when(messageUtil.getMessage("message-response", "msg.login.welcome_suf")).thenReturn("님~!");
		
		// when & then
		mockMvc.perform(post("/login")
				.param("LID", userId)
				.param("PWD", password))
			.andExpect(model().attribute("MSG", "로그인에 성공했습니다.\\n반가워요, 테스트 유저 1 님~!"))
			.andExpect(model().attribute("URL", "/mab3/"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.redirecting"));
	}
	
	@Test
	@DisplayName("로그인 Post - 사용자 없음 > 알림창")
	void testPostLogin_userNotFound() throws Exception {
		// given
		String userId = "nonExistingUser";
		String password = "wrongPassword";
		when(userService.login(userId, password))
			.thenThrow(new Exception("사용자를 찾을 수 없습니다."));

		// when & then
		mockMvc.perform(post("/login")
				.param("LID", userId)
				.param("PWD", password))
			.andExpect(model().attribute("MSG", "사용자를 찾을 수 없습니다."))
			.andExpect(model().attribute("URL", "/mab3/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.redirecting"));
	}
	
	@Test
	@DisplayName("로그인 Post - 비밀번호 불일치 > 알림창")
	void testPostLogin_wrongPassword() throws Exception {
		// given
		String userId = "existingUser";
		String password = "wrongPassword";
		when(userService.login(userId, password))
			.thenThrow(new Exception("비밀번호가 일치하지 않습니다."));
		
		// when & then
		mockMvc.perform(post("/login")
				.param("LID", userId)
				.param("PWD", password))
			.andExpect(model().attribute("MSG", "비밀번호가 일치하지 않습니다."))
			.andExpect(model().attribute("URL", "/mab3/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("root.redirecting"));
	}

	/*-------- 로그아웃 --------*/
	
	@Test
	@DisplayName("로그아웃 Get - 로그아웃 전")
	void testGetLogout_notLoggedOut() throws Exception {
		// given
		// session 에 user 정보 있음 => 로그인 상태
		mockHttpSession = new MockHttpSession();
		mockHttpSession.setAttribute("USER_ID", 1);
		
		// when & then
		mockMvc.perform(get("/logout")
				.session(mockHttpSession))
			.andExpect(status().isOk())
			.andExpect(view().name("root.redirecting"));
		
		// session.invalidate() 확인
		assertThrows(IllegalStateException.class, () -> {
			mockHttpSession.getAttribute("USER_ID");
		});
	}
	
	@Test
	@DisplayName("로그아웃 Get - 로그아웃 후 > 홈 ")
	void testGetLogout_alreadyLoggedOut() throws Exception {
		// given
		// 생성된 session 없음 => 로그아웃 상태
		
		// when & then
		mockMvc.perform(get("/logout"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/"));
	}
	
}
