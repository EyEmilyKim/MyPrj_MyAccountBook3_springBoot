package com.EyEmilyKim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class LoginInterceptorImp_dev implements LoginInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		// session 에서 USER_ID 찾아서
		HttpSession session = req.getSession();
		Integer userId = (Integer) session.getAttribute("USER_ID");
		// 로그인 안했으면 master로 간주 **개발 환경 ONLY**
		if(userId == null) userId = 1;
		// request 에 담기
		req.setAttribute("userId", userId);
		return true; // 다음 인터셉터 또는 컨트롤러로 요청 전달
	}

}
