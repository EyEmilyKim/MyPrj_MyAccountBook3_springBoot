package com.EyEmilyKim.interceptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.EyEmilyKim.config.properties.ApiProperties;

public class LoginInterceptorImp_NOTdevTest {

    @InjectMocks
    private LoginInterceptorImp_NOTdev loginInterceptor;

    @Mock
    private ApiProperties apiProps;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    @DisplayName("로그인 안했을 때 - false 반환 > 로그인 창")
  	public void testPreHandle_UserNotLoggedIn() throws Exception {
        when(session.getAttribute("USER_ID")).thenReturn(null);
        when(apiProps.getContext_path()).thenReturn("/mab3");

        boolean result = loginInterceptor.preHandle(request, response, new Object());

        assertFalse(result);
        verify(response).sendRedirect("/mab3/login");
    }

    @Test
    @DisplayName("로그인 했을 때 - true 반환")
  	public void testPreHandle_UserLoggedIn() throws Exception {
        when(session.getAttribute("USER_ID")).thenReturn(1);
        when(apiProps.getContext_path()).thenReturn("/mab3");

        boolean result = loginInterceptor.preHandle(request, response, new Object());

        assertTrue(result);
        verify(response, never()).sendRedirect(anyString());
    }
}
