package com.min.www.util;

import java.util.HashMap;
import java.util.Map;

import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardReplyDto;
import com.min.www.dto.member.MemberDto;

public class ParamFactory {

	public Map<String, Object> boardDtoFactory(BoardDto boardDto) {
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("id", boardDto.getId());
		paramMap.put("title", boardDto.getTitle());
		paramMap.put("writer", boardDto.getWriter());
		paramMap.put("writetime", boardDto.getWritetime());
		paramMap.put("hit", boardDto.getHit());
		paramMap.put("content", boardDto.getContent());
		
		return paramMap;
	}
	
	public Map<String, Object> boardDtoReplyFactory(BoardReplyDto boardReplyDto) {
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("reply_id", boardReplyDto.getReply_id());
		paramMap.put("board_id", boardReplyDto.getBoard_id());
		paramMap.put("parent_id", boardReplyDto.getParent_id());
		paramMap.put("depth", boardReplyDto.getDepth());
		paramMap.put("reply_content", boardReplyDto.getReply_content());
		paramMap.put("reply_writer", boardReplyDto.getReply_writer());
		
		return paramMap;
	}
	
	public Map<String, Object> memberDtoFactory(MemberDto memberDto) {
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("id", memberDto.getId());
		paramMap.put("nickname", memberDto.getNickname());
		paramMap.put("password", memberDto.getPassword());
		paramMap.put("email", memberDto.getEmail());
		paramMap.put("IMAGEURL", memberDto.getImageurl());
		paramMap.put("ORIGINALIMAGEURL", memberDto.getORIGINALIMAGEURL());
		
		return paramMap;
	}
	
	
	
}
