package com.EyEmilyKim.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EyEmilyKim.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("HomeController > index() called");
		
		return "root.index";
	}
	
	@GetMapping("/login")
	public String login() {
		System.out.println("HomeController > login()@Get called");
		
		return "root.login";
	}
	@PostMapping("/login")
	public String login(String ID, String PWD, Model model, HttpSession session) {
		System.out.println("HomeController > login()@Post called");
		
		String msg;
		String url;
		
		int result = userService.login(ID, PWD);
		System.out.println("result : "+result);
		if(result == 1) {
			msg = "로그인에 성공했습니다. \\n환영합니다~ "+ID+"님~!";
			url = "index";
			session.setAttribute("USER_ID", ID);
		}else {
			msg = "로그인에 실패했습니다. \\n계정 또는 비밀번호를 확인해주세요...";
			url = "login";
		}
		model.addAttribute("MSG", msg);
		model.addAttribute("URL", url);
		System.out.println("msg : "+msg);
		System.out.println("url : "+url);
		return "redirect";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("MSG", "정상적으로 로그아웃되었습니다.");
		model.addAttribute("URL", "index");
		return "redirect";
	} 
}
