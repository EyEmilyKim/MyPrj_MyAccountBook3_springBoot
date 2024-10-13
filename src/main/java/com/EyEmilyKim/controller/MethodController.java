package com.EyEmilyKim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EyEmilyKim.config.AppConfig;
import com.EyEmilyKim.controller.specification.MethodControllerSpecification;
import com.EyEmilyKim.dto.request.method.MethodCreateRequestDto;
import com.EyEmilyKim.dto.request.method.MethodUpdateRequestDto;
import com.EyEmilyKim.entity.Method;
import com.EyEmilyKim.service.MethodService;
import com.EyEmilyKim.util.DtoUtil;
import com.EyEmilyKim.util.LogUtil;
import com.EyEmilyKim.util.MessageUtil;

@RequestMapping("/set/method/")
@Controller
public class MethodController implements MethodControllerSpecification {

	@Autowired
	private MethodService methodService;
	
	@Autowired
	private MessageUtil messageUtil;	
	
	@Autowired
	private AppConfig appConfig;
	
	private String nextUrl = "/set/method/list";
	
	
	/*-------- 결제수단 목록 --------*/ 
	
	@Override
	@GetMapping("list")
	public String get_list(HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("MethodController > get_list() called");
		int userId = (int) req.getAttribute("userId"); 
		System.out.println("userId : "+userId);
		
		List<Method> list = methodService.getList(userId);
		model.addAttribute("LIST", list);
		int cnt = methodService.getCount(userId);
		model.addAttribute("COUNT", cnt);
		
		return "set.method.list";
	}
	

	/*-------- 결제수단 삭제 --------*/
	
	@Override
	@GetMapping("del")
	public String get_del(String MCODE, Model model) {
		
		LogUtil.printWithTimestamp("MethodController > get_del() called");
		System.out.println(MCODE);
		
		Method meth= methodService.getOne(MCODE);
		System.out.println(meth.getMeth_code()+" - "+meth.getMeth_name());
		model.addAttribute("M", meth);
		
		return "set.method.del";
	}
	
	
	@Override
	@PostMapping("del")
	public String post_del(String MCODE, Model model) {
		
		LogUtil.printWithTimestamp("MethodController > post_del() called");
		System.out.println(MCODE);
		
		try {
			methodService.delete(MCODE);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		}
		
		return "root.redirecting";
	}
	
	
	/*-------- 결제수단 추가 --------*/

	@Override
	@GetMapping("crt")
	public String get_crt(HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("MethodController > get_crt() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		
		List<String> list = methodService.getNameList(userId);
		model.addAttribute("LIST", list);
		int maxSqn = methodService.getMaxSqn();
		System.out.println("maxSqn : "+maxSqn);
		model.addAttribute("MSN", maxSqn);
		
		return "set.method.crt";
	}
	
	
	@Override
	@PostMapping("crt")
	public String post_crt(@ModelAttribute MethodCreateRequestDto methodCreateRequestDto, HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("MethodController > post_crt() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printFieldValues(methodCreateRequestDto);
		
		try {
			methodService.insert(methodCreateRequestDto, userId);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		}
		
		return "root.redirecting";
	}
	
	
	/*-------- 결제수단 수정 --------*/ 
	
	@Override
	@GetMapping("upd")
	public String get_upd(String MCODE, HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("MethodController > get_upd() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		System.out.println(MCODE);
		
		Method meth = methodService.getOne(MCODE);
		System.out.println(meth.getMeth_code()+" - "+meth.getMeth_name());
		model.addAttribute("M", meth);
		List<String> list = methodService.getNameList(userId);
		model.addAttribute("LIST", list);
		
		return "set.method.upd";
	}
	
	
	@Override
	@PostMapping("upd")
	public String post_upd(@ModelAttribute MethodUpdateRequestDto methodUpdateRequestDto, Model model) {
		
		LogUtil.printWithTimestamp("MethodController > post_upd() called");
		DtoUtil.printFieldValues(methodUpdateRequestDto);
		
		try {
			methodService.update(methodUpdateRequestDto);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		} catch (Exception e) {
			 e.printStackTrace();
			 model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			 model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		}
		
		return "root.redirecting";
	}
	
}
