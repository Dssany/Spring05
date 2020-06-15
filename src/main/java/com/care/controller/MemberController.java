package com.care.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.service.JdbcContentServiceImpl;
import com.care.service.jdbcService;

@Controller
public class MemberController {
	private jdbcService jdbc;
	@RequestMapping("content")
	public String content(Model model) {
		jdbc = new JdbcContentServiceImpl();
		jdbc.execute(model);
		return "content";
	}
	
	@RequestMapping("save_view")
	public String save_view() {
		return "save_view";
	}
	
	@RequestMapping("save")
	public String save(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		return "redirect:content";
	}
	
	
	
	
}