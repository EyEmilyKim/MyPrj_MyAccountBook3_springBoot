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
import com.EyEmilyKim.controller.specification.CategoryControllerSpecification;
import com.EyEmilyKim.dto.request.category.CategoryCreateRequestDto;
import com.EyEmilyKim.dto.request.category.CategoryUpdateRequestDto;
import com.EyEmilyKim.entity.Category;
import com.EyEmilyKim.service.CategoryService;
import com.EyEmilyKim.util.DtoUtil;
import com.EyEmilyKim.util.LogUtil;
import com.EyEmilyKim.util.MessageUtil;

@RequestMapping("/set/category/")
@Controller
public class CategoryController implements CategoryControllerSpecification {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private AppConfig appConfig;
	
	private String nextUrl = "/set/category/list";
	
	
	/*-------- 카테고리 목록 --------*/
	
	@GetMapping("list")
	public String get_list(HttpServletRequest req, Model model) {

		LogUtil.printWithTimestamp("CategoryController > get_list() called");
		int userId = (int) req.getAttribute("userId"); 
		System.out.println("userId : "+userId);
		
		List<Category> list = categoryService.getList(userId);
		model.addAttribute("LIST", list);
		int cnt = categoryService.getCount(userId);
		model.addAttribute("COUNT", cnt);
		
		return "set.category.list";
	}
	
	
	/*-------- 카테고리 삭제 --------*/
	
	@GetMapping("del")
	public String get_del(String CCODE, Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > get_del() called");
		System.out.println(CCODE);
		
		Category cate = categoryService.getOne(CCODE);
		System.out.println(cate.getCate_code()+" - "+cate.getCate_name());
		model.addAttribute("C", cate);
		
		return "set.category.del";
	}
	
	
	@PostMapping("del")
	public String post_del(String CCODE, Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > post_del() called");
		System.out.println(CCODE);
		
		try {
			categoryService.delete(CCODE);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		}
		
		return "redirect";
	}
	
	
	/*-------- 카테고리 추가 --------*/
	
	@GetMapping("crt")
	public String get_crt(HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > get_crt() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		
		List<String> list = categoryService.getNameList(userId, "");
		model.addAttribute("LIST", list);
		int maxSqn = categoryService.getMaxSqn();
		System.out.println("maxSqn : "+maxSqn);
		model.addAttribute("MSN", maxSqn);
		
		return "set.category.crt";
	}
	
	
	@PostMapping("crt")
	public String post_crt(@ModelAttribute CategoryCreateRequestDto categoryCreateRequestDto, HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > post_crt() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printFieldValues(categoryCreateRequestDto);
		
		try {
			categoryService.insert(categoryCreateRequestDto, userId);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", appConfig.getContextPath() + nextUrl);
		}
		
		return "redirect";
	}
	
	
	/*-------- 카테고리 수정 --------*/ 
	
	@GetMapping("upd")
	public String get_upd(String CCODE, HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > get_upd() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		System.out.println(CCODE);
		
		Category cate = categoryService.getOne(CCODE);
		System.out.println(cate.getCate_code()+" - "+cate.getCate_name());
		model.addAttribute("C", cate);
		List<String> list = categoryService.getNameList(userId, "");
		model.addAttribute("LIST", list);
		
		return "set.category.upd";
	}
	
	
	@PostMapping("upd")
	public String post_upd(@ModelAttribute CategoryUpdateRequestDto categoryUpdateRequestDto, Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > post_upd() called");
		DtoUtil.printFieldValues(categoryUpdateRequestDto);

		try {
			categoryService.update(categoryUpdateRequestDto);
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
