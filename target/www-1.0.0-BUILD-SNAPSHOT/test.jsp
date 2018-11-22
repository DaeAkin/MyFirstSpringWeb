<%@page import="com.min.www.Service.BoardServiceImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.min.www.dto.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.min.www.Service.BoardService"%>
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
	BoardService boardService = new BoardServiceImpl();
	Map<String,Object> paramMap = new HashMap<>();
	
	paramMap.put("start", 0);
	paramMap.put("end", 10);
	
	List<BoardDto> list = new ArrayList<>();
	
	boardService.getContentList(paramMap);
	
	out.print(list.size());

%>
</body>
</html>