package com.EyEmilyKim.interceptor;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.EyEmilyKim.config.properties.ApiProperties;
import com.EyEmilyKim.config.properties.OperatingHoursProperties;

@Component
public class OperatingHoursInterceptor implements HandlerInterceptor {

	@Autowired
	private OperatingHoursProperties config;
	
	@Autowired
	private ApiProperties apiProps;
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws IOException {
		LocalTime currentTime = LocalTime.now(); // 현재 서버 시간
		
		String start = config.getStart();
		String end = config.getEnd();

		// 운영 시간 설정이 없으면 24시간 운영으로 간주
		if (start == null || end == null || (start.equals("00:00") && end.equals("00:00"))) {
			return true;
		}

		LocalTime startTime = LocalTime.parse(start);
		LocalTime endTime = LocalTime.parse(end);
    
		// 운영시간 외일 경우
		if(currentTime.isBefore(startTime) || currentTime.isAfter(endTime)) {
			HttpSession session = req.getSession(false);
			if(session != null) {
				session.invalidate(); // 세션 종료
			}
			
			// 리다이렉트 및 운영시간 안내
			res.sendRedirect(apiProps.getContext_path() + "/outOfOpHours");
			return false; // 요청 처리 중단
		}
		
		return true; // 정상적으로 요청 처리 계속 진행
	}
}
