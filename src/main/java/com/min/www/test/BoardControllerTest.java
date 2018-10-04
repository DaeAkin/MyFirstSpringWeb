package com.min.www.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.min.www.Service.BoardService;
import com.min.www.controller.BoardController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "context-testContext.xml")
public class BoardControllerTest {
	
	

	
	

	
	@Autowired
	BoardService boardService;
	
	
	private MockMvc mockMvc; 

	// 테스트 메소드 실행전 셋업 메소드입니다.
	@Before
	public void setup(){
		// 이곳에서 HomeController를 MockMvc 객체로 만듭니다.
		this.mockMvc = MockMvcBuilders.standaloneSetup(new BoardController()).build();
	}

	
	@Test
	public void test() throws Exception{
		
		System.out.println("hi");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("searchArea", "hi");
		// HomeController의 "/" 매핑으로 정의합니다.
		this.mockMvc.perform(get("/ex"))
		// 처리 내용을 출력합니다.
		.andDo(print())
		// 상태값은 OK가 나와야 합니다.
		.andExpect(status().isOk())
		// "serverTime"이라는 attribute가 존재해야 합니다.
		.andExpect(model().attributeExists("boardList"));
	}
	
	
}
