package com.min.www.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.www.Service.BoardService;
import com.min.www.dao.BoardDao;
import com.min.www.dto.BoardReplyDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "context-testContext.xml")
public class BoardReplyTest {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardDao boardDao;
	
	
	BoardReplyDto reply1;
	
	
	@Before
	public void setUp() {
		
		reply1 = new BoardReplyDto(reply_id, board_id, parent_id, depth, reply_content, reply_writer, reply_password, register_datetime, IMAGEURL, ORIGINALIMAGEURL)
		
	}

}
