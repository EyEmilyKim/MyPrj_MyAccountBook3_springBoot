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
import com.EyEmilyKim.dto.request.method.MethodCreateRequestDto;
import com.EyEmilyKim.dto.request.method.MethodUpdateRequestDto;
import com.EyEmilyKim.entity.Method;
import com.EyEmilyKim.service.MethodService;
import com.EyEmilyKim.util.DtoUtil;
import com.EyEmilyKim.util.LogUtil;
import com.EyEmilyKim.util.MessageUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/set/method/")
@Controller
@Tag(name="view - 결제수단", description = "결제수단 페이지 그룹")
public class MethodController {

	@Autowired
	private MethodService methodService;
	
	@Autowired
	private MessageUtil messageUtil;	
	
	@Autowired
	private AppConfig appConfig;
	
	private String nextUrl = "/set/method/list";
	
	
	/*-------- 결제수단 목록 --------*/ 
	
	@GetMapping("list")
	@Operation(summary = "목록 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
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
	
	@GetMapping("del")
	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_del(
			@Parameter(name = "MCODE", description = "결제수단 코드", example = "", required = true)
			String MCODE, 
			Model model) {
		
		LogUtil.printWithTimestamp("MethodController > get_del() called");
		System.out.println(MCODE);
		
		Method meth= methodService.getOne(MCODE);
		System.out.println(meth.getMeth_code()+" - "+meth.getMeth_name());
		model.addAttribute("M", meth);
		
		return "set.method.del";
		
	}
	
	
	@PostMapping("del")
	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "삭제 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "삭제 실패 알림, \n\n리다이렉트: 목록 페이지")
	})
	public String post_del(
			@Parameter(name = "MCODE", description = "결제수단 코드", example = "", required = true)
			String MCODE,
			@Parameter(name = "MNAME", description = "결제수단 이름 ** to_be_refactored **", example = "", required = true)
			String MNAME,
			Model model
			) {
		
		LogUtil.printWithTimestamp("MethodController > post_del() called");
		System.out.println(MCODE+" - "+MNAME);
		
		try {
			methodService.delete(MCODE);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		}
		
		return "redirect";
		
	}
	
	
	/*-------- 결제수단 추가 --------*/

	@GetMapping("crt")
	@Operation(summary = "추가 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
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
	
	
	@PostMapping("crt")
	@Operation(summary = "추가 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "추가 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "추가 실패 알림, \n\n리다이렉트: 목록 페이지")
	})	
	public String post_crt(
			@ModelAttribute MethodCreateRequestDto methodCreateRequestDto, 
			HttpServletRequest req, Model model) {
		
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
		
		return "redirect";
		
	}
	
	
	/*-------- 결제수단 수정 --------*/ 
	
	@GetMapping("upd")
	@Operation(summary = "수정 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_upd(
			@Parameter(name = "MCODE", description = "결제수단 코드", example = "", required = true)
			String MCODE, 
			HttpServletRequest req, Model model) {
		
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
	
	
	@PostMapping("upd")
	@Operation(summary = "수정 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "수정 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "수정 실패 알림, \n\n리다이렉트: 목록 페이지")
	})
	public String post_upd(
			@ModelAttribute MethodUpdateRequestDto methodUpdateRequestDto, 
			Model model) {
		
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
		
		return "redirect";
		
	}
	
}
