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
		// session 에서 USER_ID 찾아서 request 에 담기
		HttpSession session = req.getSession();
		Integer userId = (Integer) session.getAttribute("USER_ID");
		req.setAttribute("userId", userId);
		// 로그인 안했으면 → 로그인 화면으로 리다이렉트, 요청 중단.
		if (userId == null) { 
			res.sendRedirect("/login");
			return false;
		}
		return true; // 다음 인터셉터 또는 컨트롤러로 요청 전달
	}

}
