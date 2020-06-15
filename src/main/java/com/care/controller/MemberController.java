package com.care.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.service.JdbcContentServiceImpl;
import com.care.service.JdbcModifyServiceImpl;
import com.care.service.JdbcSaveServiceImpl;
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
		model.addAttribute("request", request);//map 으로 뽑아올시에 앞의값이 키값, 뒷값이 value가된다
		jdbc = new JdbcSaveServiceImpl();
		jdbc.execute(model);
		return "redirect:content";
	}
	
	@RequestMapping("modify")
	public String modify(@RequestParam String id,Model model) {
		model.addAttribute("id",id);
		jdbc = new JdbcModifyServiceImpl();
		jdbc.execute(model);
		return "modify";
	}
	
	
}