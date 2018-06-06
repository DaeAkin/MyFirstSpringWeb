package com.min.www.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.min.www.dao.BoardDao;
import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardReplyDto;
import com.min.www.util.FileUtils;
import com.min.www.util.TimeUtil;
@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Resource(name="boardDao")
	private BoardDao boardDao;
	
	@Resource(name="fileUtils")
	private FileUtils FileUtils;
	
	@Resource(name="timeUtil")
	private TimeUtil timeUtil;
	


	public int getContentCnt(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.getContentCtn(paramMap);
	}

	@Override
	public List<BoardDto> getContentList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		return boardDao.getContentList(paramMap);
	}

	@Override
	public BoardDto getContentView(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.getContentView(paramMap);
	}

	@Override
	public int regReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.regReply(paramMap);
	}

	@Override
	public List<BoardReplyDto> getReplyList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<BoardReplyDto> boardReplyList = boardDao.getReplyList(paramMap);
		//mysql서의계층적 쿼리 만들기
	
		//부모
		List<BoardReplyDto> boardReplyListParent = new ArrayList<BoardReplyDto>();
		//자식
		List<BoardReplyDto> boardReplyListChild = new ArrayList<BoardReplyDto>();
		//통합
		List<BoardReplyDto> newBoardReplyList = new ArrayList<BoardReplyDto>();
		
		//1.부모와 자식 분리
		for(BoardReplyDto boardReplyDto: boardReplyList) {
			if(boardReplyDto.getDepth().equals("0")) {
				boardReplyListParent.add(boardReplyDto);
			} else {
				boardReplyListChild.add(boardReplyDto);
			}
		}
		
		//2. 부모를 돌린다.
		for(BoardReplyDto boardReplyParent : boardReplyListParent) {
			//2-1. 부모는 무조건 넣는다.
			newBoardReplyList.add(boardReplyParent);
			//3.자식을 돌린다.
			for(BoardReplyDto boardReplyChil : boardReplyListChild) {
				//3-1 부모의 자식인 것들만 넣는다.
				if(boardReplyParent.getReply_id().equals(boardReplyChil.getParent_id())) {
					newBoardReplyList.add(boardReplyChil);
				}
			}
		}
		
//		System.out.println(" 날짜 뽑기 테스트용 " +newBoardReplyList.get(0).getRegister_datetime());
		//정리한 list return
		return newBoardReplyList;
	}

	@Override
	public int delReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.delReply(paramMap);
	}

	@Override
	public int getBoardCheck(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.getBoardCheck(paramMap);
	}

	@Override
	public int delBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.delBoard(paramMap);
	}
	@Override
	public int regContent(Map<String, Object> paramMap ,HttpServletRequest request) throws Exception{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String writer = (String)session.getAttribute("nickname");
		paramMap.put("writer", writer);
		boardDao.regContent(paramMap);
		System.out.println(paramMap.get("id"));
		System.out.println("게시물 내용 : " +paramMap.get("smarteditor"));
		System.out.println("게시물 등록 중");
		
//		List<Map<String, Object>> list = FileUtils.parseInsertFileInfo(paramMap, request);
//		for(int i=0 , size=list.size(); i<size; i++) {
//			boardDao.insertFile(list.get(i));
//		}
		
		return 0;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see com.min.www.Service.BoardService#searchedContentCnt(java.util.Map)
	 *  검색에 관련된 메소드.
	 *  SearchOption = title  이면 제목 + 내용 으로 검색
	 *  				   writerOnly 이면 글쓴이로 검색
	 */
	@Override
	public int searchedContentCnt(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		return boardDao.searchedContentCnt(paramMap);

	}



	@Override
	public List<BoardDto> searchedContentCntList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		String searchOption = (String)paramMap.get("searchOption");
		List<BoardDto> boardlist = new ArrayList<>();
		System.out.println("searchOption : " + searchOption);
		
		// 제목 + 내용으로 검색하면 .
		if(searchOption.equals("title")) {
			boardlist =  boardDao.getSearchedContentCntList(paramMap);
			System.out.println("Service 제목 + 내용 검색()");
			
		} 
		 //글쓴이로 검색하면 .
		else {
			boardlist = boardDao.getSearchedContentCntList2(paramMap);
			System.out.println("Service 글쓴이로 검색()");
		}
		
		
		
		return boardlist;
		
	}
	/*
	 * 소켓 테스트 중..
	 * (non-Javadoc)
	 * @see com.min.www.Service.BoardService#getBoardReplySocket(java.util.Map)
	 */
	@Override
	public List<BoardReplyDto> getBoardReplySocket(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		
		return boardDao.getReplyList(paramMap);
	}

	@Override
	public void regAlert(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		boardDao.insertAlert(paramMap);
	}
	
	

}
