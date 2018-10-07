package com.min.www.Service.member;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.min.www.dto.member.MemberDto;

public interface MemberService {
	
	List<MemberDto> getMemberlist(Map<String,Object> paramMap);
	
	MemberDto getMember(String id);
	
	int insertMember(Map<String,Object> paramMap);
	
	int deleteMember(Map<String,Object> paramMap);
	
	int memberIdCheck(Map<String, Object> paramMap);
	
	int memberLogin(Map<String, Object>	paramMap);
	
	int memberNickCheck(Map<String, Object> paramMap);
	
	Map<String, Object> memberImageUpload(String user,String uploadPath, String originalName, byte[] fileData ) throws Exception;
	
	void memberEdit(Map<String, Object> paramMap);
}
