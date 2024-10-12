package com.EyEmilyKim.controller.specification;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EyEmilyKim.dto.request.category.CategoryCreateRequestDto;
import com.EyEmilyKim.dto.request.category.CategoryUpdateRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="view - 카테고리", description = "카테고리 페이지 그룹")
public interface CategoryControllerSpecification {

	/*-------- 카테고리 목록 --------*/

	@Operation(summary = "목록 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_list(HttpServletRequest req, Model model);
	
	/*-------- 카테고리 삭제 --------*/

	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_del(
			@Parameter(name = "CCODE", description = "카테고리 코드", example = "", required = true)
			String CCODE, 
			Model model
		);

	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "삭제 성공 알림, \n\n리다이렉트 : 목록 페이지"),
		@ApiResponse(responseCode = "500", description = "삭제 실패 알림, \n\n리다이렉트 : 목록 페이지")
	})
	String post_del(
			@Parameter(name = "CCODE", description = "카테고리 코드", example = "", required = true)
			String CCODE, 
			Model model
		);

	/*-------- 카테고리 추가 --------*/

	@Operation(summary = "추가 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_crt(HttpServletRequest req, Model model);

	@Operation(summary = "추가 페이지", description = "")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "추가 성공 알림, \n\n리다이렉트 : 목록 페이지"),
		@ApiResponse(responseCode = "500", description = "추가 실패 알림, \n\n리다이렉트 : 목록 페이지")
	})	
	String post_crt(
			CategoryCreateRequestDto categoryCreateRequestDto, 
			HttpServletRequest req, Model model
		);

	/*-------- 카테고리 수정 --------*/ 
	
	@Operation(summary = "수정 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_upd(
			@Parameter(name = "CCODE", description = "카테고리 코드", example = "", required = true)
			String CCODE, 
			HttpServletRequest req, Model model
		);

	@Operation(summary = "수정 페이지", description = "")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "수정 성공 알림, \n\n리다이렉트 : 목록 페이지"),
		@ApiResponse(responseCode = "500", description = "수정 실패 알림, \n\n리다이렉트 : 목록 페이지")
	})
	String post_upd(
			CategoryUpdateRequestDto categoryUpdateRequestDto, 
			Model model
		);

}
