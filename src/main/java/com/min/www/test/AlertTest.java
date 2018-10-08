package com.min.www.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.www.Service.BoardService;
import com.min.www.dao.BoardDao;
import com.min.www.dto.BoardAndAlertJoinDto;
import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardReplyDto;
import com.min.www.util.ParamFactory;

// 사용자가 쓴 게시물에 댓글이 달리면 알람 카운터가 올라가는 테스트.
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

		boardReplyDto1 = new BoardReplyDto( "1", "0",
				"0", "hi1", "who?", "1234", null, "12.png", "123.png");
		boardReplyDto2 = new BoardReplyDto( "1", "0",
				"0", "hi2", "who?", "1234", null, "23.png", "234.png");
		boardReplyDto3 = new BoardReplyDto( "1", "0",
				"0", "hi3", "who?", "1234", null, "34.png", "456.png");

		boardReplyParam1 = paramFactory.boardDtoReplyFactory(boardReplyDto1);

		boardReplyParam2 = paramFactory.boardDtoReplyFactory(boardReplyDto2);

		boardReplyParam3 = paramFactory.boardDtoReplyFactory(boardReplyDto3);

	}

	// 게시판 DTO 주입
	@Before
	public void boardDtoSetup() {
		ParamFactory paramFactory = new ParamFactory();

		boardDto = new BoardDto(1, "test!!", "DaeAkin",
				null, "contentArea", 0);

		boardParam = paramFactory.boardDtoFactory(boardDto);

	}

	//
	// @Test
	// public void setUpAddAndGet() {
	// boardService.deleteAllBoard();
	// boardDao.deleteAllReply();
	//
	// assertThat(boardDao.getBoardCnt(), is(0));
	//
	// assertThat(boardDao.getBoardReplyCnt(), is(0));
	//
	// boardService.regReply(boardReplyParam1);
	//
	// boardDao.regContent(boardParam);
	//
	// assertThat(boardDao.getBoardReplyCnt(), is(1));
	//
	// assertThat(boardDao.getBoardCnt(), is(1));
	//
	//
	// }

	// 컨트롤러에서 글쓴이만 붙여주면됨 session 이용하기. 
	@Test
	public void boardAlertTest() {
		// 모든 게시물 삭제
		boardService.deleteAllBoard();
		// 모든 댓글 삭제
		boardDao.deleteAllReply();
		// 모든 알람 삭제
		boardDao.deleteAllBoardAlert();

		// 다 삭제 됬는지 확인 작업
		assertThat(boardDao.getBoardCnt(), is(0));
		assertThat(boardDao.getBoardReplyCnt(), is(0));
		// 게시글 등록 게시글 번호 : 1
		boardDao.regContent(boardParam);
		// 게시글 번호1에 달린 댓글 3개 등록
	 boardService.regReply(boardReplyParam1);
		
		System.out.println("reply_id 값 : " +
		boardReplyDto1.getReply_id());
		boardService.regReply(boardReplyParam2);
		boardService.regReply(boardReplyParam3);
		
		//댓글 한개를 전부 검사해보기 (전부 다 등록 되어있나)
//		BoardReplyDto replydto = boardService.
//				getOneReply(String.valueOf(reply_id));
		// 대표로 boardReplyParam1을 가져올 것이다. 
		// 검사 
//			checkReplyValid(replydto, boardReplyDto1);
		
		
		

		// 전체 게시물이 1개인지 확인
		assertThat(boardDao.getBoardCnt(), is(1));
		// 전체 댓글이 3개인지 확인
		assertThat(boardDao.getBoardReplyCnt(), is(3));

		// 댓글갯수와 알람갯수가 일치해야 함
		assertThat(boardDao.boardAlertCnt(), is(boardDao.getBoardReplyCnt()));

		// 글쓴이 가져오기
		String writer = boardDto.getWriter();
		
		if(writer == null) {
			throw new RuntimeException();
		}
		System.out.println("글쓴이 :" +writer);
		// Map<String, Object> paramMap = new HashMap<>();
		// paramMap.put("writer", boardDto.getWriter());

		// 조인테이블 DTO 생성 하였음 글쓴이 대입해서 알람 갯수 몇개인지 체크
		List<BoardAndAlertJoinDto> BoardAndAlertJoinDtos = boardService.getAlerts(writer);

		// 알려줘야 할 알림 갯수 확인 ( 읽은거나 읽지 않은거나 )
//		System.out.println("사이즈 :  " + BoardAndAlertJoinDtos.size());
		 assertThat(BoardAndAlertJoinDtos.size(), is(3));

		// 댓글을 확인했으면 alert를 1로 변경해주는 작업
		for (BoardAndAlertJoinDto boardAndAlertJoinDto : BoardAndAlertJoinDtos) {
			boardDao.checkingTheBoardReply(boardAndAlertJoinDto.getAlert_id());
		}

		BoardAndAlertJoinDtos = boardDao.getAlerts(writer);

		// 모든 댓글의 alert 값이 1로 변경됬는지 확인해주는 작업
		for (BoardAndAlertJoinDto boardAndAlertJoinDto : BoardAndAlertJoinDtos) {
			assertThat(boardAndAlertJoinDto.getIsalerted(), is(1));
		}

	}
	public void checkReplyValid(BoardReplyDto DBReplyDto,
			BoardReplyDto replyDto) {
		assertThat(DBReplyDto.getReply_content(),
				is(replyDto.getReply_content()));
		
		assertThat(DBReplyDto.getIMAGEURL(),
				is(replyDto.getIMAGEURL()));
		
		assertThat(DBReplyDto.getORIGINALIMAGEURL(),
				is(replyDto.getORIGINALIMAGEURL()));
		
		assertThat(DBReplyDto.getReply_writer(),
				is(replyDto.getReply_writer()));
		

		
	}
	
	
	
	

}
