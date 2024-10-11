package com.EyEmilyKim.interceptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

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
import com.EyEmilyKim.config.properties.OperatingHoursProperties;

public class OperatingHoursInterceptorUnitTest {

	@InjectMocks
	private OperatingHoursInterceptor operatingHoursInterceptor;

	@Mock
	private OperatingHoursProperties operatingHoursProps;

	@Mock
	private AppConfig appConfig;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private HttpSession session;

	private Clock fixedClock;

  
	@BeforeEach
	public void setUp() {
  	// given common
  	MockitoAnnotations.openMocks(this);
  	when(request.getSession(true)).thenReturn(session);
  	when(appConfig.getContextPath()).thenReturn("/mab3");
  	when(operatingHoursProps.getStart()).thenReturn("08:00");
  	when(operatingHoursProps.getEnd()).thenReturn("20:00");
	}

  
	@Test
	@DisplayName("0800~2000/AM 07:00 - false 반환 > 시간외 홈")
	public void testPreHandle_OutsideOperatingHours_AM0700() throws Exception {
  	// given : AM 7시를 Mock 시간으로 설정 (KST 기준)
  	fixedClock = Clock.fixed(Instant.parse("2023-10-02T07:00:00+09:00"), ZoneId.of("Asia/Seoul"));
  	operatingHoursInterceptor.setClock(fixedClock);

  	// when
  	boolean result = operatingHoursInterceptor.preHandle(request, response, new Object());

  	// then
  	assertFalse(result);
  	verify(request).getSession(true); // 세션 새로 생성
  	verify(session).setAttribute("redirectedFromInterceptor", true);
  	verify(response).sendRedirect("/mab3/outOfOpHours");
	}

	@Test
	@DisplayName("0800~2000/PM 03:00 - true 반환")
	public void testPreHandle_InsideOperatingHours__PM0300() throws Exception {
  	// given : PM 3시를 Mock 시간으로 설정 (KST 기준)
  	fixedClock = Clock.fixed(Instant.parse("2023-10-02T15:00:00+09:00"), ZoneId.of("Asia/Seoul"));
  	operatingHoursInterceptor.setClock(fixedClock);

  	// when
  	boolean result = operatingHoursInterceptor.preHandle(request, response, new Object());

  	// then
  	assertTrue(result);
  	verify(response, never()).sendRedirect(anyString());
	}
  
	@Test
	@DisplayName("0800~2000/PM 07:59 - true 반환")
	public void testPreHandle_InsideOperatingHours_PM0759() throws Exception {
  	// given : PM 07:59를 Mock 시간으로 설정 (KST 기준)
  	fixedClock = Clock.fixed(Instant.parse("2023-10-02T19:59:00+09:00"), ZoneId.of("Asia/Seoul"));
  	operatingHoursInterceptor.setClock(fixedClock);
  	
  	// when
  	boolean result = operatingHoursInterceptor.preHandle(request, response, new Object());
  	
  	// then
  	assertTrue(result);
  	verify(response, never()).sendRedirect(anyString());
	}
  
	@Test
	@DisplayName("0800~2000/PM 08:01 - false 반환 > 시간외 홈")
	public void testPreHandle_OutsideOperatingHours_PM0900() throws Exception {
  	// given : PM 08:01를 Mock 시간으로 설정 (KST 기준)
  	fixedClock = Clock.fixed(Instant.parse("2023-10-02T20:01:00+09:00"), ZoneId.of("Asia/Seoul"));
  	operatingHoursInterceptor.setClock(fixedClock);

  	// when
  	boolean result = operatingHoursInterceptor.preHandle(request, response, new Object());

  	// then
  	assertFalse(result);
  	verify(request).getSession(true); // 세션 새로 생성
  	verify(session).setAttribute("redirectedFromInterceptor", true);
  	verify(response).sendRedirect("/mab3/outOfOpHours");
	}

}
