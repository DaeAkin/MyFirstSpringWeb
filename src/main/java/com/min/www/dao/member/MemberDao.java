package com.min.www.dao.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.min.www.dto.member.MemberDto;

public interface MemberDao {
	
	
	List<MemberDto> getMemberlist(Map<String,Object> paramMap);
	
	MemberDto getMember(Map<String,Object> paramMap,HttpServletRequest request);
	
	int insertMember(Map<String,Object> paramMap);
	
	int deleteMember(Map<String,Object> paramMap);

	int memberIdCheck(Map<String, Object> paramMap);
	
	int memberNickCheck(Map<String, Object> paramMap);
	
	void insertMemberImage(Map<String, Object> paramMap);

}
