<%@page import="java.util.Map"%>
<%@page import="com.min.www.dao.BoardDao"%>
<%@page import="com.min.www.dto.BoardAndAlertJoinDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!------ Include the above in your HEAD tag ---------->

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">
        
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/custom.css">
        <style>
   
            /* 모바일 시 패딩 없애기 */
/*         .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
            padding: 0;
          } */
        </style>
        
        
        <script>
        
        
            $(document).ready(function(){
   $('[data-toggle="offcanvas"]').click(function(){
       $("#navigation").toggleClass("hidden-xs");
   });
});
        </script>
        </head>
        
     <body class="home">
    <div class="container-fluid display-table">
        <div class="row display-table-row">
            <div class="col-md-2 col-sm-1 hidden-xs display-table-cell v-align box" id="navigation">
                <div class="logo">
                    <a hef="home.html"><img src="donghyeon.png" alt="merkery_logo" class="hidden-xs hidden-sm">
                        <img src="http://jskrishna.com/work/merkury/images/circle-logo.png" alt="merkery_logo" class="visible-xs visible-sm circle-logo">
                    </a>
                </div>
                <div class="navi">
                    <ul>
                        <li class="active"><a href="#"><i class="fa fa-home" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Home</span></a></li>
                        <li><a href="#"><i class="fa fa-tasks" aria-hidden="true"></i><span class="hidden-xs hidden-sm">QNA</span></a></li>
                        <li><a href="#"><i class="fa fa-bar-chart" aria-hidden="true"></i><span class="hidden-xs hidden-sm">News</span></a></li>
                        <li><a href="#"><i class="fa fa-user" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Calender</span></a></li>
                        <li><a href="#"><i class="fa fa-calendar" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Users</span></a></li>
                        <li><a href="#"><i class="fa fa-cog" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Setting</span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-10 col-sm-11 display-table-cell v-align">
                <!--<button type="button" class="slide-toggle">Slide Toggle</button> -->
                <div class="row">
                    <header>
                        <div class="col-md-7">
                            <nav class="navbar-default pull-left">
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle collapsed" data-toggle="offcanvas" data-target="#side-menu" aria-expanded="false">
                                        <span class="sr-only">Toggle navigation</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>
                                </div>
                            </nav>
                            <div class="search hidden-xs hidden-sm">
                                <input type="text" placeholder="Search" id="search">
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="header-rightside">
                                <ul class="list-inline header-top pull-right">
                                <!--  로그인 상태라면 , -->
                                <%if(session.getAttribute("loginuser") != null) {
                                	
					%>
                                 
<!--                                    <li class="hidden-xs"></li>-->
                                    <li><a href="#"><i class="fa fa-envelope" aria-hidden="true"></i></a></li>
                                    
                                    
                                                           <li class="dropdown">
                                        <a href="#" class="icon-info dropdown-toggle" data-toggle="dropdown"><span class="fa fa-bell" aria-hidden="true"></span>
                                            <span class="label label-primary">${totalAlert }</span>
                                            <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li>
                                                 <div class="navbar-content">
                                                 
                                                   
                                         <a href="#" class="icon-info">
                                            
                                           
                                        </a>

                                        		<c:forEach var="boardAndAlertJoinDtos" items="${boardAndAlertJoinDtos }" varStatus="status">
                                                    
                                                      <div class="input-group">
                                                      
  														<p> ${boardAndAlertJoinDtos.reply_content } 댓글이 달렸습니다.</p>
													</div>
                                                     
                                                        <div class="divider">
                                                    </div>
                                               </c:forEach>

                                                    
                                                    
                                                </div>
                                     
                                            </li>
                                        </ul>
                                    </li>
                      <%--               <li>
                                        <a href="#" class="icon-info">
                                            <i class="fa fa-bell" aria-hidden="true"></i>
                                            <span class="label label-primary">${totalAlert }</span>
                                        </a>
                                    </li> --%>
                                    
                                       <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
                                            <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li>
                                                 <div class="navbar-content">
                                                 
                                                   
                                                   <div class="input-group">
  														<a href="<%=request.getContextPath()%>/member/editForm"  class="btn btn-default">내 정보</a> 
													</div>

                                                    <div class="divider">
                                                    </div>
                                                    
                                                      <div class="input-group">
  														<a href="<%=request.getContextPath()%>/member/logout"  class="btn btn-default">로그아웃</a> 
													</div>
                                                    
                                                    
                                                </div>
                                     
                                            </li>
                                        </ul>
                                    </li>
                                  
                                <%} else {%>
                                    <!-- Single button -->
                                        
                                        
                                       
                                        
                                      
                                
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
                                            <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li>
                                                 <div class="navbar-content">
                                                 
                                                   <form action="<%=request.getContextPath()%>/member/login" method="POST">
                                                   <div class="input-group">
  <span class="input-group-addon" id="sizing-addon2">ID</span>
  <input type="text" id="id" name="id" class="form-control" placeholder="ID" aria-describedby="sizing-addon2">
</div>
                                           <div class="input-group">
  <span class="input-group-addon" id="sizing-addon2">PW</span>
  <input type="password" id="password" name="password" class="form-control" placeholder="PW" aria-describedby="sizing-addon2">
</div>
                                                    <div class="divider">
                                                    </div>
                                                    <input type="submit" class="btn btn-danger" value="로그인">
                                                </div>
                                                </form>
                                            </li>
                                        </ul>
                                    </li>
                                    
                                    <%} %>
                                </ul>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="user-dashboard">
                
        <tiles:insertAttribute name="content"/>
        </div>
                
 
            </div>
            
        </div>
        

    </div>



    <!-- Modal -->
    <div id="add_project" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header login-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h4 class="modal-title">Add Project</h4>
                </div>
                <div class="modal-body">
                            <input type="text" placeholder="Project Title" name="name">
                            <input type="text" placeholder="Post of Post" name="mail">
                            <input type="text" placeholder="Author" name="passsword">
                            <textarea placeholder="Desicrption"></textarea>
                    </div>
                <div class="modal-footer">
                    <button type="button" class="cancel" data-dismiss="modal">Close</button>
                    <button type="button" class="add-project" data-dismiss="modal">Save</button>
                </div>
            </div>

        </div>
    </div>
</body>

    </html>