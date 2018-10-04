package com.min.www.test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.bridge.MessageWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistrar;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.www.Service.BoardService;
import com.min.www.dao.BoardDao;
import com.min.www.dto.BoardAndAlertJoinDto;
import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardReplyDto;
import com.min.www.util.ParamFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "context-testContext.xml")
public class AlertTest {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardDao boardDao;
	
	private BoardDto boardDto;
	
	private BoardReplyDto boardReplyDto1;
	
	private BoardReplyDto boardReplyDto2;
	
	private BoardReplyDto boardReplyDto3;
	
	
	private Map<String, Object> boardParam;
	
	private Map<String, Object> boardReplyParam1;
	
	private Map<String, Object> boardReplyParam2;
	
	private Map<String, Object> boardReplyParam3;
	// 댓글 DTO 주입
	@Before
	public void boardRelyDtoSetup() {
		ParamFactory paramFactory = new ParamFactory();
		
		boardReplyDto1 = new BoardReplyDto("1", "1", "0", "0",
				"hi1", "who?", "1234", null, null, null);
		boardReplyDto2 = new BoardReplyDto("2", "1", "0", "0",
				"hi2", "who?", "1234", null, null, null);
		boardReplyDto3 = new BoardReplyDto("3", "1", "0", "0",
				"hi3", "who?", "1234", null, null, null);
		
		boardReplyParam1 = 
				paramFactory.boardDtoReplyFactory(boardReplyDto1);
		
		boardReplyParam2 = 
				paramFactory.boardDtoReplyFactory(boardReplyDto2);
		
		boardReplyParam3 = 
				paramFactory.boardDtoReplyFactory(boardReplyDto3);
		
		
		
		
	}
	
	
	//게시판 DTO 주입
	@Before
	public void boardDtoSetup() {
		ParamFactory paramFactory = new ParamFactory();
		
		boardDto = new BoardDto(1, "test!!",
				"DaeAkin", null, "contentArea", 0);
		
		boardParam = 
				paramFactory.boardDtoFactory(boardDto);
		
			
		
	}
	
//	
//	@Test
//	public void setUpAddAndGet() {
//		boardService.deleteAllBoard();
//		boardDao.deleteAllReply();
//		
//		assertThat(boardDao.getBoardCnt(), is(0));
//		
//		assertThat(boardDao.getBoardReplyCnt(), is(0));
//		
//		boardService.regReply(boardReplyParam1);
//		
//		boardDao.regContent(boardParam);
//		
//		assertThat(boardDao.getBoardReplyCnt(), is(1));
//		
//		assertThat(boardDao.getBoardCnt(), is(1));
//		
//		
//	}
	
	
	@Test
	public void boardAlertTest() {
		
		boardService.deleteAllBoard();
		boardDao.deleteAllReply();
		boardDao.deleteAllBoardAlert();
		
		assertThat(boardDao.getBoardCnt(), is(0));
		assertThat(boardDao.getBoardReplyCnt(), is(0));
		
		boardService.regReply(boardReplyParam1);
		boardService.regReply(boardReplyParam2);
		boardService.regReply(boardReplyParam3);
		boardDao.regContent(boardParam);
		
		assertThat(boardDao.getBoardReplyCnt(), is(3));
		assertThat(boardDao.getBoardCnt(), is(1));
		
		assertThat(boardDao.boardAlertCnt(),
				is(boardDao.getBoardReplyCnt()));
		
		String writer = boardDto.getWriter();
		
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("writer", boardDto.getWriter());
		
		List<BoardAndAlertJoinDto> BoardAndAlertJoinDtos =
				boardDao.getAllBoardReplys(writer);
		
		// 알려줘야 할 알림 갯수 확인
		assertThat(BoardAndAlertJoinDtos.size(), is(3));
		
		for (BoardAndAlertJoinDto boardAndAlertJoinDto : BoardAndAlertJoinDtos) {
			boardDao.checkingTheBoardReply(boardAndAlertJoinDto.getAlert_id());
		}
		
	 BoardAndAlertJoinDtos =
				boardDao.getAllBoardReplys(writer);
		
		for (BoardAndAlertJoinDto boardAndAlertJoinDto : BoardAndAlertJoinDtos) {
			assertThat(boardAndAlertJoinDto.getIsalerted(), is(1));
		}
	 
	 
		
		
	}
	
	

	

}
