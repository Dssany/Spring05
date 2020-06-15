package com.care.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.jdbc_dao.JdbcDAO;

public class JdbcDeleteServiceImpl implements jdbcService{

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		String id = (String)map.get("id");
		JdbcDAO dao = new JdbcDAO();
		dao.delete(id);
		
		
		
	}

}
