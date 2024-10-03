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

import com.EyEmilyKim.config.properties.ApiProperties;
import com.EyEmilyKim.dto.request.category.CategoryCreateRequestDto;
import com.EyEmilyKim.dto.request.category.CategoryUpdateRequestDto;
import com.EyEmilyKim.entity.Category;
import com.EyEmilyKim.service.CategoryService;
import com.EyEmilyKim.util.DtoUtil;
import com.EyEmilyKim.util.LogUtil;
import com.EyEmilyKim.util.MessageUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/set/category/")
@Controller
@Tag(name="view - 카테고리", description = "카테고리 페이지 그룹")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private ApiProperties apiProps;
	
	private String nextUrl = "/set/category/list";
	
	
	/*-------- 카테고리 목록 --------*/
	
	@GetMapping("list")
	@Operation(summary = "목록 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_list(HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > get_list() called");
		int userId = (int) req.getAttribute("userId"); 
		System.out.println("userId : "+userId);
		
		List<Category> list = categoryService.getList(userId);
		model.addAttribute("LIST", list);
		int cnt = categoryService.getCount(userId);
		model.addAttribute("COUNT", cnt);
		
		return "set.category.list";
		
	}
	
	
	/*-------- 카테고리 삭제 --------*/
	
	@GetMapping("del")
	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_del(
			@Parameter(name = "CCODE", description = "카테고리 코드", example = "", required = true)
			String CCODE, 
			Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > get_del() called");
		System.out.println(CCODE);
		
		Category cate = categoryService.getOne(CCODE);
		System.out.println(cate.getCate_code()+" - "+cate.getCate_name());
		model.addAttribute("C", cate);
		
		return "set.category.del";
		
	}
	
	
	@PostMapping("del")
	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "삭제 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "삭제 실패 알림, \n\n리다이렉트: 목록 페이지")
	})
	public String post_del(
			@Parameter(name = "CCODE", description = "카테고리 코드", example = "", required = true)
			String CCODE, 
			@Parameter(name = "CNAME", description = "카테고리 이름 ** to_be_refactored **", example = "", required = true)
			String CNAME,
			Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > post_del() called");
		System.out.println(CCODE+" - "+CNAME);
		
		try {
			categoryService.delete(CCODE);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
			model.addAttribute("URL", apiProps.getContext_path() + nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", apiProps.getContext_path() + nextUrl);
		}
		
		return "redirect";
		
	}
	
	
	/*-------- 카테고리 추가 --------*/
	
	@GetMapping("crt")
	@Operation(summary = "추가 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_crt(HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > get_crt() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		
		List<String> list = categoryService.getNameList(userId, "");
		model.addAttribute("LIST", list);
		int maxSqn = categoryService.getMaxSqn();
		System.out.println("maxSqn : "+maxSqn);
		model.addAttribute("MSN", maxSqn);
		
		return "set.category.crt";
		
	}
	
	
	@PostMapping("crt")
	@Operation(summary = "추가 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "추가 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "추가 실패 알림, \n\n리다이렉트: 목록 페이지")
	})	
	public String post_crt(
			@ModelAttribute CategoryCreateRequestDto categoryCreateRequestDto, 
			HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > post_crt() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printFieldValues(categoryCreateRequestDto);
		
		try {
			categoryService.insert(categoryCreateRequestDto, userId);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
			model.addAttribute("URL", apiProps.getContext_path() + nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", apiProps.getContext_path() + nextUrl);
		}
		
		return "redirect";
		
	}
	
	
	/*-------- 카테고리 수정 --------*/ 
	
	@GetMapping("upd")
	@Operation(summary = "수정 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	public String get_upd(
			@Parameter(name = "CCODE", description = "카테고리 코드", example = "", required = true)
			String CCODE, 
			HttpServletRequest req, 
			Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > get_upd() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		System.out.println(CCODE);
		
		Category cate = categoryService.getOne(CCODE);
		System.out.println(cate.getCate_code()+" - "+cate.getCate_name());
		model.addAttribute("C", cate);
		List<String> list = categoryService.getNameList(userId, "");
		model.addAttribute("LIST", list);
		
		return "set.category.upd";
		
	}
	
	
	@PostMapping("upd")
	@Operation(summary = "수정 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "수정 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "수정 실패 알림, \n\n리다이렉트: 목록 페이지")
	})
	public String post_upd(
			@ModelAttribute CategoryUpdateRequestDto categoryUpdateRequestDto, 
			Model model) {
		
		LogUtil.printWithTimestamp("CategoryController > post_upd() called");
		DtoUtil.printFieldValues(categoryUpdateRequestDto);

		try {
			categoryService.update(categoryUpdateRequestDto);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
			model.addAttribute("URL", apiProps.getContext_path() + nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", apiProps.getContext_path() + nextUrl);
		}
		
		return "redirect";
		
	}
	
}
