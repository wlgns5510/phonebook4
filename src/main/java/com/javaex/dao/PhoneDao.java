package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository //Autowired에 연결
public class PhoneDao {
	
	@Autowired
	private SqlSession sqlSession; //미리 여러개를 연결해놈
	
	@Autowired
	private DataSource dataSource;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	
	private void getConnection() {
		try {
			//Class.forName(driver);
			//conn = DriverManager.getConnection(url, id, pw);
			conn = dataSource.getConnection();
		
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	private void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	//등록
	public int personInsert(PersonVo personVo) {
		System.out.println("PhoneDao>>personInsert");
		
		int count = sqlSession.insert("phonebook.personInsert", personVo);
		
		return count;
	}
	
	//수정	
	public int personUpdate(PersonVo personVo) {
		System.out.println("PhoneDao>>personInsert");
		
		int count = sqlSession.update("phonebook.personUpdate", personVo);
		
		return count;
	}
	
	//삭제
	public int personDelete(int personId) {
		System.out.println("PhoneDao>>personInsert");
		
		int count = sqlSession.delete("phonebook.personDelete", personId);
		return count;
	}
	
	//리스트
	public List<PersonVo> getPersonList() {
		System.out.println("PhoneDao>>getPersonList");
		
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		System.out.println(personList);
		
		return personList;
	}
		
	public PersonVo getPerson(int pId) {
		PersonVo pVo = new PersonVo();
		
		try {
			getConnection();
			
			String query = "";
			query += " select	person_id,";
			query += " 			name,";
			query += " 			hp,";
			query += " 			company";
			query += " from		person";
			query += " where		person_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			if(pId != 0) {
				pstmt.setInt(1, pId);
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pVo.setPersonId(pId);
				pVo.setName(rs.getString("name"));
				pVo.setHp(rs.getString("hp"));
				pVo.setCompany(rs.getString("company"));
			}
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return pVo;
	}
}