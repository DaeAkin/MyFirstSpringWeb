package com.min.www.dao;


import java.util.List;
import java.util.Map;

import com.min.www.dto.BoardAndAlertJoinDto;
import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardOptionsDto;
import com.min.www.dto.BoardReplyDto;

public interface BoardDao {
	
	/* Map파라미터가 하는일
	 * 
	 *  */

	int regContent(Map<String, Object> paramMap);
	
	int insertFile(Map<String, Object> paramMap);
	
	int getContentCtn(Map<String,Object> paramMap);
	
	List<BoardDto> getContentList(Map<String, Object> paramMap);
	
	BoardDto getContentView(Map<String, Object> paramMap);
	
	int regReply(Map<String, Object> parMap);
	
	List<BoardReplyDto> getReplyList(Map<String, Object> paramMap);
	
	int delReply(Map<String, Object> paramMap);
	
	int getBoardCheck(Map<String, Object> paramMap);
	
	int delBoard(Map<String, Object> paramMap);
	
	 int searchedContentCnt(Map<String, Object> paramMap);

	List<BoardDto> getSearchedContentCntList(Map<String, Object> paramMap);
	
	List<BoardDto> getSearchedContentCntList2(Map<String, Object> paramMap);
	
	List<BoardReplyDto> getSocketReply(Map<String, Object> paramMap);

	
	
	
	void deleteAllContent();
	
	void deleteAllReply();
	
	void testSys();
	
	BoardDto getBoardOne(int id);
	
	BoardReplyDto getBoardReplyOne(String reply_id);
	
	void deleteAllBoard();

	int getBoardCnt();
	
	void deleteAllBoardAgree();
	
	void boardAgree(Map<String, Object> paramMap);
	
	void boardDisgree(Map<String, Object> paramMap);
	
	void insertBoardAgree(Map<String, Object> paramMap);
	
	BoardOptionsDto getBoardAgreeAndDisagreeOne(int boardid);
	
	// 게시글에 좋아요 , 싫어요를 눌렀는지 확인
	BoardOptionsDto isCanAgreeWithBoard(Map<String, Object> paramMap);
	
//	// 좋아요 구현 
//	void likeBoardAgree(Map<String,Object> paramMap);
//	
//	//싫어요 구현
//	
//	void dislikeBoardDisagree(Map<String, Object> paramMap);
	
	// DB에 댓글이 남아있는지 확인 
	
	int getBoardReplyCnt();
	
	// alert 카운트
	int boardAlertCnt();
	
	void deleteAllBoardAlert();
	
	// 새로운 댓글 읽었으면 DB에서 읽음 처리
	void checkingTheBoardReply(int alertid); 
	
	// 댓글 알람 가져오기 
	List<BoardAndAlertJoinDto> getAlerts(String writer);
	
	//댓글 등록과 함께 reply DB에 집어 넣기 
	void insertReplyAlert(Map<String,Object> paramMap);


	
	
	// 사용자가 작성한 게시판번호들을 가져와서 댓글들만 가져와야함  조인 . 
	
	


}
