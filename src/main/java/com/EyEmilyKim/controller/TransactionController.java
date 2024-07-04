package com.EyEmilyKim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EyEmilyKim.entity.Category;
import com.EyEmilyKim.entity.Method;
import com.EyEmilyKim.service.CategoryService;
import com.EyEmilyKim.service.MethodService;

@RequestMapping("/tran/")
@Controller
public class TransactionController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	MethodService methodeService;
	
	@GetMapping("listAll")
	public String listAll() {
		System.out.println("TransactionController > listAll()@Get called");
		return "tran.listAll";
	}
	
	@GetMapping("add")
	public String add(HttpServletRequest req, Model model) {
		System.out.println("TransactionController > add()@Get called");
		Integer userId = (Integer) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		
		List<Category> catelist = categoryService.getList(userId);
		model.addAttribute("CATELIST", catelist);
		List<Method> methlist = methodeService.getList(userId);
		model.addAttribute("METHLIST", methlist);
		
		return "tranAdd.add";
	}
}
