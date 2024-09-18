package com.EyEmilyKim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EyEmilyKim.dto.TransactionDto;
import com.EyEmilyKim.dto.request.tran.TranCreateRequestDto;
import com.EyEmilyKim.dto.request.tran.TranDeleteRequestDto;
import com.EyEmilyKim.dto.request.tran.TranListRequestDto;
import com.EyEmilyKim.dto.request.tran.TranUpdateRequestDto;
import com.EyEmilyKim.dto.response.tran.TranListResponseDto;
import com.EyEmilyKim.entity.Category;
import com.EyEmilyKim.entity.Method;
import com.EyEmilyKim.entity.Transaction;
import com.EyEmilyKim.service.CategoryService;
import com.EyEmilyKim.service.MethodService;
import com.EyEmilyKim.service.TransactionService;
import com.EyEmilyKim.util.DtoUtil;
import com.EyEmilyKim.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/tran/")
@Controller
@Tag(name="view - 거래기록", description = "거래기록 페이지 그룹")
public class TransactionController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	MethodService methodService;
	
	@Autowired
	TransactionService transactionService;
	
	private String failMsg = "에러가 발생했습니다.";
	private String succMsg = "정상적으로 처리되었습니다.";
	
	
	/*-------- 거래기록 목록 --------*/
	
	// 전체 목록	
	@GetMapping("listAll")
	@Operation(summary = "목록 페이지 : 전체", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_listAll(
			@ModelAttribute TranListRequestDto tranListRequestDto, 
			HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > get_listAll() called");
		Integer userId = (Integer) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printFieldValues(tranListRequestDto);
		
		try {
			TranListResponseDto responseDto = transactionService.getList(tranListRequestDto, userId, "ALL");
			model.addAttribute("DTO", responseDto);
			List<String> cname_list = categoryService.getNameList(userId, "ALL");
			model.addAttribute("CNAME_LIST", cname_list);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", "/tran/listAll");
			return "redirect";
		}
		
		return "tran.list";
		
	}
	
	
	// 수입 목록
	@GetMapping("listIn")
	@Operation(summary = "목록 페이지 : 수입", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_listIn(
			@ModelAttribute TranListRequestDto tranListRequestDto, 
			HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > get_listIn() called");
		Integer userId = (Integer) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printFieldValues(tranListRequestDto);
		
		try {
			TranListResponseDto responseDto = transactionService.getList(tranListRequestDto, userId, "IN");
			model.addAttribute("DTO", responseDto);
			List<String> cname_list = categoryService.getNameList(userId, "IN");
			model.addAttribute("CNAME_LIST", cname_list);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", "/tran/listIn");
			return "redirect";
		}
		
		return "tran.list";
		
	}
	
	
	//지출 목록
	@GetMapping("listEx") 
	@Operation(summary = "목록 페이지 : 지출", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_listEx(
			@ModelAttribute TranListRequestDto tranListRequestDto, 
			HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > get_listEx() called");
		Integer userId = (Integer) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printFieldValues(tranListRequestDto);
		
		try {
			TranListResponseDto responseDto = transactionService.getList(tranListRequestDto, userId, "EX");
			model.addAttribute("DTO", responseDto);
			List<String> cname_list = categoryService.getNameList(userId, "EX");
			model.addAttribute("CNAME_LIST", cname_list);
			List<String> mname_list = methodService.getNameList(userId);
			model.addAttribute("MNAME_LIST", mname_list);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", "/tran/listIn");
			return "redirect";
		}
		
		return "tran.list";
		
	}
	
	
	/*-------- 거래기록 삭제 --------*/
	
	@GetMapping("del")
	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_del(
			@Parameter(name = "TRAN_ID", description = "거래기록 ID", example = "", required = true)
			String TRAN_ID, 
			Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > get_del() called");
		System.out.println("TRAN_ID : "+TRAN_ID);
		
		TransactionDto tran = transactionService.getOne(TRAN_ID);
		model.addAttribute("T", tran);
		
		return "tran.del";
		
	}
	
	
	@PostMapping("del")
	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "삭제 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "삭제 실패 알림, \n\n리다이렉트: 목록 페이지")
	})
	public String post_del(
			@ModelAttribute TranDeleteRequestDto tranDeleteRequestDto, 
			Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > post_del() called");		
		DtoUtil.printFieldValues(tranDeleteRequestDto);
		
		String tran_id = tranDeleteRequestDto.getTRAN_ID();
		String nextUrl = tranDeleteRequestDto.getPREV_URL();
		
		try {
			transactionService.delete(tran_id);
			model.addAttribute("MSG", succMsg);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
		}
		model.addAttribute("URL", nextUrl);
		
		return "redirect";
		
	}	

	
	/*-------- 거래기록 추가 --------*/
	
	@GetMapping("crt")
	@Operation(summary = "추가 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_crt(HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > get_crt() called");
		Integer userId = (Integer) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		
		Integer maxMySqn = transactionService.getMaxMySqn(userId);
		model.addAttribute("MSN", maxMySqn);
		List<Category> catelist = categoryService.getList(userId);
		model.addAttribute("CATELIST", catelist);
		List<Method> methlist = methodService.getList(userId);
		model.addAttribute("METHLIST", methlist);
		
		return "tranCrt.crt";
		
	}
	
	
	@PostMapping("crt")
	@Operation(summary = "추가 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "추가 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "추가 실패 알림, \n\n리다이렉트: 목록 페이지")
	})	
	public String post_crt(
			@ModelAttribute TranCreateRequestDto tranCreateRequestDto, 
			HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > post_crt() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printFieldValues(tranCreateRequestDto);
		
		try {
			transactionService.insert(tranCreateRequestDto, userId);
			model.addAttribute("OK", true);
			model.addAttribute("MSG", succMsg);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("OK", false);
			model.addAttribute("MSG", failMsg);
		}
		model.addAttribute("TRAN_CRT", true);
		model.addAttribute("URL_AGAIN", "/tran/crt");
		model.addAttribute("URL_NEXT", "/tran/listAll");
		
		return "redirect";
		
	}
	
	/*-------- 거래기록 수정 --------*/
	
	@GetMapping("upd")
	@Operation(summary = "수정 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_upd(
			@Parameter(name = "TRAN_ID", description = "거래기록 ID", example = "", required = true)
			String TRAN_ID, 
			HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > get_upd() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		System.out.println("TRAN_ID : "+TRAN_ID);
		
		Transaction tran = transactionService.getOne(TRAN_ID);
		model.addAttribute("T", tran);
		System.out.println("tran : "+tran);
		
		List<Category> catelist = categoryService.getList(userId);
		model.addAttribute("CATELIST", catelist);
		List<Method> methlist = methodService.getList(userId);
		model.addAttribute("METHLIST", methlist);
		
		return "tran.upd";
		
	}
	
	
	@PostMapping("upd")
	@Operation(summary = "수정 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "수정 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "수정 실패 알림, \n\n리다이렉트: 목록 페이지")
	})
	public String post_upd(
			@ModelAttribute TranUpdateRequestDto tranUpdateRequestDto, 
			HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > post_upd() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printFieldValues(tranUpdateRequestDto);
		
		try {
			transactionService.update(tranUpdateRequestDto);
			model.addAttribute("MSG", succMsg);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
		}
		model.addAttribute("URL", tranUpdateRequestDto.getPREV_URL());
		
		return "redirect";
		
	}
	
}
