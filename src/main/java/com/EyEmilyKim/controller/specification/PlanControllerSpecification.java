package com.EyEmilyKim.controller.specification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="[3] view - 계획하기", description = "계획 페이지 그룹")
public interface PlanControllerSpecification {

	/*-------- 계획하기 --------*/
	
	@Operation(summary = "계획하기→[준비중] 페이지", description = "현재 버전에서는 [준비중] 페이지 demo를 위한 dummy 역할")
	@ApiResponse(responseCode = "200", description = "정상적으로 [준비중] 페이지 반환됨\n\n5초 후 이전 경로로 자동 이동")
	String get_plan();

}
