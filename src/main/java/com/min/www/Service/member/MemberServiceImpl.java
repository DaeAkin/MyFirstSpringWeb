package com.min.www.Service.member;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.min.www.dao.member.MemberDao;
import com.min.www.dto.member.MemberDto;
import com.min.www.util.FileUtils;
@Service("MemberService")
public class MemberServiceImpl implements MemberService{
	
	@Resource(name="MemberDao")
	MemberDao memberDao;
	
	@Resource
	FileUtils fileutils;
	

	

	@Override
	public List<MemberDto> getMemberlist(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.getMemberlist(paramMap);
	}

	@Override
	public MemberDto getMember(Map<String, Object> paramMap,HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 아이디 비밀번호 꺼내와서 if넣어주기.
		
		
		return memberDao.getMember(paramMap,request);
	}

	@Override
	public int insertMember(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.insertMember(paramMap);
	}

	@Override
	public int deleteMember(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.deleteMember(paramMap);
	}

	@Override
	public int memberIdCheck(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.memberIdCheck(paramMap);
	}
	

	@Override
	public int memberNickCheck(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.memberNickCheck(paramMap);
	}

	@Override
	public int memberLogin(Map<String, Object> paramMap,HttpServletRequest request,Model model) {
		// TODO Auto-generated method stub
		int result = 0;
		HttpSession session = request.getSession();
		
		MemberDto memberDto = memberDao.getMember(paramMap,request);
		
		
		Map<String, Object> memberMap = new HashMap<>();
		

		
	
		if(memberDto.getId().equals(paramMap.get("id")) &&
				memberDto.getPassword().equals(paramMap.get("password")) ) {
			//request 영역에 넣어주기. 
			//id랑 password는 이미 request 영역에 있다.
			session.setAttribute("nickname", memberDto.getNickname());
			System.out.println("아이디 비밀번호 인증 완료");
			System.out.println("인증된 닉네임 : + " 	+ memberDto.getNickname());
			memberMap.put("id", memberDto.getId());
			memberMap.put("nickname", memberDto.getNickname());
			memberMap.put("email", memberDto.getEmail());
			memberMap.put("IMAGEURL", memberDto.getImageurl());
			model.addAttribute("check","check");
			model.addAllAttributes(memberMap);
			System.out.println(model.containsAttribute("IMAGEURL"));
			
			
			
			return result = 1;
		} else {
			
		return result; 
		
		}
		
	}


	@Override
	public void memberImageUpload(String user,String uploadPath, String originalName, byte[] fileData) throws Exception {
		// TODO Auto-generated method stub
		//이미자가 경로된 위치 값을 리턴받는 변수
		Map<String, String> returnedImageurl = new HashMap<>();
		String savedPath;
		String originalPath;
		
		returnedImageurl = FileUtils.reSizeImage(uploadPath, originalName, fileData);
		
		savedPath = returnedImageurl.get("savedPath");
		originalPath = returnedImageurl.get("originalPath");
		//Mapper에 전달해줄 Map객체
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", user);
		paramMap.put("IMAGEURL", savedPath);
		paramMap.put("ORIGINALIMAGEURL", originalPath);
		
		System.out.println("Fileutils에서 리턴 한 String 값 :" + savedPath);
		//DB에 이미지가 저장된 url를 저장.
		memberDao.insertMemberImage(paramMap);
		
		
		
		
	}


	
	
	

}
