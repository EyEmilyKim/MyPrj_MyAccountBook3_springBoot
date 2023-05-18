package com.EyEmilyKim.controller.set;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EyEmilyKim.entity.Category;
import com.EyEmilyKim.service.CategoryService;

@RequestMapping("/set/category/")
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("list")
	public String list(Model model) {
		System.out.println("CategoryController > list() called");
		
		String id = "master";
		
		List<Category> list = categoryService.getList(id);
		model.addAttribute("LIST", list);
		int cnt = categoryService.getCount(id);
		model.addAttribute("COUNT", cnt);
		
		return "set.category.list";
	}
}
