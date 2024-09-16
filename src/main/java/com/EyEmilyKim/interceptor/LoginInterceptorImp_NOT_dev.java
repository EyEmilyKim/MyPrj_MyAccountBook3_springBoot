package com.EyEmilyKim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!dev")
public class LoginInterceptorImp_NOT_dev implements LoginInterceptor {
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		HttpSession session = req.getSession();
		Integer userId = (Integer) req.getAttribute("userId");
		if (userId == null) { 
			// 원래 요청 경로 세션에 저장
			String originalUrl = req.getRequestURI();
			String queryString = req.getQueryString();
			if (queryString != null) originalUrl += "?" + queryString;
			session.setAttribute("OriginalUrl", originalUrl);
			// 로그인 화면으로 리다이렉션
			res.sendRedirect("/login");
			return false; // 요청을 중단하고 리다이렉션 수행
		}
		return true; // 다음 인터셉터 또는 컨트롤러로 요청 전달
	}

}
