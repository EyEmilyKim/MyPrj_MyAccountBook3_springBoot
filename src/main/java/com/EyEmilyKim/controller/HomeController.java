package com.EyEmilyKim.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.EyEmilyKim.dto.response.LoginResponseDto;
import com.EyEmilyKim.service.MessageService;
import com.EyEmilyKim.service.UserService;
import com.EyEmilyKim.util.LogUtil;

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
	private MessageService messageService;
	
	/*-------- 홈 화면 --------*/
	
	@Operation(summary = "메인 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	@GetMapping("/index")
	public String index() {
		LogUtil.printWithTimestamp("HomeController > index() called");
		
		return "root.index";
	}
	
	/*-------- 시간 외 홈 화면 --------*/
	
	@Operation(summary = "시간외 메인 페이지", description = "서비스 운영시간 외에 로그인이 필요한 메뉴 접근 시 표시")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	@GetMapping("/outOfOpHours")
	public String outOfOpHours() {
		LogUtil.printWithTimestamp("HomeController > outOfOpHours() called");
		
		return "root.outOfOpHours";
	}
	
	/*-------- 로그인 --------*/
	
	@Operation(summary = "로그인 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	@GetMapping("/login")
	public String login() {
		LogUtil.printWithTimestamp("HomeController > login()@Get called");
		
		return "root.login";
	}
	
	@Operation(summary = "로그인 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "로그인 성공 알림, \n\n리다이렉트: 메인 페이지"),
			@ApiResponse(responseCode = "401", description = "로그인 실패 알림, \n\n리다이렉트: 로그인 GET 페이지")
	})
	@PostMapping("/login")
	public String login(
			@Parameter(name = "LID", description = "로그인 ID", example = "test1", required = true)
			String LID, 
			@Parameter(name = "PWD", description = "비밀번호", example = "1test1", required = true)
			String PWD, 
			Model model, HttpSession session) {
		LogUtil.printWithTimestamp("HomeController > login()@Post called");
		try {
			LoginResponseDto loginResponseDto = userService.login(LID, PWD);
			session.setAttribute("USER_ID", loginResponseDto.getUser_id());
			session.setAttribute("NICKNAME", loginResponseDto.getNickname());
			String msg = messageService.getMessage("message-response", "msg.login.success")
								+ "\\n" + messageService.getMessage("message-response", "msg.login.welcome_pre")
								+ " " + loginResponseDto.getNickname()
								+ " " + messageService.getMessage("message-response", "msg.login.welcome_suf");
			model.addAttribute("MSG", msg);
			
			String originalUrl = (String) session.getAttribute("OriginalUrl");
			if (originalUrl == null) originalUrl = "/index";
			model.addAttribute("URL", originalUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", e.getMessage());
			model.addAttribute("URL", "/login");
		}
		return "redirect";
	}
	
	/*-------- 로그아웃 --------*/
	
	@Operation(summary = "로그아웃 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "로그아웃 성공 알림, \n\n리다이렉트: 메인 페이지")
	@GetMapping("logout")
	public String logout(HttpSession session, Model model) {
		LogUtil.printWithTimestamp("HomeController > logout()@Get called");
		String user_id = (String) session.getAttribute("NICKNAME");
		String msg = messageService.getMessage("message-response", "msg.logout.success")
				+ "\\n" + messageService.getMessage("message-response", "msg.logout.farewell_pre")
				+ " " + user_id
				+ " " + messageService.getMessage("message-response", "msg.logout.farewell_suf");
		model.addAttribute("MSG", msg);
		model.addAttribute("URL", "/index");
		session.invalidate();
		return "redirect";
	} 
}
