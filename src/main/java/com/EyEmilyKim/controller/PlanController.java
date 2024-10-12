package com.EyEmilyKim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.EyEmilyKim.controller.specification.PlanControllerSpecification;
import com.EyEmilyKim.util.LogUtil;

@Controller
public class PlanController implements PlanControllerSpecification {

	/*-------- 계획하기 --------*/
	
	@GetMapping("/plan")
	public String get_plan() {
		LogUtil.printWithTimestamp("HomeController > get_plan() called");
		
		return "root.comingSoon";
	}
	
}
