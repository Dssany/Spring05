package com.care.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.jdbc_dao.JdbcDAO;

public class JdbcSaveServiceImpl implements jdbcService{

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();//모델은 asmap형태로 뽑아올수 있다.
		HttpServletRequest re = (HttpServletRequest)map.get("request");
		String id = re.getParameter("id");
		String name = re.getParameter("name");
		JdbcDAO dao = new JdbcDAO();
		dao.save(id,name);
			
		
		
		
	}

}
