<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>    <script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.js"></script>
    <!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<script>
		var printImageList = "";
        $(document).ready(function() {
        
       
                //파일업로드 ajax 
                $("#uploadFile").on("change",function(event) {
                    event.preventDefault();
                    var formData = new FormData();
                    formData.append("file",$("#uploadFile")[0].files[0]);
                    
                    
                    $.ajax({
                        
                        type            :       "post",
                        url             :       "<%=request.getContextPath()%>/board/upload",
                        data            :       formData,
                        dataType        :       "json",
                        processData     :       false,
                        contentType     :       false, 
                        success         :       function(data) {
                            
                        
                        alert(data.makeThumbnail);
                   
                        var str = "";
                        var textstr = "";
                      
                        
                       <%--  str = "<div><a href='<%=request.getContextPath()%>/board/displayFile?fileName="+data.originalFile+"'>"; --%>
                        <%-- str += "<img src='<%=request.getContextPath()%>/board/displayFile?fileName="+data.makeThumbnail+"'></a></div>"; --%>
                        str += "<img src='<%=request.getContextPath()%>/board/displayFile?fileName="+data.makeThumbnail+"'>";
<%--                         textstr = "<div><a href='<%=request.getContextPath()%>/board/displayFile?fileName="+data.originalFile+"'>"; --%>
                        textstr += "<span style='color:#FF0000;'><img style='max-width:100%; height:auto'src='<%=request.getContextPath()%>/board/displayFile?fileName="+data.boardInsertImage+"'/></span>";
                        printImageList += "<span><img style='max-width:100%; height:auto'src='<%=request.getContextPath()%>/board/displayFile?fileName="+data.boardInsertImage+"'/></span>";
                        /*
                        	imageList : 썸네일 찍기위한 코드. 
                        	printImageList : 게시판에 붙일때 쓰는 코드
                        */
                        
                        
                        $("#textstr").val(printImageList);
                            
                        var imageList ="<div class='row'>" +
                     			 "<div class='col-xs-6 col-md-3'>" +
                     				"<a href='#' class='thumbnail'>" +
                     			str +
                     			"</a>" +
                     				"</div>" +
                     				"</div>";
                     				
                     		/* alert(imageList); */
                     			
                        	
                        
                        	$("#imageList").append(imageList);
                        	$("#textstr").val(printImageList);
                        	alert($("#textstr").val());
                        	
                        
                        }
                    })
                    
                    
                })
    });
    
    
        function send() {
            
            var textstr = $("#textstr").val();
            // 네이버 에디터에 값들 푸쉬해주기.
            opener.pasteHTML(textstr);
            // 현재창 닫기
            window.close();
        }
</script>

</head>
    
<body>
    

<!-- 이미지 업로드 append용 hidden -->
<input type="hidden" id="textstr" name="textstr" value=""/>   
    
<div class="page-header">
    <h1>이미지업로드 <small>Subtext for header</small></h1>
</div>
<div class="page-header">
    <input type="file" name="uploadFile" id="uploadFile" accept="image/*"> 
</div>

<div id="imageList">

</div>

<input type="button" class="btn btn-default" value="업로드" onclick="send()"/>

</body>

</html>