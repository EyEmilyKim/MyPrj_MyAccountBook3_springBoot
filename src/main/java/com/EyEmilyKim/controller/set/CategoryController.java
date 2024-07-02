package com.EyEmilyKim.controller.set;

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
import com.EyEmilyKim.service.CategoryService;

@RequestMapping("/set/category/")
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	private String failMsg = "해당 기능이 준비되지 않아 처리하지 못했습니다.";
	private String succMsg = "정상적으로 처리되었습니다.";
	private String nextUrl = "/set/category/list";
		
	/*-------- 카테고리 목록 --------*/
	
	@GetMapping("list")
	public String list(HttpServletRequest req, Model model) {
		System.out.println("CategoryController > list() called");
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
	public String del(String CCODE, Model model) {
		System.out.println("CategoryController > del()@Get called");
		System.out.println(CCODE);
		
		Category cate = categoryService.getOne(CCODE);
		System.out.println(cate.getCate_code()+" - "+cate.getCate_name());
		model.addAttribute("C", cate);
		return "set.category.del";
	}
	
	@PostMapping("del")
	public String del(String CCODE, Model model, String CNAME) {
		System.out.println("CategoryController > del()@Post called");
		System.out.println(CCODE+" - "+CNAME);
		
		try {
			categoryService.delete(CCODE);
			model.addAttribute("MSG", succMsg);
			model.addAttribute("URL", nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", nextUrl);
		}
		return "redirect";
	}
	
	/*-------- 카테고리 추가 --------*/
	
	@GetMapping("add")
	public String add(HttpServletRequest req, Model model) {
		System.out.println("CategoryController > add()@Get called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		
		List<String> list = categoryService.getNameList(userId);
		model.addAttribute("LIST", list);
		int maxSqn = categoryService.getMaxSqn();
		System.out.println("maxSqn : "+maxSqn);
		model.addAttribute("MSN", maxSqn);
		return "set.category.add";
	}
	
	@PostMapping("add")
	public String add(@RequestParam Map<String,String> fm, HttpServletRequest req, Model model) {
		System.out.println("CategoryController > add()@Post called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		for(Map.Entry<String,String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		try {
			categoryService.insert(fm, userId);
			model.addAttribute("MSG", succMsg);
			model.addAttribute("URL", nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", nextUrl);
		}
		return "redirect";
	}
	
	/*-------- 카테고리 수정 --------*/ 
	
	@GetMapping("upd")
	public String upd(HttpServletRequest req, String CCODE, Model model) {
		System.out.println("CategoryController > upd()@Get called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		System.out.println(CCODE);
		
		Category cate = categoryService.getOne(CCODE);
		System.out.println(cate.getCate_code()+" - "+cate.getCate_name());
		model.addAttribute("C", cate);
		List<String> list = categoryService.getNameList(userId);
		model.addAttribute("LIST", list);
		return "set.category.upd";
	}
	
	@PostMapping("upd")
	public String upd(@RequestParam Map<String,String> fm, Model model) {
		System.out.println("CategoryController > upd()@Post called");
		for(Map.Entry<String,String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}

		try {
			categoryService.update(fm);
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
