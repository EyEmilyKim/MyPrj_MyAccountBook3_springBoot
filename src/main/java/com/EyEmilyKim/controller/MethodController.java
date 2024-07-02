package com.EyEmilyKim.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EyEmilyKim.entity.Method;
import com.EyEmilyKim.service.MethodService;

@RequestMapping("/set/method/")
@Controller
public class MethodController {

	@Autowired
	private MethodService methodService;
	
	private String failMsg = "해당 기능이 준비되지 않아 처리하지 못했습니다.";
	private String succMsg = "정상적으로 처리되었습니다.";
	private String nextUrl = "/set/method/list";
	
	
	/*-------- 결제수단 목록 --------*/ 
	
	@GetMapping("list")
	public String list(HttpServletRequest req, Model model) {
		System.out.println("MethodController > list() called");
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
	public String del(String MCODE, Model model) {
		System.out.println("MethodController > del()@Get called");
		System.out.println(MCODE);
		
		Method meth= methodService.getOne(MCODE);
		System.out.println(meth.getMeth_code()+" - "+meth.getMeth_name());
		model.addAttribute("M", meth);
		return "set.method.del";
	}
	
	@PostMapping("del")
	public String del(String MCODE, Model model, String MNAME) {
		System.out.println("MethodController > del()@Post called");
		System.out.println(MCODE+" - "+MNAME);
		
		try {
			methodService.delete(MCODE);
			model.addAttribute("MSG", succMsg);
			model.addAttribute("URL", nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", nextUrl);
		}
		return "redirect";
	}
	
	/*-------- 결제수단 추가 --------*/

	@GetMapping("add")
	public String add(HttpServletRequest req, Model model) {
		System.out.println("MethodController > add()@Get called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		
		List<String> list = methodService.getNameList(userId);
		model.addAttribute("LIST", list);
		int maxSqn = methodService.getMaxSqn();
		System.out.println("maxSqn : "+maxSqn);
		model.addAttribute("MSN", maxSqn);
		return "set.method.add";
	}
	
	@PostMapping("add")
	public String add(@RequestParam Map<String,String> fm, HttpServletRequest req, Model model) {
		System.out.println("MethodController > add()@Post called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		for(Map.Entry<String,String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		try {
			methodService.insert(fm, userId);
			model.addAttribute("MSG", succMsg);
			model.addAttribute("URL", nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", nextUrl);
		}
		return "redirect";
	}
	
	/*-------- 결제수단 수정 --------*/ 
	
	@GetMapping("upd")
	public String upd(String MCODE, HttpServletRequest req, Model model) {
		System.out.println("MethodController > upd()@Get called");
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
	public String upd(@RequestParam Map<String,String> fm, Model model) {
		System.out.println("MethodController > upd()@Post called");
		for(Map.Entry<String, String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		try {
			methodService.update(fm);
			model.addAttribute("MSG", succMsg);
			model.addAttribute("URL", nextUrl);
		} catch (Exception e) {
			 e.printStackTrace();
			 model.addAttribute("MSG", failMsg);
			 model.addAttribute("URL", nextUrl);
		}
		return "redirect";
	}
}
