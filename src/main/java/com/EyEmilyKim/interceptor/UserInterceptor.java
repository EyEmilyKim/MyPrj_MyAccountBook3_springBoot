package com.EyEmilyKim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class UserInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("USER_ID");
		if(userId == null || userId == "") userId = "master"; 
		req.setAttribute("userId", userId);
		System.out.println("userId : "+userId);
		return true; // 다음 인터셉터 또는 컨트롤러로 요청 전달
	}
}
