<%@page import="org.springframework.ui.Model"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--  
<% /*
int startPage;
int endPage;
if(request.getParameter("startPage") == null) {
	startPage = 1;
} else { 
	
	 startPage = Integer.parseInt(request.getParameter("startPage"));
}
endPage = Integer.parseInt(request.getParameter("endPage"));


*/%> -->
<head>
    <style>
        td {
            text-align: center;
        }
    </style>
</head>

<body>

    <table border="1">
        <tr>
            <td width=50>번호</td>
            <td width=800>제목</td>
            <td width=60>글쓴이</td>
            <td width=40>시간</td>
            <td width=40>조회</td>
        </tr>
        
        		<c:forEach var="freeboard" items="${freeboard }">
             <tr>
        		<td>${ freeboard.id}</td>
        		<td>${freeboard.title}</td>
        		<td>${freeboard.writer}</td>
        		<td>${freeboard.writertime}</td>
        		<td>${freeboard.hit}</td>
        		</tr>
        		</c:forEach>

        

    </table>
    <a href="/write">글쓰기</a>
    
    
   
   -->
   </body>
    