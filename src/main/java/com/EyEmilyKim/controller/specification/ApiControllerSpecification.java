package com.EyEmilyKim.controller.specification;

import com.EyEmilyKim.dto.response.EmailToDeveloperResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API", description = "서버 → 클라이언트 데이터 전달")
public interface ApiControllerSpecification {

	@Operation(summary = "email 문의 템플릿")
	EmailToDeveloperResponseDto getEmailToDeveloper();

}
