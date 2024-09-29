package com.EyEmilyKim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class WantedUrlInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		HttpSession session = req.getSession();
		// 원래 요청 경로 session 에 저장
		String originalUrl = req.getRequestURI();
		String queryString = req.getQueryString();
		if (queryString != null) originalUrl += "?" + queryString;
		session.setAttribute("OriginalUrl", originalUrl);
		return true; // 다음 인터셉터 또는 컨트롤러로 요청 전달
	}

}
