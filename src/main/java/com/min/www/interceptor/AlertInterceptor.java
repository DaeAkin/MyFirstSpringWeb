package com.min.www.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.min.www.Service.BoardService;
import com.min.www.dto.BoardAndAlertJoinDto;
import com.min.www.dto.member.MemberDto;

public class AlertInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired BoardService boardService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("--- preHandle -----");
		//세션 생성 
		HttpSession session = request.getSession();
		
		// 알람 List 
		List<BoardAndAlertJoinDto> alertList = null;
		//로그인 session 가져오기 
		MemberDto memberInfo = (MemberDto)session.getAttribute("memberInfo");
		
		//  로그인 상태라면 
		if(memberInfo != null) {
			alertList = getUserAlert(memberInfo.getNickname());
			System.out.println("알려줄 알람 :" + alertList.size());
			request.setAttribute("alertList", alertList);
			
		// 로그인이 풀리면
		} else {
			System.out.println("로그인 안한상태거나 세션 만료 입니다.");
		}
		
		
		
		return true;
	}
	
	private List<BoardAndAlertJoinDto> getUserAlert(String writer) {
		
		return boardService.getAlerts(writer);
	}



}
