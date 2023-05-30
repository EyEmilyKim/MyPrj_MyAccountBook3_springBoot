package com.EyEmilyKim.controller.set;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EyEmilyKim.entity.Category;
import com.EyEmilyKim.service.CategoryService;

@RequestMapping("/set/category/")
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("list")
	public String list(Model model, HttpSession session) {
		System.out.println("CategoryController > list() called");
		
		String id = (String) session.getAttribute("USER_ID");
		if(id == null || id == "") id = "master";
		System.out.println("id : "+id);
		
		List<Category> list = categoryService.getList(id);
		model.addAttribute("LIST", list);
		int cnt = categoryService.getCount(id);
		model.addAttribute("COUNT", cnt);
		
		return "set.category.list";
	}
	
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
		
		int result = 0;
		String url = "list";
		try {
			result = categoryService.delete(CCODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String msg;
		if(result == 1) {
			msg = "정상적으로 처리되었습니다.";
		}else {
			msg = "처리에 실패했습니다... 관리자에게 문의해주세요.";
		}
		model.addAttribute("MSG", msg);
		model.addAttribute("URL", url);
		return "redirect";
	}
}
