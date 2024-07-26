package com.EyEmilyKim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class LoginInterceptor_prod implements LoginInterceptor {
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		Integer userId = (Integer) req.getAttribute("userId");
		if (userId == null) { 
			res.sendRedirect("/login");
			return false; // 요청을 중단하고 리다이렉션 수행
		}
		return true; // 다음 인터셉터 또는 컨트롤러로 요청 전달
	}

}
