package com.care.jdbc_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.care.jdbc_dto.JdbcDTO;
import com.care.template.Constant;

public class JdbcDAO {
	/*
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "spring";
	private String pwd = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	*/
	private JdbcTemplate template;
	
	public JdbcDAO() {
		this.template = Constant.template;
		/*
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public ArrayList<JdbcDTO> list(){
		String sql = "select * from test_jdbc order by id desc";
		ArrayList<JdbcDTO> list = null;
		
		try {
			list = (ArrayList<JdbcDTO>)template.query(sql, new BeanPropertyRowMapper<JdbcDTO>(JdbcDTO.class));
		} catch (Exception e) {
			e.printStackTrace();//이렇게 작성하는게 더 효율적이다
		}
			
		
		
		return list;
		/*
		ArrayList<JdbcDTO> list = new ArrayList<JdbcDTO>();
		try {
			con = DriverManager.getConnection(url,user,pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				JdbcDTO dto = new JdbcDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		*/
	}
	
	
	public void save(final String id, final String name) {//변경할수 없게 final 선언 
		String sql = "insert into test_jdbc(id,name) values(?,?)";
		//"insert into test_jdbc(id,name) values('"+id+"','"+name+"')"; 이렇게 쓰면 template 사용가능
		template.update(sql,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, id);
				ps.setString(2, name);
				
			}});
		
		
		
		
		
		
//		try {
//			con = DriverManager.getConnection(url,user,pwd);
//			ps = con.prepareStatement(sql);
//			ps.setString(1, id);
//			ps.setString(2, name);
//			ps.executeUpdate();
//			
//		} catch (Exception e) {
//		}
	}
	
	public JdbcDTO modify(String id) {
		//String sql = "select * from test_jdbc where id=?";
		String sql = "select * from test_jdbc where id=" + id;
		JdbcDTO dto =null;
		dto = template.queryForObject(sql, new BeanPropertyRowMapper<JdbcDTO>(JdbcDTO.class));
		//하나의 값을 얻어오기 위해 queryForObject를 사용
		
		return dto;
//		try {
//			con = DriverManager.getConnection(url,user,pwd);
//			ps = con.prepareStatement(sql);
//			ps.setString(1, id);
//			rs = ps.executeQuery();
//			if(rs.next()) {
//				dto = new JdbcDTO();
//				dto.setId(rs.getInt("id"));
//				dto.setName(rs.getString("name"));
//			}
//		} catch (Exception e) {
//			
//		}
		
	}
	
	public void modifySave(String id,String name) {
		String sql = "update test_jdbc set name=? where id=?";
		//람다식 표현
		template.update(sql,ps->{
			ps.setString(1, name);
			ps.setString(2, id);
		});
		
		
		/*
		try {
			con = DriverManager.getConnection(url,user,pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, Integer.parseInt(id));
			ps.executeUpdate();
		} catch (Exception e) {
			
		}
		*/
	}
	
	
	
	public void delete(String id) {
		//String sql = "delete from test_jdbc where id=?";
		String sql = "delete from test_jdbc where id=" + id;
		template.update(sql);
		
		
//		try {
//			con = DriverManager.getConnection(url,user,pwd);
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, Integer.parseInt(id));
//			ps.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}
	
	public int count() {
		String sql = "select count(*) from test_jdbc";
		return template.queryForObject(sql, Integer.class);
	}
	
	
	
	
	
	
}
