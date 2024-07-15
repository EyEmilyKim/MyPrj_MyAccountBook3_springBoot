package com.EyEmilyKim.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
	private static final String ERROR_PATH = "/error";
	
	@GetMapping(ERROR_PATH)
	public String handleError(HttpServletRequest req, Model model) {
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String referer = req.getHeader("Referer");
		String nextUrl = (referer != null) ? referer : "/index";
		String failMsg;
		
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if (statusCode == 404) {
				failMsg = "존재하지 않는 페이지입니다.";
			} else if (statusCode == 500) {
				failMsg = "서버 내부 오류가 발생했습니다.";
			} else {
				failMsg = "알 수 없는 오류가 발생했습니다.";
			}
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", nextUrl);
		}
		
		return "redirect";
	}
	
}
