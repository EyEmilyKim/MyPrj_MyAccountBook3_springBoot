package com.EyEmilyKim.controller.specification;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.EyEmilyKim.dto.request.tran.TranCreateRequestDto;
import com.EyEmilyKim.dto.request.tran.TranDeleteRequestDto;
import com.EyEmilyKim.dto.request.tran.TranListRequestDto;
import com.EyEmilyKim.dto.request.tran.TranUpdateRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="[4] view - 거래기록", description = "거래기록 페이지 그룹")
public interface TransactionControllerSpecification {

	/*-------- 거래기록 목록 --------*/
	
	// 전체 목록	
	@Operation(summary = "목록 페이지 : 전체", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_listAll(
			TranListRequestDto tranListRequestDto, 
			HttpServletRequest req, Model model
		);

	// 수입 목록
	@Operation(summary = "목록 페이지 : 수입", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_listIn(
			TranListRequestDto tranListRequestDto, 
			HttpServletRequest req, Model model
		);

	//지출 목록
	@Operation(summary = "목록 페이지 : 지출", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_listEx(
			TranListRequestDto tranListRequestDto, 
			HttpServletRequest req, Model model
		);

	/*-------- 거래기록 삭제 --------*/
	
	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_del(
			@Parameter(name = "TRAN_ID", description = "거래기록 ID", example = "", required = true)
			String TRAN_ID, 
			Model model
		);

	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "삭제 성공 알림, \n\n리다이렉트 : 목록 페이지"),
		@ApiResponse(responseCode = "500", description = "삭제 실패 알림, \n\n리다이렉트 : 목록 페이지")
	})
	String post_del(
			TranDeleteRequestDto tranDeleteRequestDto, 
			Model model
		);

	/*-------- 거래기록 추가 --------*/
	
	@Operation(summary = "추가 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_crt(HttpServletRequest req, Model model);

	@Operation(summary = "추가 페이지", description = "")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "추가 성공 알림, \n\n리다이렉트 : 목록 페이지"),
		@ApiResponse(responseCode = "500", description = "추가 실패 알림, \n\n리다이렉트 : 목록 페이지")
	})	
	String post_crt(
			TranCreateRequestDto tranCreateRequestDto, 
			HttpServletRequest req, Model model
		);

	/*-------- 거래기록 수정 --------*/
	
	@Operation(summary = "수정 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	String get_upd(
			@Parameter(name = "TRAN_ID", description = "거래기록 ID", example = "", required = true)
			String TRAN_ID, 
			HttpServletRequest req, Model model
		);

	@Operation(summary = "수정 페이지", description = "")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "수정 성공 알림, \n\n리다이렉트 : 목록 페이지"),
		@ApiResponse(responseCode = "500", description = "수정 실패 알림, \n\n리다이렉트 : 목록 페이지")
	})
	String post_upd(
			TranUpdateRequestDto tranUpdateRequestDto, 
			HttpServletRequest req, Model model
		);

}
