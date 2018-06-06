<%@page import="com.min.www.dto.member.MemberDto"%>
<%@page import="org.springframework.ui.Model"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- <security:authorize access="isAuthenticated()">
	<security:authentication property="pri"/>
</security:authorize> --%>
<!--  웹소켓 메세지알림 -->
<script type="text/javascript">


$(document).ready(function() {
	/*
	WebSocket 프로토콜을 사용하여 통신하기 위해서는 webSocket 객를 생성하는데,
	
	WebSocket 생성자는 하나의 필수 파라미터와 하나의 선택 파라미터를 받는다.
	
	
	WebSocket webSocket(
			
			in DOMString url,
			in optional DOMString protocls
			)
	
	 url = WebSocket 서버가 응답할 URL
	 
	 protocols (선택) 
		하나의 프로토콜 문자열, 또는 프로토콜 문자열의 배열,
		이 문자열들은 서브 프로토콜을 지정하는데 사용되어, 하나의 서버가 여러 개의 WebSocket 서브 프로토콜을 구현할 수 있도록 해줌.
		예를 들어, 하나의 서버가 처리하는 상호작용이 지정된 protocols에 따라 달라지도록 할 수 있다.
		만약 프로토콜 문자열을 지정하지 않으면 빈 문자열을 넣은 것으로 간주됨.
	 
	 
	*/
	
	var wsUri = "ws://localhost:53493/www/count";

	function send_meesage() {
		
		websocket.onopen = function(evt) {
			onOpen(evt);	
		};
		
		websocket.onmessage = function(evt) {
			onMessage(evt);	
		};
		
		websocket.onerror = function(evt) {
			onError(evt);	
		};
	}

	function onOpen(evt) {
		
		/* 
		send() 
		
		보내고 싶은 메세지를 지정하면 된다. 
		
		연결을 맺는 행위는 비동기 작업이고, 실패하기 쉬운 작업이기 때문에,
		WebSocket 오브젝트를 생성하마자 send()로 데이터를 전송을 시도하는 것은 
		성공하지 않을 가능성이 있다.
		연결이 수립된 이후에만 데이터를 전송하도록 하기위해 onOpen 핸들러에 정의하고 안에서 작업한다. 
		*/
		websocket.send("대악인");
		
	}

	function onMessage(evt) {
		/*
			서버로부터 데이터 수신하기
			메세지가 수신되면 meesage 이벤트가 onmessage 함수로 전달되게 된다.
			
		
		*/
		$('#count').text(evt.data);	
	}

	function onError(evt) {
		
	}
	
	websocket = new WebSocket(wsUri);
	
	
	
	setInterval(function() {
		websocket.send("대악인");
		/* send_meesage(); */
	}, 6000); 
	
});



</script>

</head>
<body>
<%	MemberDto memberInfo = (MemberDto)session.getAttribute("memberInfo");
	String requestId = (String)request.getAttribute("requestId");
	String id = (String)session.getAttribute("loginuser");
	String nickname = (String)session.getAttribute("nickname");
	
%>


  	<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">하이하이!${IMAGEURL}${id }${nickname }asd${check }${hi} </a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="<%=request.getContextPath()%>/board/list">Home</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Page 1-1</a></li>
            <li><a href="#">Page 1-2</a></li>
            <li><a href="#">Page 1-3</a></li>
          </ul>
        </li>
        <li><a href="<%=request.getContextPath()%>/board/list">게시판</a></li>
        <li><a href="#">Page 3</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
       <%
    	if(id == null) {
    %>
        <li><a href="<%=request.getContextPath()%>/member"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="<%=request.getContextPath()%>/member/loginform"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        <li><span class="glyphicon glyphicon-comment"></span></li>
        <li><span class="glyphicon glyphicon-log-in"></span>sd</li>
        <li><a href="<%=request.getContextPath()%>/member/notification"><span class="glyphicon glyphicon-comment"></span>알림 :<span id="count"></span></a></li>
        <li><span> sadasd</span> </li>
      	<%
    	} else {
        %>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <span class="glyphicon glyphicon-user"></span> <%=(String)session.getAttribute("nickname") %>님<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<%=request.getContextPath()%>/member/edit">회원정보변경</a></li>
            <li><a href="<%=request.getContextPath()%>/member/logout">Logout </a></li>
            <li><a href="#">Page 1-3</a></li>
          </ul>
        </li>
        
       
        <%} %>
      </ul>
    </div>
  </div>
</nav>
  

    
    



<!--  
<div class="nav2">
    <ul>
        <li class="home"><a href="#"><img src="%EC%A7%91.png"></a></li>
        <li>메뉴 1
            <ul class="dropbox">
                <li>하단메뉴 1</li>
                <li>하단메뉴 2</li>
                <li>아아아아아</li>
            </ul>
        </li>
        <li>게시판11ㄴ
            <ul class="dropbox">
                <li>자유게시판</li>
                <li>하단메뉴 2</li>
            </ul></li>
        <li>메뉴 3</li>
        <li>메뉴 4</li>
    </ul>
</div>
-->


</body>
</html>