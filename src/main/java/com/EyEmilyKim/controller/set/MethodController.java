package com.EyEmilyKim.controller.set;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EyEmilyKim.entity.Method;
import com.EyEmilyKim.service.MethodService;

@RequestMapping("/set/method/")
@Controller
public class MethodController {

	@Autowired
	private MethodService methodService;
	
	@RequestMapping("list")
	public String list(Model model) {
		System.out.println("MethodController > list() called");
		
		String id = "master";
		
		List<Method> list = methodService.getList(id);
		model.addAttribute("LIST", list);
		int cnt = methodService.getCount(id);
		model.addAttribute("COUNT", cnt);
		
		return "set.method.list";
	}
}
