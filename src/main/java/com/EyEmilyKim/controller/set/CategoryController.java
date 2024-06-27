package com.EyEmilyKim.controller.set;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EyEmilyKim.entity.Category;
import com.EyEmilyKim.service.CategoryService;

@RequestMapping("/set/category/")
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	private String failMsg = "해당 기능이 준비되지 않아 처리하지 못했습니다.";
	private String succMsg = "정상적으로 처리되었습니다.";
	private String nextUrl = "/set/category/list";
	
	@Autowired
	private HttpSession httpSession;
	
	/*-------- 카테고리 목록 --------*/
	
	@RequestMapping("list")
	public String list(Model model) {
		System.out.println("CategoryController > list() called");
		
		String id = (String) httpSession.getAttribute("USER_ID");
		if(id == null || id == "") id = "master";
		System.out.println("id : "+id);
		
		List<Category> list = categoryService.getList(id);
		model.addAttribute("LIST", list);
		int cnt = categoryService.getCount(id);
		model.addAttribute("COUNT", cnt);
		
		return "set.category.list";
	}
	
	/*-------- 카테고리 삭제 --------*/
	
	@GetMapping("del")
	public String del(String CCODE, Model model) {
		System.out.println("CategoryController > del()@Get called");
		System.out.println(CCODE);
		
		Category cate = categoryService.getOne(CCODE);
		System.out.println(cate.getCate_code()+" - "+cate.getCate_name());
		model.addAttribute("C", cate);
		return "set.category.del";
	}
	
	@PostMapping("del")
	public String del(String CCODE, Model model, String CNAME) {
		System.out.println("CategoryController > del()@Post called");
		System.out.println(CCODE+" - "+CNAME);
		
		int result = 0;
		try {
			result = categoryService.delete(CCODE);
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
	
	/*-------- 카테고리 추가 --------*/
	
	@GetMapping("add")
	public String add(Model model) {
		System.out.println("CategoryController > add()@Get called");
		String id = (String) httpSession.getAttribute("USER_ID");
		if(id == null) id = "master";
		System.out.println("id : "+id);
		
		List<String> list = categoryService.getNameList(id);
		model.addAttribute("LIST", list);
		int maxSqn = categoryService.getMaxSqn();
		System.out.println("maxSqn : "+maxSqn);
		model.addAttribute("MSN", maxSqn);
		return "set.category.add";
	}
	
	@PostMapping("add")
	public String add(@RequestParam Map<String,String> fm, Model model) {
		System.out.println("CategoryController > add()@Post called");
		for(Map.Entry<String,String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		String id = (String) httpSession.getAttribute("USER_ID");
		if(id == null) id = "master";
		System.out.println("id : "+id);
		
		Category cate = new Category();
		cate.setSeqno(Integer.parseInt(fm.get("SEQNO")));
		cate.setInex(fm.get("INEX"));
		cate.setCate_code(fm.get("INEX")+fm.get("SEQNO"));
		cate.setCate_name(fm.get("CNAME"));
		cate.setId(id);
		
		int result = 0;
		try {
			result = categoryService.insert(cate);
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
	
	/*-------- 카테고리 수정 --------*/ 
	
	@GetMapping("upd")
	public String upd(String CCODE, Model model) {
		System.out.println("CategoryController > upd()@Get called");
		System.out.println(CCODE);

		String id = (String) httpSession.getAttribute("USER_ID");
		if(id == null) id = "master";
		System.out.println("id : "+id);
		
		Category cate = categoryService.getOne(CCODE);
		System.out.println(cate.getCate_code()+" - "+cate.getCate_name());
		model.addAttribute("C", cate);
		List<String> list = categoryService.getNameList(id);
		model.addAttribute("LIST", list);
		return "set.category.upd";
	}
	
	@PostMapping("upd")
	public String upd(@RequestParam Map<String,String> fm, Model model) {
		System.out.println("CategoryController > upd()@Post called");
		for(Map.Entry<String,String> entry : fm.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}		
		System.out.println(fm.get("CCODE")+" - "+fm.get("CNAME")+" -> "+fm.get("N_CNAME"));
		
		Category cate = new Category();
		cate.setCate_code(fm.get("CCODE"));
		cate.setCate_name(fm.get("N_CNAME"));

		int result = 0;
		try {
			result = categoryService.update(cate);
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
