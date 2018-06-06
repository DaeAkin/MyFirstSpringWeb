<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

    <!-- form enctype="multipart/form-data" 을 꼭 적어줘야 함 -->
    <form class="form-horizontal" method="post" action="/uploads" enctype="multipart/form-data">
        <!-- input type="file" 이라고 꼭 저어줘야 함 -->
        <input type="file" " id="uploadFile" name="uploadFile" style="border:0px solid black;"/>
        
        <button type="submit" class="btn btn-default">등록</button>
        <button type="reset" class="btn btn-default">취소</button>
    </form>


</body>
</html>