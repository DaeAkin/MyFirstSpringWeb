<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.js"></script>
	
<!--  웹소켓 테스트  -->
<script type="text/javascript">
var wsUri = "ws://localhost:53493/www/count";

function send_meesage() {
	websocket = new WebSocket(wsUri);
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
	websocket.send("대악인");
	
}

function onMessage(evt) {
	$('#count').append(evt.data);	
}

function onError(evt) {
	
}

$(document).ready(function() {
	send_meesage()
});



</script>
</head>
<body>

<p id="count"> 테스트 </p>

</body>
</html>