package com.EyEmilyKim.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EyEmilyKim.entity.Category;
import com.EyEmilyKim.service.CategoryService;
import com.EyEmilyKim.util.LogUtil;

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
	
	private String failMsg = "에러가 발생했습니다.";
	private String succMsg = "정상적으로 처리되었습니다.";
	private String nextUrl = "/set/category/list";
		
	/*-------- 카테고리 목록 --------*/
	
	@Operation(summary = "목록 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	@GetMapping("list")
	public String list(HttpServletRequest req, Model model) {
		LogUtil.printWithTimestamp("CategoryController > list() called");
		int userId = (int) req.getAttribute("userId"); 
		System.out.println("userId : "+userId);
		
		List<Category> list = categoryService.getList(userId);
		model.addAttribute("LIST", list);
		int cnt = categoryService.getCount(userId);
		model.addAttribute("COUNT", cnt);
		
		return "set.category.list";
	}
	
	/*-------- 카테고리 삭제 --------*/
	
	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	@GetMapping("del")
	public String del(
			@Parameter(name = "CCODE", description = "카테고리 코드", example = "", required = true)
			String CCODE, 
			Model model) {
		LogUtil.printWithTimestamp("CategoryController > del()@Get called");
		System.out.println(CCODE);
		
		Category cate = categoryService.getOne(CCODE);
		System.out.println(cate.getCate_code()+" - "+cate.getCate_name());
		model.addAttribute("C", cate);
		return "set.category.del";
	}
	
	@Operation(summary = "삭제 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "삭제 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "삭제 실패 알림, \n\n리다이렉트: 목록 페이지")
	})
	@PostMapping("del")
	public String del(
			@Parameter(name = "CCODE", description = "카테고리 코드", example = "", required = true)
			String CCODE, 
			@Parameter(name = "CNAME", description = "카테고리 이름 ** to_be_refactored **", example = "", required = true)
			String CNAME,
			Model model) {
		LogUtil.printWithTimestamp("CategoryController > del()@Post called");
		System.out.println(CCODE+" - "+CNAME);
		
		try {
			categoryService.delete(CCODE);
			model.addAttribute("MSG", succMsg);
			model.addAttribute("URL", nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", nextUrl);
		}
		return "redirect";
	}
	
	/*-------- 카테고리 추가 --------*/
	
	@Operation(summary = "추가 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	@GetMapping("crt")
	public String crt(HttpServletRequest req, Model model) {
		LogUtil.printWithTimestamp("CategoryController > crt()@Get called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		
		List<String> list = categoryService.getNameList(userId, "");
		model.addAttribute("LIST", list);
		int maxSqn = categoryService.getMaxSqn();
		System.out.println("maxSqn : "+maxSqn);
		model.addAttribute("MSN", maxSqn);
		return "set.category.crt";
	}
	
	@Operation(summary = "추가 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "추가 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "추가 실패 알림, \n\n리다이렉트: 목록 페이지")
	})	
	@PostMapping("crt")
	public String crt(
			@RequestParam Map<String,String> fm, 
			HttpServletRequest req, Model model) {
		LogUtil.printWithTimestamp("CategoryController > crt()@Post called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		for(Map.Entry<String,String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		try {
			categoryService.insert(fm, userId);
			model.addAttribute("MSG", succMsg);
			model.addAttribute("URL", nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", nextUrl);
		}
		return "redirect";
	}
	
	/*-------- 카테고리 수정 --------*/ 
	
	@Operation(summary = "수정 페이지", description = "")
	@ApiResponse(responseCode = "200", description = "정상적으로 페이지 반환됨")
	@GetMapping("upd")
	public String upd(
			@Parameter(name = "CCODE", description = "카테고리 코드", example = "", required = true)
			String CCODE, 
			HttpServletRequest req, 
			Model model) {
		LogUtil.printWithTimestamp("CategoryController > upd()@Get called");
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
	
	@Operation(summary = "수정 페이지", description = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "수정 성공 알림, \n\n리다이렉트: 목록 페이지"),
			@ApiResponse(responseCode = "500", description = "수정 실패 알림, \n\n리다이렉트: 목록 페이지")
	})
	@PostMapping("upd")
	public String upd(
			@RequestParam Map<String,String> fm, 
			Model model) {
		LogUtil.printWithTimestamp("CategoryController > upd()@Post called");
		for(Map.Entry<String,String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}

		try {
			categoryService.update(fm);
			model.addAttribute("MSG", succMsg);
			model.addAttribute("URL", nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", nextUrl);
		}
		return "redirect";
	}
}
