<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>​

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
<%-- /*$(document).ready(function() {
$("#alerts").on("click",function(event) {
    event.preventDefault();
    
    
	var formData = {
			alert_id			:	$("#alert_id").val()
			
	};
    
    
    $.ajax({
        
        type            :       "post",
        url             :       "<%=request.getContextPath()%>/alert/checked",
        data            :       formData,
        dataType        :       "json",
        async			:		false, 
        success         :       function(data) {
            
        alert("썪쎾쓰!");
        }
    })
    
    
	})
}); --%>
</script>
</head>
<body>

<table>
<c:forEach var="alerts" items="${alerts}">

<tr> 

	<input type="hidden" id="alert_id" value="${alerts.alert_id }"/>
	<td id="alerts"	class="alerts"><a href="<%=request.getContextPath()%>/board/view?id=${alerts.board_id}"> ${alerts.title } 의 댓글이 달렸습니다. </a></td>

</tr>

</c:forEach>
</table>
</body>
</html>