package com.EyEmilyKim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class UserInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		HttpSession session = req.getSession();
		int userId = (int) session.getAttribute("USER_ID");
		req.setAttribute("userId", userId);
		return true; // 다음 인터셉터 또는 컨트롤러로 요청 전달
	}
}
