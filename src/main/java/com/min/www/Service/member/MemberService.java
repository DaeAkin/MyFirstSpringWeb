package com.min.www.Service.member;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.min.www.Exception.IsNotValidId;
import com.min.www.Exception.IsNotValidNickname;
import com.min.www.Exception.MemberDuplicationException;
import com.min.www.dto.member.MemberDto;

public interface MemberService {
	
	List<MemberDto> getMemberlist(Map<String,Object> paramMap);
	
	MemberDto getMember(String id);
	
	Map<String,Object> insertMember(Map<String,Object> paramMap) throws MemberDuplicationException;
	
	int deleteMember(Map<String,Object> paramMap);
	
	Map<String, Object> memberInvalidCheck(Map<String, Object> paramMap);
	
	int memberLogin(Map<String, Object>	paramMap);
	
	int memberNickCheck(Map<String, Object> paramMap);
	
	Boolean isInvalidId(Map<String, Object> paramMap);
	
	Boolean isInvalidNickname(Map<String, Object> paramMap);
	
	
	
	
	Map<String, Object> memberImageUpload(HttpServletRequest request ,String user,String uploadPath, String originalName, byte[] fileData ) throws Exception;
	
	void memberEdit(Map<String, Object> paramMap);
}
