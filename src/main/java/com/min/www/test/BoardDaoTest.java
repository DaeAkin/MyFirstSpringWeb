package com.min.www.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.www.Service.BoardService;
import com.min.www.dao.BoardDao;
import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardReplyDto;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "context-testContext.xml")
public class BoardDaoTest {

	@Autowired
	private BoardDao boardDao;
	
	
	@Autowired BoardService boardService;
	
	private BoardDto board1;
	private BoardDto board2;
	private BoardDto board3;
	
	private BoardReplyDto replyDto1;
	private BoardReplyDto replyDto2;

	Map<String, Object> paramMap;
	@Before
	public void setUp() {
		paramMap = new HashMap<>();
		board1 = new BoardDto(1, "안녕", "아무개", null, "하이하이", 5, null);
		board2 = new BoardDto(2, "하힛", "누굴까", null, "하이하이", 3, null);
		board3 = new BoardDto(3, "룰루", "랄라", null, "하이하이", 100, null);

		replyDto1 = new BoardReplyDto("1", "1", "1", "1", "hihi", "아무개", null, null, null, null);
		
		paramMap.put("id", board1.getId());
		paramMap.put("title", board1.getTitle());
		paramMap.put("writer", board1.getWriter());
		paramMap.put("smarteditor", board1.getContent());
	
	}
	
		
	
	@Test
	public void boardAddAndGet() {

		boardDao.deleteAllContent();
		boardDao.regContent(paramMap);
			
		BoardDto DBdata = boardDao.getBoardOne(board1.getId());
		
		assertThat(DBdata.getTitle(), is("안녕"));
		
		System.out.println("갯수 :" + boardDao.getContentCtn(paramMap));

	}
	
	
	@Test
	public void boardEdit() {
		Map<String, Object> boardEditParam = new HashMap<>();
		boardEditParam.put("id", board1.getId());
		
		boardEditParam.put("title", "hihi");
		boardEditParam.put("writer", board1.getWriter());
		boardEditParam.put("smarteditor", "hello");
		
		boardDao.regContent(boardEditParam);
		
		BoardDto DBData = boardDao.getBoardOne(board1.getId());
		
		assertThat(DBData.getTitle(), is("hihi"));
		assertThat(DBData.getContent(), is("hello"));
		
	}
	
	
	@Test
	public void replyAddAndGet() {
		boardDao.deleteAllReply();
		
		Map<String, Object> replyParam = new HashMap<>();
		replyParam.put("reply_id", replyDto1.getReply_id());
		replyParam.put("board_id", replyDto1.getBoard_id());
		replyParam.put("parent_id", replyDto1.getParent_id());
		replyParam.put("depth", replyDto1.getDepth());
		replyParam.put("reply_content", replyDto1.getReply_content());
		replyParam.put("reply_writer", replyDto1.getReply_writer());
		
		boardDao.regReply(replyParam);
		
		
		
		BoardReplyDto DBData = 
				boardDao.getBoardReplyOne(
						replyDto1.getReply_id());
		
		
		assertThat(replyDto1.getReply_id(), is(DBData.getReply_id()));
						
		
	}
	
	
	@Test
	public void replyEditTest() {
		
		Map<String, Object> editParam = new HashMap<>();
		editParam.put("reply_id", replyDto1.getReply_id());
		editParam.put("board_id", replyDto1.getBoard_id());
		editParam.put("parent_id", replyDto1.getParent_id());
		editParam.put("depth", replyDto1.getDepth());
		editParam.put("reply_content", "수정했습니다!");
		editParam.put("reply_writer", replyDto1.getReply_writer());
		
		
		boardDao.regReply(editParam);
		
		BoardReplyDto DBData = 
				boardDao.getBoardReplyOne(
						replyDto1.getReply_id());
		
		assertThat(DBData.getReply_content(), is("수정했습니다!"));
		
		
	}
	
	
	
	
	
	
	
	
}
