package com.care.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.jdbc_dao.JdbcDAO;

public class JdbcModifySaveServiceImpl implements jdbcService{

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest re = (HttpServletRequest)map.get("request");
		JdbcDAO dao = new JdbcDAO();
		dao.modifySave(re.getParameter("id"),re.getParameter("name"));
		
	}
	
}
