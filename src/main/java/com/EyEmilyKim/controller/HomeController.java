package com.EyEmilyKim.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.EyEmilyKim.config.AppConfig;
import com.EyEmilyKim.dto.response.LoginResponseDto;
import com.EyEmilyKim.service.UserService;
import com.EyEmilyKim.util.LogUtil;
import com.EyEmilyKim.util.MessageUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name="view - 홈", description = "루트 페이지 그룹")
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private AppConfig appConfig;
	
	/*-------- 홈 화면 --------*/
	
	@Operation(summary = "루트 URL → 메인 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 메인 페이지가 반환됨")
	@GetMapping("/")
	public String get_home() {
		LogUtil.printWithTimestamp("HomeController > get_home() called");
		
		return "root.index";
	}
	
	@Operation(summary = "메인 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	@GetMapping("/index")
	public String get_index() {
		LogUtil.printWithTimestamp("HomeController > get_index() called");
		
		return "root.index";
	}
	
	/*-------- 시간 외 홈 화면 --------*/
	
	@Operation(summary = "시간외 메인 페이지", description = "서비스 운영시간 외에 로그인이 필요한 메뉴 접근 시 표시\n\n사용자의 직접적인 url 접근은 차단")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	@GetMapping("/outOfOpHours")
	public String get_outOfOpHours(HttpSession session) {
		LogUtil.printWithTimestamp("HomeController > get_outOfOpHours() called");
		
		// 사용자 직접 요청 방지
		Boolean redirectedFromInterceptor = (Boolean) session.getAttribute("redirectedFromInterceptor");
		if (redirectedFromInterceptor == null || !redirectedFromInterceptor) {
			return "redirect:/";
		}
		
		// 세션 종료
		if (session != null) {
			session.invalidate();
		}
		
		return "root.outOfOpHours";
	}
	
	/*-------- 로그인 --------*/
	
	@Operation(summary = "로그인 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	@GetMapping("/login")
	public String get_login(HttpSession session) {
		LogUtil.printWithTimestamp("HomeController > get_login() called");
		
		// 이미 로그인한 상태로 요청 시 홈으로 이동
		Integer userId = (Integer) session.getAttribute("USER_ID");
		System.out.println("HomeCont.get_login() userId : " + userId);
		if(userId != null) return "redirect:/"; 
			
		return "root.login";
	}
	
	@Operation(summary = "로그인 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "로그인 성공 알림, \n\n리다이렉트: 메인 페이지"),
			@ApiResponse(responseCode = "401", description = "로그인 실패 알림, \n\n리다이렉트: 로그인 GET 페이지")
	})
	@PostMapping("/login")
	public String post_login(
			@Parameter(name = "LID", description = "로그인 ID", example = "test1", required = true)
			String LID, 
			@Parameter(name = "PWD", description = "비밀번호", example = "1test1", required = true)
			String PWD, 
			Model model, HttpSession session) {
		LogUtil.printWithTimestamp("HomeController > post_login() called");
		try {
			LoginResponseDto loginResponseDto = userService.login(LID, PWD);
			session.setAttribute("USER_ID", loginResponseDto.getUser_id());
			session.setAttribute("NICKNAME", loginResponseDto.getNickname());
			String msg = messageUtil.getMessage("message-response", "msg.login.success")
								+ "\\n" + messageUtil.getMessage("message-response", "msg.login.welcome_pre")
								+ " " + loginResponseDto.getNickname()
								+ " " + messageUtil.getMessage("message-response", "msg.login.welcome_suf");
			model.addAttribute("MSG", msg);
			
			String originalUrl = (String) session.getAttribute("OriginalUrl");
			if (originalUrl == null) originalUrl = appConfig.getContextPath() + "/";
			model.addAttribute("URL", originalUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", e.getMessage());
			model.addAttribute("URL", appConfig.getContextPath() + "/login");
		}
		return "redirect";
	}
	
	/*-------- 로그아웃 --------*/
	
	@Operation(summary = "로그아웃 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "로그아웃 성공 알림, \n\n리다이렉트: 메인 페이지")
	@GetMapping("logout")
	public String get_logout(HttpSession session, Model model) {
		LogUtil.printWithTimestamp("HomeController > get_logout() called");
		
		// 이미 로그아웃한 상태로 요청 시 홈으로 이동
		Integer userId = (Integer) session.getAttribute("USER_ID");
		System.out.println("HomeCont.get_logout() userId : " + userId);
		if(userId == null) return "redirect:/"; 
		
		String user_id = (String) session.getAttribute("NICKNAME");
		String msg = messageUtil.getMessage("message-response", "msg.logout.success")
				+ "\\n" + messageUtil.getMessage("message-response", "msg.logout.farewell_pre")
				+ " " + user_id
				+ " " + messageUtil.getMessage("message-response", "msg.logout.farewell_suf");
		model.addAttribute("MSG", msg);
		model.addAttribute("URL", appConfig.getContextPath() + "/");
		session.invalidate();
		return "redirect";
	} 
}
