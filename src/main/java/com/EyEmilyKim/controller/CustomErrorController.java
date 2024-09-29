package com.EyEmilyKim.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.EyEmilyKim.service.MessageService;

@Controller
public class CustomErrorController implements ErrorController {
	
	private static final String ERROR_PATH = "/error";
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping(ERROR_PATH)
	public String handleError(HttpServletRequest req, Model model) {
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String referer = req.getHeader("Referer");
		String nextUrl = (referer != null) ? referer : "/";
		String errorMessage;
		
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if (statusCode == 404) {
				errorMessage = messageService.getMessage("message-error", "msg.error.404");
			} else if (statusCode == 500) {
				errorMessage = messageService.getMessage("message-error", "msg.error.500");
			} else {
				errorMessage = messageService.getMessage("message-error", "msg.error.else");
			}
			model.addAttribute("MSG", errorMessage);
			model.addAttribute("URL", nextUrl);
		}
		
		return "redirect";
	}
	
}
