package com.min.www.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.www.Service.BoardService;
import com.min.www.Service.BoardServiceImpl;
import com.min.www.dao.BoardDao;
import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardOptionsDto;
import com.min.www.dto.BoardReplyDto;
import com.min.www.util.TimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "context-testContext.xml")
public class BoardServiceTest {

	@Autowired
	BoardDao boardDao;

	@Autowired
	BoardService boardService;

	@Autowired
	BoardService testBoardService;

	BoardDto board1;
	BoardDto board2;

	BoardOptionsDto option1;

	@Before
	public void setUp() {

		board1 = new BoardDto(1, "test2", "whois", "2017-08-02", "contentArea", 2);

		option1 = new BoardOptionsDto(1, "testId", null);
	}

//	@Test
//	public void advisorAutoProxyCreator() {
//		// 프록시로 변경된 오브젝트인지 확인하기
//		assertThat(testBoardService, is(java.lang.reflect.Proxy.class));
//	}

	
	@Test
	public void regBoardAgreeTest() {
		// DB Data 전부 삭제
		boardService.deleteAllBoardAgree();
		// 맵핑
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("boardid", option1.getBoardId());
		paramMap.put("agree", option1.getAgree());
		paramMap.put("disagree", option1.getDisagree());

		// DB 한개 추가
		boardService.insertBoardAgree(paramMap);

		// DB뽑아오기
		BoardOptionsDto insertedBoardAgree = 
				boardService.getBoardAgreeAndDisagreeOne(option1.getBoardId());

		// 일치하는지 확인
		assertThat(insertedBoardAgree.getAgree(), is(option1.getAgree()));

		// 좋아요 못누름
		assertThat(boardService.isCanAgreeWithBoard(paramMap), is(false));

		// 맵핑2
		Map<String, Object> paramMap2 = new HashMap<>();

		paramMap2.put("boardid", 1);
		paramMap2.put("agree", "kei890");

		// 좋아요 누를수 있음
		assertThat(boardService.isCanAgreeWithBoard(paramMap2), is(true));
	}
	
	//transaction Test
	@Test
	public void insertBoardAgreeTransactionTest() {

		// DB Data 전부 삭제
		boardService.deleteAllBoardAgree();
		// 맵핑
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("boardid", option1.getBoardId());
		paramMap.put("agree", option1.getAgree());
		paramMap.put("disagree", option1.getDisagree());

		// DB 한개 추가
		boardService.insertBoardAgree(paramMap);

		// DB뽑아오기
		BoardOptionsDto insertedBoardAgree = boardService.getBoardAgreeAndDisagreeOne(option1.getBoardId());

		// 일치하는지 확인
		assertThat(insertedBoardAgree.getAgree(), is(option1.getAgree()));
		
		// 수정하기
//		try {
//			// 중복된 좋아요를 DB에 넣어주고
//			boardService.insertBoardAgree(paramMap);
//			if(boardService.isCanAgreeWithBoard(paramMap)) {
//				throw new TestBoardServiceException();
//			}
//		} catch (TestBoardServiceException e) {
//			// TODO: handle exception
//		}
		
		

	}
	

	/*
	 * DB에서 수동으로 시간조작해서 테스트 해야함.
	 */
	@Test
	public void timerTest() {

		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("start", 0);
		paramMap.put("end", 10);
		List<BoardDto> boards = boardService.getContentList(paramMap);

		for (BoardDto boardDto : boards) {

			System.out.println(boardDto.getWritetime());
		}
	}

	static class TestBoardServiceImpl extends BoardServiceImpl {

		private String agreeId = "kei890";

		@Override
		public Boolean isCanAgreeWithBoard(Map<String, Object> paramMap) {
			// TODO Auto-generated method stub
			if (paramMap.get("agree").equals(agreeId))
				throw new TestBoardServiceException();
			return super.isCanAgreeWithBoard(paramMap);
		}
	}

	static class TestBoardServiceException extends RuntimeException {

	}

}
