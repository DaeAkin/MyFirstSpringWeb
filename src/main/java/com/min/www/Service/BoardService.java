package com.min.www.Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.min.www.dto.BoardAndAlertJoinDto;
import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardOptionsDto;
import com.min.www.dto.BoardReplyDto;

public interface BoardService {
	
	int regContent(Map<String, Object> paramMap, HttpServletRequest request) throws Exception;
	
	int getContentCnt(Map<String, Object> paramMap);

	List<BoardDto> getContentList(Map<String, Object> paramMap);
	
	BoardDto getContentView(Map<String, Object> paramMap);
	
	void regReply(Map<String, Object> paramMap);
	
	List<BoardReplyDto> getReplyList(Map<String, Object> paramMap);
	
	int delReply(Map<String, Object> paramMap);
	
	int getBoardCheck(Map<String, Object> paramMap);
	
	int delBoard(Map<String, Object> paramMap);
 
	int searchedContentCnt(Map<String, Object> paramMap);
	
	List<BoardDto> searchedContentCntList(Map<String, Object> paramMap);
	
	List<BoardReplyDto> getBoardReplySocket(Map<String, Object> paramMap);

	void regAlert(Map<String, Object> paramMap);
	
	void deleteAllBoard();
	
	int getBoardCnt();
	
	void boardAgree();
	
	void insertBoardAgree(Map<String, Object> paramMap);
	
	// 조인한 좋아요 싫어요 테이블 전부삭제
	void deleteAllBoardAgree();
	
	// 게시글마다 Agree는 나중에 구현하기. 지금은 DB에 있는 데이터 수
	void getBoardAgreeAndDisagreeCnt();
	
	BoardOptionsDto getBoardAgreeAndDisagreeOne(int boardid);
	
	// 게시글에 좋아요 , 싫어요를 눌렀는지 확인
	Boolean isCanAgreeWithBoard(Map<String, Object> paramMap);
//	// 좋아요 구현 
//	void likeBoardAgree(Map<String,Object> paramMap);
//	
//	//싫어요 구현
//	
//	void dislikeBoardDisagree(Map<String, Object> paramMap);

	// 새로운 댓글 읽었으면 DB에서 읽음 처리
	void checkingTheBoardReply(int alertId); 

	// 알림할 댓글들 가져오기 
	List<BoardAndAlertJoinDto> getAlerts(String writer);
	
	//댓글 한개 가져오기
	BoardReplyDto getOneReply(String reply_id);

}
