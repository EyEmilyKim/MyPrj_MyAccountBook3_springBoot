package com.EyEmilyKim.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.EyEmilyKim.config.AppConfig;
import com.EyEmilyKim.controller.specification.HomeControllerSpecification;
import com.EyEmilyKim.dto.response.LoginResponseDto;
import com.EyEmilyKim.service.UserService;
import com.EyEmilyKim.util.LogUtil;
import com.EyEmilyKim.util.MessageUtil;

@Controller
public class HomeController implements HomeControllerSpecification {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private AppConfig appConfig;
	
	
	/*-------- 홈 화면 --------*/
	
	@Override
	@GetMapping("/")
	public String get_home() {
		LogUtil.printWithTimestamp("HomeController > get_home() called");
		
		return "root.index";
	}
	
	@Override
	@GetMapping("/index")
	public String get_index() {
		LogUtil.printWithTimestamp("HomeController > get_index() called");
		
		return "redirect:/";
	}
	
	/*-------- 시간 외 홈 화면 --------*/
	
	@Override
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
	
	@Override
	@GetMapping("/login")
	public String get_login(HttpSession session) {
		LogUtil.printWithTimestamp("HomeController > get_login() called");
		
		// 이미 로그인한 상태로 요청 시 홈으로 이동
		Integer userId = (Integer) session.getAttribute("USER_ID");
		System.out.println("HomeCont.get_login() userId : " + userId);
		if(userId != null) return "redirect:/"; 
			
		return "root.login";
	}
	
	@Override
	@PostMapping("/login")
	public String post_login(String LID, String PWD, Model model, HttpSession session) {
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
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", e.getMessage());
			model.addAttribute("URL", appConfig.getContextPath() + "/login");
		}
		
		return "redirect";
	}
	
	/*-------- 로그아웃 --------*/
	
	@Override
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
