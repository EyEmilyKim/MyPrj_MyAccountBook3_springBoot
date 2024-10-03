package com.EyEmilyKim.interceptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.EyEmilyKim.config.AppConfig;

public class LoginInterceptorImp_NOTdevTest {

	@InjectMocks
	private LoginInterceptorImp_NOTdev loginInterceptor;

	@Mock
	private AppConfig appConfig;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private HttpSession session;

	@BeforeEach
	public void setUp() {
		// given common
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		when(appConfig.getContextPath()).thenReturn("/mab3");
	}

	@Test
	@DisplayName("로그인 안했을 때 - false 반환 > 로그인 창")
	public void testPreHandle_UserNotLoggedIn() throws Exception {
		// given
		when(session.getAttribute("USER_ID")).thenReturn(null);
		// when
		boolean result = loginInterceptor.preHandle(request, response, new Object());
		// then
		assertFalse(result);
		verify(response).sendRedirect("/mab3/login");
  }

	@Test
	@DisplayName("로그인 했을 때 - true 반환")
	public void testPreHandle_UserLoggedIn() throws Exception {
		// given
		when(session.getAttribute("USER_ID")).thenReturn(1);
		// when
		boolean result = loginInterceptor.preHandle(request, response, new Object());
		// then
		assertTrue(result);
		verify(response, never()).sendRedirect(anyString());
	}
    
}
