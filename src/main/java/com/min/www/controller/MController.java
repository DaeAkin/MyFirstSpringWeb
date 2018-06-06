package com.min.www.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.min.www.Service.member.MemberService;
import com.min.www.dto.member.MemberDto;
import com.min.www.util.FileUtils;

@Controller
public class MController {
	
	@Autowired
	MemberService memberService;
	
	@Resource
	String imageUploadPath;
	
	@Resource
	FileUtils fileUtils;
	
	@RequestMapping(value="/member")
	public String member() {
		
		return "memberCheck";
	}
	
	
	@RequestMapping(value="/member/check" , method=RequestMethod.POST)
	public String memberCheck(@RequestParam Map<String, Object> paramMap) {
		System.out.println("ID : " +paramMap.get("id"));
		System.out.println("PASSWORD : " + paramMap.get("password").toString());
		System.out.println("email : " +paramMap.get("email").toString());
	
		
		System.out.println("insert 성공 메세지 : " + 
		memberService.insertMember(paramMap));
		
		System.out.println("성공");
		
		
		
		return "boardList";
	}
	
	@RequestMapping(value="/member/idCheck")
	@ResponseBody
	public Object memberIdCheck(@RequestParam Map<String, Object> paramMap) {
		
		Map<String,Object> reVal = new HashMap<String, Object>();
		System.out.println("중복 확인할 아이디 : " + paramMap.get("id"));
		
		
		int result = memberService.memberIdCheck(paramMap);
		
		System.out.println("ID Check 쿼리 확인 :" + result);
		
		if(result == 0) {
			reVal.put("code", "OK");
		
		} else {
			reVal.put("code", "FAIL");
			
		}
		System.out.println("reVal의 변수는 : " +reVal.get("code"));

		return reVal;
	}
	
	
	@RequestMapping(value="/member/nickCheck")
	@ResponseBody
	public Object memberNickCheck(@RequestParam Map<String, Object> paramMap) {
		
		System.out.println("중복 확인할 닉네임 : " + paramMap.get("nickname"));
		
		Map<String, Object> reVal = new HashMap<>();

		int result = memberService.memberNickCheck(paramMap);
		
		if(result == 0 ) {
			reVal.put("code", "OK");
		} else {
			reVal.put("code", "FAIL");
		}
		
		return reVal;
	}
	
	
	
	@RequestMapping(value="/member/loginform")
	public String memberLoginform() {
		
		return "loginform";
	}
	@RequestMapping(value="/member/login")
	@ResponseBody
	public Object memberLogin(@RequestParam Map<String, Object> paramMap,  HttpSession session,HttpServletRequest request,
			Model model) {
	
		Map<String, Object> retVal = new HashMap<String, Object>();
		
		//1. sql문에 대입하여 아이디 확인 작업 서비스에서 처리.
		int reVal = memberService.memberLogin(paramMap,request,model);
		
		//로그인 했을 때 닉네임으로 뜨게하고싶으면
		//request에 넣어줘야함 가져와서 
		
		
		//2. session 생성해주기. 
		// 0이 아니면 로그인처리 
		if(reVal != 0) {
			session.setAttribute("loginuser", paramMap.get("id"));
			
			MemberDto memberInfo = memberService.getMember(paramMap, request);
			// 로그인 객체 세션 생성
			session.setAttribute("memberInfo", memberInfo);
			
			
			retVal.put("code", "OK");
			
			
			// 세션 이름 = loginuser에 id를 넣어줌.
		
			//redirect로 메인메이피로 넘어가기는 jsp에서 처리.
			System.out.println(paramMap.get("id") + "님이 로그인.");
		
			
		}
		
		model.addAttribute("nickname", request.getAttribute("nickname"));
		model.addAttribute("hi","hi");	
		System.out.println("닉네임은? :" + request.getAttribute("nickname"));
		
	 
		
		return retVal;
	}
	@RequestMapping(value="/member/logout")
	public String memberLogout(HttpSession session) {
		
		session.invalidate();
		return "redirect:/board/list"; //추후에 홈으로 수정.
	}
	
	@RequestMapping(value="/member/edit") 
	public String memberEdit(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginuser");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		// request 불 필요.
		model.addAttribute("member", memberService.getMember(paramMap, request));
		
		return "MemberEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/member/memberEdit")
	public void memberEdit(@RequestMapping Map<String,Object> paramMap) {
		
		
		
	}
	
	@RequestMapping(value="/member/image/upload")
	public void memberImageUpload(MultipartFile file,HttpSession session)  throws Exception{
		
		byte[] fileData = file.getBytes();
		String originalName = file.getOriginalFilename();
		
		//session에 저장된 사용자 id값 가져오기.
		String user = (String)session.getAttribute("loginuser");
		
		//파일을 저장하는 Service
		memberService.memberImageUpload(user,imageUploadPath, originalName, fileData);
		
		
	}

}
