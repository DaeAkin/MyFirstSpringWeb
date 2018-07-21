package com.min.www.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.www.Service.BoardService;
import com.min.www.dao.BoardDao;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "context-testContext.xml")
public class BoardDaoTest {

	@Autowired
	private BoardDao BoardDao;
	
	
	@Autowired BoardService boardService;
	
	@Test
	public void test() {
		System.out.println("hi");
	}
	
}
