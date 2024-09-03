package com.EyEmilyKim.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.EyEmilyKim.dto.UserSessionDto;
import com.EyEmilyKim.service.UserService;
import com.EyEmilyKim.util.LogUtil;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	/*-------- 홈 화면 --------*/
	
	@GetMapping("/index")
	public String index() {
		LogUtil.printWithTimestamp("HomeController > index() called");
		
		return "root.index";
	}
	
	/*-------- 시간 외 홈 화면 --------*/
	
	@GetMapping("/outOfOpHours")
	public String outOfOpHours() {
		LogUtil.printWithTimestamp("HomeController > outOfOpHours() called");
		
		return "root.outOfOpHours";
	}
	
	/*-------- 월별 계획 --------*/
	
	@GetMapping("/plan")
	public String plan() {
		LogUtil.printWithTimestamp("HomeController > plan() called");
		
		return "root.comingSoon";
	}
	
	/*-------- 로그인 --------*/
	
	@GetMapping("/login")
	public String login() {
		LogUtil.printWithTimestamp("HomeController > login()@Get called");
		
		return "root.login";
	}
	
	@PostMapping("/login")
	public String login(String LID, String PWD, Model model, HttpSession session) {
		LogUtil.printWithTimestamp("HomeController > login()@Post called");
		try {
			UserSessionDto userSess = userService.login(LID, PWD);
			session.setAttribute("USER_ID", userSess.getUser_id());
			session.setAttribute("NICKNAME", userSess.getNickname());
			String msg = "로그인에 성공했습니다. \\n환영합니다~ " + userSess.getNickname() + "님~!";
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
	
	@GetMapping("logout")
	public String logout(HttpSession session, Model model) {
		LogUtil.printWithTimestamp("HomeController > logout()@Get called");
		session.invalidate();
		model.addAttribute("MSG", "정상적으로 로그아웃되었습니다.");
		model.addAttribute("URL", "/index");
		return "redirect";
	} 
}
