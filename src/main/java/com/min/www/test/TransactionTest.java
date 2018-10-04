package com.min.www.test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "context-testContext.xml")
public class TransactionTest {

	
	@Autowired
	BoardDao boardDao;
	
	@Autowired
	BoardService boardService;
	
	
	@Autowired
	BoardService testBoardService;
	

	
	private BoardDto board1;
	
	private BoardOptionsDto option1;
	@Before
	public void setUp() {
		
		board1 = new BoardDto(1, "test2", "whois", "2017-08-02",
				"contentArea", 2);
		
		option1 = new BoardOptionsDto(1,"testId",null);
		
	}
	
	
	@Test
	
	public void regContentTransactionTest() {
		
		boardService.deleteAllBoard();
		
		assertThat(boardService.getBoardCnt(), is(0));
		
		Map<String,Object> paramMap = new HashMap<>();
		HttpServletRequest request = null;
		paramMap.put("id", board1.getId());
		paramMap.put("title", board1.getTitle());
		paramMap.put("writer", board1.getWriter());
		paramMap.put("smarteditor", board1.getContent());

	
		
		try {
			boardService.regContent(paramMap, request);
			assertThat(boardService.getBoardCnt(), is(1));
			
//			BoardDto selectBoard = boardService.getContentView(paramMap);
			
//			assertThat(selectBoard.getTitle(), is(board1.getTitle()));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	@Test
	public void regBoardAgreeAndDisagreeTest() {
		// DB Data 전부 삭제
		boardService.deleteAllBoardAgree();
		//맵핑
		 Map<String, Object> paramMap = new HashMap<>();
		 
		 paramMap.put("boardid", option1.getBoardId());
		 paramMap.put("agree", option1.getAgree());
		 paramMap.put("disagree", option1.getDisagree());
		 
		 //DB 한개 추가
		 boardService.insertBoardAgree(paramMap);
		
		 //DB뽑아오기
		 BoardOptionsDto insertedBoardAgree =
				 boardService.getBoardAgreeAndDisagreeOne(option1.getBoardId());
		 
		 //일치하는지 확인 
		 assertThat(insertedBoardAgree.getAgree(), is(option1.getAgree()));
		
		 //좋아요 못누름
		 assertThat(boardService.isCanAgreeWithBoard(paramMap), 
				 is(false));
		 
		 
		 //맵핑2 
		 Map<String, Object> paramMap2 = new HashMap<>();
		 
		 paramMap2.put("boardid", 1);
		 paramMap2.put("agree", "kei890");
		 
		 // 좋아요 누를수 있음
		 assertThat(boardService.isCanAgreeWithBoard(paramMap2), 
				is(true));
	
	}
	
	
	
	@Test
	public void insertBoardAgreeTransactionTest() {
		// DB Data 전부 삭제
		boardService.deleteAllBoardAgree();
		
		//맵핑
		 Map<String, Object> paramMap = new HashMap<>();
		 
		 paramMap.put("boardid", option1.getBoardId());
		 paramMap.put("agree", option1.getAgree());
		 paramMap.put("disagree", option1.getDisagree());
		 
		 //DB 한개 추가
		 boardService.insertBoardAgree(paramMap);
		
		 //DB뽑아오기
		 BoardOptionsDto insertedBoardAgree =
				 boardService.getBoardAgreeAndDisagreeOne(option1.getBoardId());
		 
		 //일치하는지 확인 
		 assertThat(insertedBoardAgree.getAgree(), is(option1.getAgree()));
	}
	
	


	
	
	
	static class TestBoardService extends BoardServiceImpl {
		
		private String title = "test2";
		//  rollback test
		@Override
		public int regContent(Map<String, Object> paramMap, HttpServletRequest request) throws Exception {
			// TODO Auto-generated method stub
//			if(paramMap.get("title").equals(title)) {
//				System.out.println("???");
//				System.out.println(paramMap.get("title"));
//				throw new TestBoardServiceException();
//			}
			return super.regContent(paramMap, request);
		}
		
		
	}
	
	static class TestBoardServiceException extends RuntimeException {
		
	}
	
}
