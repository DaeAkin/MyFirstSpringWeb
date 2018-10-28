package com.min.www.Service.member;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.min.www.Exception.MemberDuplicationException;
import com.min.www.Exception.NoSuchMemberException;
import com.min.www.dao.member.MemberDao;
import com.min.www.dto.member.MemberDto;
import com.min.www.util.FileUtils;
@Service("MemberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDao memberDao;
	
	@Resource
	FileUtils fileutils;
	
	final static String DEFAULT_MEMBER_IMAGE = "64x64.svg";
	

	

	@Override
	public List<MemberDto> getMemberlist(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.getMemberlist(paramMap);
	}

	@Override
	public MemberDto getMember(String id) {
		// TODO Auto-generated method stub
		// 아이디 비밀번호 꺼내와서 if넣어주기.
		
		
		return memberDao.getMember(id);
	}

	@Override
	public int insertMember(Map<String, Object> paramMap) throws MemberDuplicationException {
		//ID 중복체크.
		// 컨트롤러에게 Exception처리를 위임해준다. 
		memberInvalidCheck(paramMap);
		
	
		
		
		// 넘어온 이미지 url이 빈 문자열일 
		if((String)paramMap.get("ORIGINALIMAGEURL") == "" ) {
			paramMap.put("ORIGINALIMAGEURL", DEFAULT_MEMBER_IMAGE);
			paramMap.put("IMAGEURL", DEFAULT_MEMBER_IMAGE);	
		}
		
		
		return memberDao.insertMember(paramMap);
	}

	@Override
	public int deleteMember(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.deleteMember(paramMap);
	}

	@Override
	public int memberInvalidCheck(Map<String, Object> paramMap) throws MemberDuplicationException {
		/* 두가지 방법.
		 * 1, where 절에 id랑 nickname을 두개 줘서 OR로 일치하는거 다 가져온다음 .
		 * for each 돌려서 id가 중복인지 nickname인지 중복 체크 해주는 것 .
		 * 장점 : 메소드가 한개로 끝남.
		 * 단점 : DB데이터가 많으면 그만큼 for each 돌리는데 시간이 많이 걸린다.
		 * 
		 * 2. count(id)로 id체크 한개 count(nickname)으로 nickname체크 한개.
		 * 해서 1 이상이면 중복이니까 exception 던져주는 방식 .
		 * 장점 : DB단에서 끝나므로 부하가 상대적으로 덜하다.
		 * 단점 : 메소드가 check하는 수만큼 더 생기게 됨. 
		 */
		
		
		
		int IDCheckInt = memberInvalidCheck(paramMap);
		
		if(IDCheckInt >= 1) {
			throw new MemberDuplicationException();
		}
		
		return memberDao.memberInvalidCheck(paramMap);
	}
	

	@Override
	public int memberNickCheck(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.memberNickCheck(paramMap);
	}

	@Override
	public int memberLogin(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		int result = 0;
		
		
		MemberDto memberDto = memberDao.getMember((String)paramMap.get("id"));
		
		// 일치하는 회원 없으면 throw
		if(memberDto == null) {
			throw new NoSuchMemberException("일치하는 회원이 없습니다.");
		}
		
		Map<String, Object> memberMap = new HashMap<>();
		
		
		
	
		if(memberDto.getId().equals(paramMap.get("id")) &&
				memberDto.getPassword().equals(paramMap.get("password")) ) {
			//request 영역에 넣어주기. 
			//id랑 password는 이미 request 영역에 있다.
			System.out.println("아이디 비밀번호 인증 완료");
			System.out.println("인증된 닉네임 : + " 	+ memberDto.getNickname());
			memberMap.put("id", memberDto.getId());
			memberMap.put("nickname", memberDto.getNickname());
			memberMap.put("email", memberDto.getEmail());
			memberMap.put("IMAGEURL", memberDto.getImageurl());
			
			
			
			
			return result = 1;
		} else {
			
		return result; 
		
		}
		
	}


	@Override
	public Map<String, Object> memberImageUpload(String user,String uploadPath, String originalName, byte[] fileData) throws Exception {
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
//		memberDao.insertMemberImage(paramMap);
		
		
		return paramMap;
		
		
	}

	@Override
	public void memberEdit(Map<String, Object> paramMap) {
		
		memberDao.memberEdit(paramMap);
		
	}


	
	
	

}
