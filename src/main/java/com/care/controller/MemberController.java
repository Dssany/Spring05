package com.care.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.service.JdbcContentServiceImpl;
import com.care.service.JdbcDeleteServiceImpl;
import com.care.service.JdbcModifySaveServiceImpl;
import com.care.service.JdbcModifyServiceImpl;
import com.care.service.JdbcSaveServiceImpl;
import com.care.service.jdbcService;
import com.care.template.Constant;

@Controller
public class MemberController {
	
	public MemberController() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		JdbcTemplate template = ctx.getBean("template",JdbcTemplate.class);//template.xml 객체를 생성해서 저장
		Constant.template = template;//스태틱 변수에저장
		System.out.println("====멤버 컨트롤러 실행 ====");
	}
	
	
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
	
	@RequestMapping("modifySave")
	public String modifySave(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		jdbc = new JdbcModifySaveServiceImpl();
		jdbc.execute(model);
		return "redirect:content";
	}
	
	@RequestMapping("delete")
	public String delete(@RequestParam String id, Model model) {
		model.addAttribute("id",id);
		jdbc = new JdbcDeleteServiceImpl();
		jdbc.execute(model);
		return "redirect:content";
	}
	
	
	
	
	
	
	
	
	
}