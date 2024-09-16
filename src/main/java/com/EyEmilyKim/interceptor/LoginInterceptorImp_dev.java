package com.EyEmilyKim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class LoginInterceptorImp_dev implements LoginInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		Integer userId = (Integer) req.getAttribute("userId");
		// 개발용 임시!! 로그인 안했으면 master로 간주
		if(userId == null) userId = 1;
		req.setAttribute("userId", userId);
		return true; // 다음 인터셉터 또는 컨트롤러로 요청 전달
	}

}
