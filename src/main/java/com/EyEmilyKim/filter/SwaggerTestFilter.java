package com.EyEmilyKim.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!dev")
public class SwaggerTestFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response; 
		
		// Swagger-UI 에서 오는 POST 요청만 필터링
		if(isSwaggerRequest(req) && "POST".equalsIgnoreCase(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request 상태 코드 설정
			res.setContentType("text/plain; charset=UTF-8"); // 응답 타입 설정
			res.getWriter().write("Prod 환경에서 Swagger-UI를 통한 POST 요청은 테스트할 수 없습니다."); // 응답 메시지 설정
			res.getWriter().flush();
			return; // 필터 체인을 더 이상 타지 않고 바로 응답
		}
		
		// GET 이나 Swagger가 아닌 요청은 통과시킴
		chain.doFilter(req, res);
		
	}
	
	// Swagger-UI 요청 URI 확인
	private boolean isSwaggerRequest(HttpServletRequest req) {
		String referer = req.getHeader("Referer");
		return (referer != null && referer.contains("/swagger-ui"));
	}

}
