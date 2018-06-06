package com.min.www.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.www.Service.IFBDao;

@Controller
public class MainController {
	
	@Autowired
	private SqlSession sqlSession;
	

	
	@RequestMapping("/")
	public String hello() {
		System.out.println("main 호출()");
		return "content";
	}
	
	
	@RequestMapping("/freeboard")
	public String freeboard(Model model) {
		IFBDao dao = sqlSession.getMapper(IFBDao.class);
	
		model.addAttribute("freeboard",dao.selectfreeboard());

		return "freeboard";
	}
	
	@RequestMapping("/freeboardwriteView")
	public String freeboardwriteView() {
		
		return "freeboardwrite";
	}
	

	
}
