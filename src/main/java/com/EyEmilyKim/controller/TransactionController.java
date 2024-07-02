package com.EyEmilyKim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tran/")
@Controller
public class TransactionController {

	@GetMapping("listAll")
	public String listAll() {
		System.out.println("TransactionController > listAll()@Get called");
		return "tran.listAll";
	}
	
	@GetMapping("add")
	public String add() {
		System.out.println("TransactionController > add()@Get called");
		return "tranAdd.add";
	}
}
