<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>

<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- menu -->

<jsp:include page="<%=request.getContextPath() %>/WEB-INF/decorator/layouts/menu.jsp"></jsp:include>

<!-- content -->
<decorator:body></decorator:body>


<jsp:include page="<%=request.getContextPath() %>/WEB-INF/decorator/layouts/footer.jsp"></jsp:include>

</body>
</html>