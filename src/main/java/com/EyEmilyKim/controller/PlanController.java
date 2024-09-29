package com.EyEmilyKim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.EyEmilyKim.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name="view - 계획하기", description = "계획 페이지 그룹")
public class PlanController {

	/*-------- 계획하기 --------*/
	
	@Operation(summary = "계획하기→[준비중] 페이지", description = "현재 버전에서는 [준비중] 페이지 demo를 위한 dummy 역할")
	@ApiResponse(responseCode = "200", description = "정상적으로 [준비중] 페이지 반환됨. \n\n5초 후 이전 경로로 자동 이동.")
	@GetMapping("/plan")
	public String get_plan() {
		LogUtil.printWithTimestamp("HomeController > get_plan() called");
		
		return "root.comingSoon";
	}
	
}
