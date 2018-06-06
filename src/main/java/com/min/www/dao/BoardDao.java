package com.min.www.dao;


import java.util.List;
import java.util.Map;

import com.min.www.dto.BoardDto;
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
	
	public int searchedContentCnt(Map<String, Object> paramMap);

	List<BoardDto> getSearchedContentCntList(Map<String, Object> paramMap);
	
	List<BoardDto> getSearchedContentCntList2(Map<String, Object> paramMap);
	
	List<BoardReplyDto> getSocketReply(Map<String, Object> paramMap);

	void insertAlert(Map<String, Object> paramMap);
	
	
	
	
}
