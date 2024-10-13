package com.EyEmilyKim.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.EyEmilyKim.config.AppConfig;
import com.EyEmilyKim.util.MessageUtil;

@Controller
public class CustomErrorController implements ErrorController {
	
	private static final String ERROR_PATH = "/error";
	
	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private AppConfig appConfig;
	
	
	@GetMapping(ERROR_PATH)
	public String handleError(HttpServletRequest req, Model model) {
		
		// 사용자의 직접적인 ERROR_PATH 접근 차단 
		if(req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) == null) {
			return "redirect:/";
		}
		
		// 에러 처리 로직
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String referer = req.getHeader("Referer");
		String nextUrl = (referer != null) ? referer : appConfig.getContextPath();
		String errorMessage;
		
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if (statusCode == 404) {
				errorMessage = messageUtil.getMessage("message-error", "msg.error.404");
			} else if (statusCode == 500) {
				errorMessage = messageUtil.getMessage("message-error", "msg.error.500");
			} else {
				errorMessage = messageUtil.getMessage("message-error", "msg.error.else");
			}
			model.addAttribute("MSG", errorMessage);
			model.addAttribute("URL", nextUrl);
		}
		
		return "root.redirecting";
	}
	
}
