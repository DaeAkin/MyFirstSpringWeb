<%@page import="java.util.ArrayList"%>
<%@page import="com.min.www.dao.BoardDao"%>
<%@page import="com.min.www.Service.BoardServiceImpl"%>
<%@page import="com.min.www.Service.BoardService"%>
<%@page import="com.min.www.dao.BoardDaoImpl"%>
<%@page import="com.min.www.dto.member.MemberDto"%>
<%@page import="java.util.Map"%>
<%@page import="com.min.www.dao.BoardDaoImpl"%>
<%@page import="com.min.www.dto.BoardAndAlertJoinDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <%
        BoardDao boardDao = new BoardDaoImpl();
   		BoardService boardService = new BoardServiceImpl();
        int totalAlert; // 전체 알람 갯수 변수
        
       	if(true) {
    		
    		MemberDto memberDto = 
    				(MemberDto)session.getAttribute("memberInfo");
    		System.out.println("세션 로그인 : " + memberDto.getNickname());
    		 
    		// 오류가 나는 부분
    		try {
    				List<BoardAndAlertJoinDto> aa =
    				boardDao.getAlerts("바보");
    				
    				System.out.println("return test : " + boardDao.returnTest());
    			
    			  totalAlert = aa.size(); 
    		} catch(NullPointerException e) {
    			  totalAlert = 0;
    			  System.out.println("nullPointerException 입니다.");
    		}
    		
    		 
    		 System.out.println("알려줄 알람 수 : " + totalAlert);  
    	} 
        
     	  	
        %>

</body>
</html>