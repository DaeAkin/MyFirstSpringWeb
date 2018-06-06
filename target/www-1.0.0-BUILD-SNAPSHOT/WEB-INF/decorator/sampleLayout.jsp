<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>

<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/main.css">


<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.js"></script>
	
	
	
	<!-- 호출 jsp의 스크립트를 가져온다. -->
<decorator:getProperty property="page.local_script"></decorator:getProperty>
</head>
<body>
<div class="container">
<div> </div>
<div class="nav1">
    <ul>
        <li><a href="#">회원가입</a></li>
        <li><a href="#">로그인</a></li>
    </ul>
</div>

<div class="nav2">
    <ul>
        <li class="home"><a href="#"><img src=""></a></li>
        <li>메뉴 1
            <ul class="dropbox">
                <li>하단메뉴 1</li>
                <li>하단메뉴 2</li>
                <li>아아아아아</li>
            </ul>
        </li>
        <li>게시판
            <ul class="dropbox">
                <li>자유게시판
                </li>
                <li>하단메뉴 2</li>
            </ul></li>
        <li>메뉴 3</li>
        <li>메뉴 4</li>
    </ul>
</div>
    <hr id="my-hr">

<span class="sidebar">
    <div class="commuitybest">
    <p style="font: 20px;text-decoration: underline;"> 커뮤니티 베스트</p>
    <table>
        <tr>
        <td> 1 </td> <td>게시물 이름</td>
        </tr> 
        <tr>
        <td> 2 </td> <td>게시물 이름</td>
        </tr> 
        <tr>
        <td> 3 </td> <td>게시물 이름</td>
        </tr> 
        <tr>
        <td> 4 </td> <td>게시물 이름</td>
        </tr> 
        <tr>
        <td> 5 </td> <td>게시물 이름</td>
        </tr> 
    </table>
    
    </div>
    
</span>
    
    
<span class="content">
<decorator:body></decorator:body>
  

    
</span>
    

    
    
    
<%@include file="/WEB-INF/decorator/layouts/footer.jsp" %>

  
 
</div>

</body>
</html>