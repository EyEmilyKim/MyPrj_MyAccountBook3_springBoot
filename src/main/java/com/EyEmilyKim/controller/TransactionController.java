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

import com.EyEmilyKim.entity.Category;
import com.EyEmilyKim.entity.Method;
import com.EyEmilyKim.service.CategoryService;
import com.EyEmilyKim.service.MethodService;
import com.EyEmilyKim.service.TransactionService;

@RequestMapping("/tran/")
@Controller
public class TransactionController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	MethodService methodeService;
	
	@Autowired
	TransactionService transactionService;
	
	private String failMsg = "해당 기능이 준비되지 않아 처리하지 못했습니다.";
	private String succMsg = "정상적으로 처리되었습니다.";
	private String nextUrl = "/tran/add";
	
	@GetMapping("listAll")
	public String listAll() {
		System.out.println("TransactionController > listAll()@Get called");
		return "tran.listAll";
	}
	
	/*-------- 거래내역 추가 --------*/
	
	@GetMapping("add")
	public String add(HttpServletRequest req, Model model) {
		System.out.println("TransactionController > add()@Get called");
		Integer userId = (Integer) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		
		// maxSeqno 받아오기
		List<Category> catelist = categoryService.getList(userId);
		model.addAttribute("CATELIST", catelist);
		List<Method> methlist = methodeService.getList(userId);
		model.addAttribute("METHLIST", methlist);
		
		return "tranAdd.add";
	}
	
	@PostMapping("add")
	public String add(@RequestParam Map<String,String> fm, HttpServletRequest req, Model model) {
		System.out.println("TransactionController > add()@Post called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		for(Map.Entry<String,String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		try {
			transactionService.insert(fm, userId);
			model.addAttribute("MSG", succMsg);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
		}
		model.addAttribute("URL", nextUrl);
		return "redirect";
	}
	
}
