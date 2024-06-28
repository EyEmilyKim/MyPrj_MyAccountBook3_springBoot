package com.EyEmilyKim.controller.set;

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

import com.EyEmilyKim.entity.Method;
import com.EyEmilyKim.service.MethodService;

@RequestMapping("/set/method/")
@Controller
public class MethodController {

	@Autowired
	private MethodService methodService;
	
	private String failMsg = "해당 기능이 준비되지 않아 처리하지 못했습니다.";
	private String succMsg = "정상적으로 처리되었습니다.";
	private String nextUrl = "/set/category/list";
	
	
	/*-------- 결제수단 목록 --------*/ 
	
	@GetMapping("list")
	public String list(HttpServletRequest req, Model model) {
		System.out.println("MethodController > list() called");
		String id = (String) req.getAttribute("userId"); 
		System.out.println("id : "+id);
		
		List<Method> list = methodService.getList(id);
		model.addAttribute("LIST", list);
		int cnt = methodService.getCount(id);
		model.addAttribute("COUNT", cnt);
		
		return "set.method.list";
	}

	/*-------- 결제수단 삭제 --------*/
	
	@GetMapping("del")
	public String del(String MCODE, Model model) {
		System.out.println("CategoryController > del()@Get called");
		System.out.println(MCODE);
		
		Method meth= methodService.getOne(MCODE);
		System.out.println(meth.getMeth_code()+" - "+meth.getMeth_name());
		model.addAttribute("M", meth);
		return "set.method.del";
	}
	
	/*-------- 결제수단 추가 --------*/
	
	/*-------- 결제수단 수정 --------*/ 
	
	@GetMapping("upd")
	public String upd(String MCODE, HttpServletRequest req, Model model) {
		System.out.println("MethodController > upd()@Get called");
		System.out.println(MCODE);
		
		String id = (String) req.getAttribute("USER_ID");
		if(id == null || id == "") id = "master";
		System.out.println("id : "+id);
		
		Method meth = methodService.getOne(MCODE);
		System.out.println(meth.getMeth_code()+" - "+meth.getMeth_name());
		model.addAttribute("M", meth);
		List<String> list = methodService.getNameList(id);
		model.addAttribute("LIST", list);
		return "set.method.upd";
	}
	
	@PostMapping("upd")
	public String upd(@RequestParam Map<String,String> fm, Model model) {
		System.out.println("MethodController > upd()@Post called");
		for(Map.Entry<String, String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		System.out.println(fm.get("MCODE")+" - "+fm.get("MNAME")+" -> "+fm.get("N_MNAME"));
		
		Method meth = new Method();
		meth.setMeth_code(fm.get("MCODE"));
		meth.setMeth_name(fm.get("N_MNAME"));
		
		int result = 0;
		try {
			result = methodService.update(meth);
			model.addAttribute("MSG", succMsg);
			model.addAttribute("URL", nextUrl);
		} catch (Exception e) {
			 e.printStackTrace();
			 model.addAttribute("MSG", failMsg);
			 model.addAttribute("URL", nextUrl);
		}
		System.out.println("result : "+result);
		return "redirect";
	}
}
