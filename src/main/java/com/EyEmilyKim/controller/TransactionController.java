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

import com.EyEmilyKim.config.AppConfig;
import com.EyEmilyKim.controller.specification.TransactionControllerSpecification;
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
import com.EyEmilyKim.util.MessageUtil;

@RequestMapping("/tran/")
@Controller
public class TransactionController implements TransactionControllerSpecification {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MethodService methodService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private AppConfig appConfig;
	
	
	/*-------- 거래기록 목록 --------*/
	
	// 전체 목록	
	@GetMapping("listAll")
	public String get_listAll(@ModelAttribute TranListRequestDto tranListRequestDto, HttpServletRequest req, Model model) {
		
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
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", "/tran/listAll");
			return "redirect";
		}
		
		return "tran.list";
	}
	
	
	// 수입 목록
	@GetMapping("listIn")
	public String get_listIn(@ModelAttribute TranListRequestDto tranListRequestDto, HttpServletRequest req, Model model) {
		
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
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", "/tran/listIn");
			return "redirect";
		}
		
		return "tran.list";
	}
	
	
	//지출 목록
	@GetMapping("listEx") 
	public String get_listEx(@ModelAttribute TranListRequestDto tranListRequestDto, HttpServletRequest req, Model model) {
		
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
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
			model.addAttribute("URL", "/tran/listIn");
			return "redirect";
		}
		
		return "tran.list";
	}
	
	
	/*-------- 거래기록 삭제 --------*/
	
	@GetMapping("del")
	public String get_del(String TRAN_ID, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > get_del() called");
		System.out.println("TRAN_ID : "+TRAN_ID);
		
		TransactionDto tran = transactionService.getOne(TRAN_ID);
		model.addAttribute("T", tran);
		
		return "tran.del";
	}
	
	
	@PostMapping("del")
	public String post_del(@ModelAttribute TranDeleteRequestDto tranDeleteRequestDto, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > post_del() called");		
		DtoUtil.printFieldValues(tranDeleteRequestDto);
		
		String tran_id = tranDeleteRequestDto.getTRAN_ID();
		String nextUrl = tranDeleteRequestDto.getPREV_URL();
		
		try {
			transactionService.delete(tran_id);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
		}
		model.addAttribute("URL", nextUrl);
		
		return "redirect";
	}	

	
	/*-------- 거래기록 추가 --------*/
	
	@GetMapping("crt")
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
	public String post_crt(@ModelAttribute TranCreateRequestDto tranCreateRequestDto, HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > post_crt() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printFieldValues(tranCreateRequestDto);
		
		try {
			transactionService.insert(tranCreateRequestDto, userId);
			model.addAttribute("OK", true);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("OK", false);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
		}
		String contextPath = appConfig.getContextPath();
		model.addAttribute("TRAN_CRT", true);
		model.addAttribute("URL_AGAIN", contextPath + "/tran/crt");
		model.addAttribute("URL_NEXT", contextPath + "/tran/listAll");
		
		return "redirect";
	}
	
	/*-------- 거래기록 수정 --------*/
	
	@GetMapping("upd")
	public String get_upd(String TRAN_ID, HttpServletRequest req, Model model) {
		
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
	public String post_upd(@ModelAttribute TranUpdateRequestDto tranUpdateRequestDto, HttpServletRequest req, Model model) {
		
		LogUtil.printWithTimestamp("TransactionController > post_upd() called");
		int userId = (int) req.getAttribute("userId");
		System.out.println("userId : "+userId);
		DtoUtil.printFieldValues(tranUpdateRequestDto);
		
		try {
			transactionService.update(tranUpdateRequestDto);
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.success"));
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("MSG", messageUtil.getMessage("message-response", "msg.res.failure"));
		}
		model.addAttribute("URL", tranUpdateRequestDto.getPREV_URL());
		
		return "redirect";
	}
	
}
