package com.EyEmilyKim.controller.specification;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="[2] view - 홈", description = "루트 페이지 그룹")
public interface HomeControllerSpecification {
	
	/*-------- 홈 화면 --------*/

	@Operation(summary = "메인 페이지", description = "어플리케이션 메인 페이지")
	@ApiResponse(responseCode = "200", description = "정상적으로 메인 페이지 반환됨")
	String get_home();

	@Operation(summary = "→ 메인 페이지", description = "어플리케이션 메인페이지 '/' 로 리다이렉트")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_index();

	/*-------- 시간 외 홈 화면 --------*/
	
	@Operation(summary = "시간외 메인 페이지", description = "서비스 운영시간 외에 로그인이 필요한 메뉴 접근 시 시간외 메인 화면 반환\n\n사용자의 직접적인 url 접근은 '/' 로 리다이렉트")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_outOfOpHours(HttpSession session);

	/*-------- 로그인 --------*/
	
	@Operation(summary = "로그인 페이지", description = "로그인 하지 않은 상태로 요청 시 로그인 화면 반환\n\n로그인 한 상태로 요청 시 '/' 로 리다이렉트")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_login(HttpSession session);

	@Operation(summary = "로그인 페이지", description = "사용자가 login_id, password 입력하여 로그인 요청")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "로그인 성공 알림, \n\n리다이렉트 : 메인 페이지"),
		@ApiResponse(responseCode = "401", description = "로그인 실패 알림, \n\n리다이렉트 : 로그인 GET 페이지")
	})
	String post_login(
			@Parameter(name = "LID", description = "로그인 ID", example = "test1", required = true)
			String LID, 
			@Parameter(name = "PWD", description = "비밀번호", example = "1test1", required = true)
			String PWD, 
			Model model, HttpSession session
		);

	/*-------- 로그아웃 --------*/
	
	@Operation(summary = "로그아웃 페이지", description = "로그아웃 하지 않은 상태로 요청 시 로그아웃 처리\n\n로그아웃 한 상태로 요청 시 '/' 로 리다이렉트")
	@ApiResponse(responseCode = "200", description = "로그아웃 성공 알림, \n\n리다이렉트 : 메인 페이지")
	String get_logout(HttpSession session, Model model);

}
