package com.EyEmilyKim.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EyEmilyKim.dto.TranPageDto;
import com.EyEmilyKim.dto.TranSearchDto;
import com.EyEmilyKim.entity.Category;
import com.EyEmilyKim.entity.Method;
import com.EyEmilyKim.service.CategoryService;
import com.EyEmilyKim.service.MethodService;
import com.EyEmilyKim.service.TransactionService;
import com.EyEmilyKim.util.DtoUtil;

@RequestMapping("/tran/")
@Controller
public class TransactionController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	MethodService methodService;
	
	@Autowired
	TransactionService transactionService;
	
	private String failMsg = "해당 기능이 준비되지 않아 처리하지 못했습니다.";
	private String succMsg = "정상적으로 처리되었습니다.";
	
	/*-------- 거래내역 목록 --------*/
	
	@GetMapping("listAll") // 전체 목록
	public String listAll(@ModelAttribute TranSearchDto searchDto, HttpServletRequest req, Model model) {
		System.out.println("TransactionController > listAll()@Get called");
		Integer userId = (Integer) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printDto(searchDto);
		try {
			TranPageDto resultDto = transactionService.getList(searchDto, userId, "");
			model.addAttribute("DTO", resultDto);
			List<String> cname_list = categoryService.getNameList(userId, "");
			model.addAttribute("CNAME_LIST", cname_list);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", "/tran/listAll");
			return "redirect";
		}
		return "tran.listAll";
	}
	
	@GetMapping("listIn") // 수입 목록
	public String listIn(@ModelAttribute TranSearchDto searchDto, HttpServletRequest req, Model model) {
		System.out.println("TransactionController > listIn()@Get called");
		Integer userId = (Integer) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printDto(searchDto);
		try {
			TranPageDto resultDto = transactionService.getList(searchDto, userId, "IN");
			model.addAttribute("DTO", resultDto);
			List<String> cname_list = categoryService.getNameList(userId, "IN");
			model.addAttribute("CNAME_LIST", cname_list);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
			model.addAttribute("URL", "/tran/listIn");
			return "redirect";
		}
		return "tran.listIn";
	}
	
	@GetMapping("listEx") // 지출 목록
	public String listEx(@ModelAttribute TranSearchDto searchDto, HttpServletRequest req, Model model) {
		System.out.println("TransactionController > listEx()@Get called");
		Integer userId = (Integer) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printDto(searchDto);
		try {
			TranPageDto resultDto = transactionService.getList(searchDto, userId, "EX");
			model.addAttribute("DTO", resultDto);
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
		return "tran.listEx";
	}
	
	/*-------- 거래내역 추가 --------*/
	
	@GetMapping("add")
	public String add(HttpServletRequest req, Model model) {
		System.out.println("TransactionController > add()@Get called");
		Integer userId = (Integer) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		
		Integer maxMySqn = transactionService.getMaxMySqn(userId);
		model.addAttribute("MSN", maxMySqn);
		List<Category> catelist = categoryService.getList(userId);
		model.addAttribute("CATELIST", catelist);
		List<Method> methlist = methodService.getList(userId);
		model.addAttribute("METHLIST", methlist);
		
		return "tranAdd.add";
	}
	
	@PostMapping("add")
	public String add(@RequestParam Map<String,String> fm, HttpServletRequest req, Model model) {
		System.out.println("TransactionController > add()@Post called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		for(Map.Entry<String,String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		try {
			transactionService.insert(fm, userId);
			model.addAttribute("MSG", succMsg);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", failMsg);
		}
		model.addAttribute("URL", "/tran/add");
		return "redirect";
	}
	
}
