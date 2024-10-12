package com.EyEmilyKim.controller.specification;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API Test", description = "세부 작동 확인용 엔드포인트")
public interface ApiTestControllerSpecification {

	@Operation(summary = "클라이언트에서 전달한 메세지 응답하기")
	String echo(
			@Parameter(name = "msg", description = "클라이언트에서 전달하는 메시지", example = "아아 마잌쳌 마잌쳌", required = true)
			String msg
		);

	@Operation(summary = "서버 콘솔에 테스트 로그 출력시키기")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "요청 성공. 반환 데이터 없음"),
		@ApiResponse(responseCode = "400", description = "잘못된 요청"),
	})
	ResponseEntity<String> log(
			@Parameter(name = "level", description = "출력할 테스트 로그 레벨 - all / trace / debug / info / warn / error", example = "all")
			String level
		);

}
