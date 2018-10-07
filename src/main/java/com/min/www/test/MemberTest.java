package com.min.www.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.min.www.Service.member.MemberService;
import com.min.www.controller.MController;
import com.min.www.dao.member.MemberDao;
import com.min.www.dto.member.MemberDto;
import com.min.www.util.ParamFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "context-testContext.xml")
@WebAppConfiguration
public class MemberTest {
	
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	ParamFactory paramFactory;
	

	
	MemberDto member1;
	MemberDto member2;
	
	
	// session, request mock
//	MockHttpSession session;
//	MockHttpServletRequest request;
	
	@Autowired
//	private WebApplicationContext wac;
//	private MockMvc mockMvc; 
	
	@Before
	public void setUp() {
		member1 = new MemberDto(null, "testuser", "testNickname",
				"testPwd", "test@email", "testImageUrl", "testImageOriginalurl");
		
//		session = new MockHttpSession();
//		request = new MockHttpServletRequest();
//		
//		//이곳에서 HomeController를 MockMvc 객체로 만듭니다.
//		
//		
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//		

	}
	
//	@After
//	public void clear() {
//		session.clearAttributes();
//		session = null;
//	}
	
	@Test
	public void addAndGet() {
		
		
		memberDao.deleteAllMember();
		
		assertThat(memberDao.selectMemberCnt(), is(0));
		
		
		Map<String, Object> paramMap = 
				paramFactory.memberDtoFactory(member1);
		System.out.println(paramMap.get("id"));
		memberService.insertMember(paramMap);
		
		
		assertThat(memberDao.selectMemberCnt(), is(1));
		
		MemberDto DBResultMemberDto = 
				memberService.getMember(member1.getId());
		
		assertThat(member1.getNickname(), is(DBResultMemberDto.getNickname()));
		assertThat(member1.getImageurl(), is(member1.getImageurl()));
	}
	
	
	@Test
	public void loginTest() {
		memberDao.deleteAllMember();
		
		assertThat(memberDao.selectMemberCnt(), is(0));
		Map<String, Object> paramMap = 
				paramFactory.memberDtoFactory(member1);
		System.out.println(paramMap.get("id"));
		memberService.insertMember(paramMap);
		
		
		assertThat(memberDao.selectMemberCnt(), is(1));
		
		MemberDto DBResultMemberDto = 
				memberService.getMember(member1.getId());
		
		int reVal = memberService.memberLogin(paramMap);
		
		
		
		assertThat(reVal, is(not(0)));
		
//		System.out.println("session - loginuser : " + session.getAttribute("loginuser"));
		
		
		
	}
	
	@Test
	public void memberEditTest() {
		//비밀번호 수정
		member1.setPassword("1234");
		//이미지위치 수정
		member1.setImageurl("whereareyou");
		
		Map<String, Object> paramMap =
				paramFactory.memberDtoFactory(member1);
		
		memberService.memberEdit(paramMap);
		
		
		MemberDto DBResultMeberDto = 
				memberService.getMember(member1.getId());
		
		assertThat(DBResultMeberDto.getPassword(),
				is("1234"));
		assertThat(DBResultMeberDto.getImageurl(), is(member1.getImageurl()));
	}
	
//	@Test
//	public void memberControllerTest() throws Exception {
//		
//		Map<String, Object> paramMap = new HashMap<>();
//		
//		mockMvc.perform(MockMvcRequestBuilders.get("/member/login"));
		// HomeController의 "/" 매핑으로 정의합니다.
//		this.mockMvc.perform(get("/member/login"))
//		// 처리 내용을 출력합니다.
//		.andDo(print())
//		// 상태값은 OK가 나와야 합니다.
//		.andExpect(status().isOk())
//		// "serverTime"이라는 attribute가 존재해야 합니다.
//		.andExpect(model().attributeExists("boardList"));
		
//	}

}
