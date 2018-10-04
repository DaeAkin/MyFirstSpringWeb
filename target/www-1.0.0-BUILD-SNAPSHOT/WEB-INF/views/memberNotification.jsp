<%@page import="com.min.www.dto.member.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%	MemberDto memberInfo = (MemberDto)session.getAttribute("memberInfo");
	String requestId = (String)request.getAttribute("requestId");
	String id = (String)session.getAttribute("loginuser");
	String nickname = (String)session.getAttribute("nickname");
	
%>
<h3><%=id%> 님의 알림들 </h3>
</body>
</html>