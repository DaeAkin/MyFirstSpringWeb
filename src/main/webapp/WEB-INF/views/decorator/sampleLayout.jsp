<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>

<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap-theme.min.css">



<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
<body>
  
<tiles:insertAttribute name="menu"/>

<%-- <tiles:insertAttribute name="menuex"/> --%>
<div class="container1">

<tiles:insertAttribute name="sidebar"/>

<div class="content">
<tiles:insertAttribute  name="content"/>
</div>
</div>
<tiles:insertAttribute name="footer"/>

</body>
</html>