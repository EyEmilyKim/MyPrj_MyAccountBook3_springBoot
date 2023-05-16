package com.EyEmilyKim.controller.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EyEmilyKim.service.CategoryService;

@RequestMapping("/set/category/")
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService cateServ;
	
	@RequestMapping("list")
	public String list(Model model) {
		System.out.println("CategoryController > list() called");
		
//		String id = "master";
//		
//		List<Category> list = cateServ.getList(id);
//		model.addAttribute("LIST", list);
//		int cnt = cateServ.getCount(id);
//		model.addAttribute("COUNT", cnt);
		
		return "set.category.list";
	}
}
