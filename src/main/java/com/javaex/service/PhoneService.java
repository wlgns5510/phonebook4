package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Service
public class PhoneService {
	
	//필드
	@Autowired
	private PhoneDao phoneDao;
	//생성자
	
	//메소드 gs
	
	//메소드 일반
	
	//전화번호 리스트
	public List<PersonVo> getPersonList(){
		//코드작성
		List<PersonVo> personList = phoneDao.getPersonList();
		
		return personList;
	}
	
	//전화번호 등록
	public int personInsert(PersonVo personVo) {
		
		int count = phoneDao.personInsert(personVo);
		
		return count;
	}
	
	//전화번호 등록(map 사용)
	public int personInsert2() {
		System.out.println("PhoneService>>personInsert2()");
		
		//map을 만들어 사용한다
		Map<String, String> pMap = new HashMap<String, String>();
		pMap.put("name", "황일영");
		pMap.put("hp", "010-1111-1111");
		pMap.put("company", "02-222-2222");
		
		int count = phoneDao.personInsert2(pMap);
		return count;
	}
	
	//전화번호 삭제
	public int personDelete(int no) {
		
		int count = phoneDao.personDelete(no);
		return count;
	}
	
	//전화번호 수정폼(정보가져오기)
	public PersonVo getPerson(int no) {
		
		PersonVo personVo = phoneDao.getPerson(no);
		
		return personVo;
	}
	
	//전화번호 수정폼(정보가져오기, map방식)
	public PersonVo getPerson2(int no) {
		
	}
	
	//전화번호 수정
	public int personUpdate(PersonVo personVo) {
		
		int count = phoneDao.personUpdate(personVo);
		
		return count;
	}

}
