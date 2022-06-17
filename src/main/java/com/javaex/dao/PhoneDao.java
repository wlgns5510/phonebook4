package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository //Autowired에 연결
public class PhoneDao {
	
	@Autowired
	private SqlSession sqlSession; //미리 여러개를 연결해놈
	

	//등록
	public int personInsert(PersonVo personVo) {
		System.out.println("PhoneDao>>personInsert");
		
		int count = sqlSession.insert("phonebook.personInsert", personVo);
		
		return count;
	}
	
	//등록(map)
	public int personInsert2(Map<String, String> pMap) {
		System.out.println("PhoneDao>>personInsert2");
		
		//key값
		pMap.get("name");
		pMap.get("hp");
		pMap.get("company");
		int count = sqlSession.insert("phonebook.personInsert2", pMap);
		
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
	//한사람 가져오기	
	public PersonVo getPerson(int personId) {
		System.out.println("PhoneDao>>getPerson");
		
		return sqlSession.selectOne("phonebook.getPerson",personId);
		
	}
	//한사람 가져오기(map)
	public PersonVo getPerson2(int personId) {
		System.out.println("PhoneDao > getPerson2()");
		
		Map<String,Object> pMap = sqlSession.selectOne("phonebook.getPerson2",personId);
		System.out.println(pMap.toString());
		
		return null;
		
		
	}
}