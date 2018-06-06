<%@page import="org.springframework.ui.Model"%>
<%@page import="com.min.www.dto.member.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		
		
		$("#list").click(function() {
			location.href = "<%=request.getContextPath()%>/board/list";
		});
		
		//댓글 저장
		$("#reply_save").click(function(){
			
			//널 검사
			
			
			if($("#reply_content").val().trim() == "") {
				alert("댓글 내용을 입력하세요.");
				$("#reply_content").focus();
				return false;
			}
			
			var reply_content = $("#reply_content").val().replace("\n", "<br>"); //개행 처리
			
			
			//값 셋팅
			var objParams = {
					board_id				:	$("#board_id").val(),
					parent_id			:	"0",
					depth				:	"0",
					reply_content		:	reply_content,
					reply_writer 		:	$("#reply_writer").val(),
					IMAGEURL				: $("#reply_image").attr("src")

					
			};
			
			var reply_id;
			
			//ajax 호출
			$.ajax({
				url				:	"<%=request.getContextPath()%>/board/reply/save",
				dataType			:	"json",
				contentType		:	"application/x-www-form-urlencoded; charset=UTF-8",
				type				:	"post",
				async			:	false, //동기: false, 비동기 : ture 
				data				:	objParams,
				success			:	function(retVal) {
					
					if(retVal.code != "OK") {
						alert(retVal.message);
					} else {
						reply_id = retVal.reply_id;
					}
				},
				error			:	function(request, status,error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					console.log("AJAX_ERROR");
				}
			});
			
			//end save
			var reply_area = $("#reply_area");
			
			var reply =
				'<tr reply_type="main">'+
				'	<td width="870px">' +
				reply_content +
				'	</td>'+
				'	<td width="100px">'
				$("#reply_writer").val()+
				'	</td>'+
				'	<td width="100px">'+
				'		<input type="password" id="reply_password_'+ reply_id + '" style="width:100px;" maxlength="10" placeholder="패스워드"/>' +
				'	<td>'+
				'		<button name="reply_del" reply_id ="'+ reply_id +'">삭제</button>' +
				'		<button name="reply_del" reply_id ="'+ reply_id +'">댓글</button>' +
				'	</td>' +
				'</tr>';
				
				if($('#reply_area').contents().size() ==0) {
					$('#reply_area').append(reply);
				}else {
					$('#reply_area tr:last').after(reply);
				}
				
				//댓글 초기화
				
				$("#reply_content").val("");
				location.reload();
		});
		
		//댓글 삭제 (다시 리뷰하기)
		$("button[name='reply_del']").click(function(){
			
			var check = false;
			var reply_id = $(this).attr("reply_id");
			var reply_password = "reply_password_"+reply_id;
			
			if($("#"+reply_password).val().trim() == "") {
				alert("패스워드를 입력하세요.");
				$("#"+reply_password).focus();
				return false;
			}
			
			//패스워드와 아이디를 넘겨 삭제한다.
			//값 셋팅.
			var objParams = {
					reply_password		:	$("#"+reply_password).val(),
					reply_id				:	reply_id
			};
			
			//ajax 호출
			$.ajax({
				url			:	"/board/reply/del",
				dataType		:	"json",
				contentType	:	"application/x-www-form-urlencoded; charset=UTF-8",
				type			:	"post",
				async		:	false, //동기 : false , 비동기 : true 
				data 		: 	objParams,
				success 		:	function(retVal) {
					
					if(retVal.code != "OK") {
						alert(retVal.message);
					} else {
						check = true;
					}
				},
				error		:	function(request,status,error) {
					console.log("AJAX_ERROR");
				}
			});
			
			if(check) { //(리뷰 다시하기 )
				//삭제하면서 하위 댓글도 삭제
				var prevTr = $(this).parent().parent().next(); // 댓글의 다음 
				
				while(prevTr.attr("reply_type") == "sub") { //댓글의 다음이 sub 계속 넘어감.
					prevTr = prevTr.next();
					prevTr.prev().remove();					
				}
				
				//마지막 리플 처리 
				if(prevTr.attr("reply_type") == undefined){
                    prevTr = $(this).parent().parent();
                    prevTr.remove();
                }
                 
                $(this).parent().parent().remove(); 
            }
             
        });
         
        //대댓글 입력창
        $(document).on("click","button[name='reply_reply']",function(){ //동적 이벤트
             
            $("#reply_add").remove();
             
            var reply_id = $(this).attr("reply_id");
            var last_check = false;//마지막 tr 체크
             
            //입력받는 창 등록
             var replyEditor = 
                '<tr id="reply_add" class="reply_reply">'+
                '   <td width="870px">'+
                '       <textarea name="reply_reply_content" rows="3" cols="50"></textarea>'+
                '   </td>'+
                '   <td width="100px">'+
                '       <input type="text" name="reply_reply_writer" style="width:100%;" maxlength="10" placeholder="작성자"/>'+
                '   </td>'+
                '   <td>'+
                '       <button name="reply_reply_save" reply_id="'+reply_id+'">등록</button>'+
                '       <button name="reply_reply_cancel">취소</button>'+
                '   </td>'+
                '</tr>';
                 
            var prevTr = $(this).parent().parent().next();
             
            //부모의 부모 다음이 sub이면 마지막 sub 뒤에 붙인다.
            //마지막 리플 처리
            if(prevTr.attr("reply_type") == undefined){
                prevTr = $(this).parent().parent();
            }else{
                while(prevTr.attr("reply_type")=="sub"){//댓글의 다음이 sub면 계속 넘어감
                    prevTr = prevTr.next();
                }
                 
                if(prevTr.attr("reply_type") == undefined){//next뒤에 tr이 없다면 마지막이라는 표시를 해주자
                    last_check = true;
                }else{
                    prevTr = prevTr.prev();
                }
                 
            }
             
            if(last_check){//마지막이라면 제일 마지막 tr 뒤에 댓글 입력을 붙인다.
                $('#reply_area tr:last').after(replyEditor);    
            }else{
                prevTr.after(replyEditor);
            }
             
        });
         
        //대댓글 등록
        $(document).on("click","button[name='reply_reply_save']",function(){
                                 
            var reply_reply_writer = $("input[name='reply_reply_writer']");
            var reply_reply_password = $("input[name='reply_reply_password']");
            var reply_reply_content = $("textarea[name='reply_reply_content']");
            var reply_reply_content_val = reply_reply_content.val().replace("\n", "<br>"); //개행처리
             
            //널 검사
            if(reply_reply_writer.val().trim() == ""){
                alert("이름을 입력하세요.");
                reply_reply_writer.focus();
                return false;
            }
             
            if(reply_reply_password.val().trim() == ""){
                alert("패스워드를 입력하세요.");
                reply_reply_password.focus();
                return false;
            }
             
            if(reply_reply_content.val().trim() == ""){
                alert("내용을 입력하세요.");
                reply_reply_content.focus();
                return false;
            }
             
            //값 셋팅
            var objParams = {
                    board_id        : $("#board_id").val(),
                    parent_id       : $(this).attr("reply_id"), 
                    depth           : "1",
                    reply_writer    : reply_reply_writer.val(),
                    reply_password  : reply_reply_password.val(),
                    reply_content   : reply_reply_content_val
            };
             
            var reply_id;
             
            //ajax 호출
            $.ajax({
                url         :   "/board/reply/save",
                dataType    :   "json",
                contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
                type        :   "post",
                async       :   false, //동기: false, 비동기: ture
                data        :   objParams,
                success     :   function(retVal){

                    if(retVal.code != "OK") {
                        alert(retVal.message);
                    }else{
                        reply_id = retVal.reply_id;
                    }
                     
                },
                error       :   function(request, status, error){
                    console.log("AJAX_ERROR");
                }
            });
             
            var reply = 
                '<tr reply_type="sub">'+
                '   <td width="870px"> → '+
                reply_reply_content_val+
                '   </td>'+
                '   <td width="100px">'+
                reply_reply_writer.val()+
                '   </td>'+
                '   <td width="100px">'+
                '       <input type="password" id="reply_password_'+reply_id+'" style="width:100px;" maxlength="10" placeholder="패스워드"/>'+
                '   </td>'+
                '   <td>'+
                '       <button name="reply_del" reply_id = "'+reply_id+'">삭제</button>'+
                '   </td>'+
                '</tr>';
                 
            var prevTr = $(this).parent().parent().prev();
             
            prevTr.after(reply);
                                 
            $("#reply_add").remove();
             
        });
         
        //대댓글 입력창 취소
        $(document).on("click","button[name='reply_reply_cancel']",function(){
            $("#reply_add").remove();
        });
         
        //글수정
        $("#modify").click(function(){
             
            var password = $("input[name='password']");
             
            if(password.val().trim() == ""){
                alert("패스워드를 입력하세요.");
                password.focus();
                return false;
            }
                                 
            //ajax로 패스워드 검수 후 수정 페이지로 포워딩
            //값 셋팅
            var objParams = {
                    id       : $("#board_id").val(),    
                    password : $("#password").val()
            };
                                 
            //ajax 호출
            $.ajax({
                url         :   "/board/check",
                dataType    :   "json",
                contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
                type        :   "post",
                async       :   false, //동기: false, 비동기: ture
                data        :   objParams,
                success     :   function(retVal){

                    if(retVal.code != "OK") {
                        alert(retVal.message);
                    }else{
                        location.href = "/board/edit?id="+$("#board_id").val();
                    }
                     
                },
                error       :   function(request, status, error){
                    console.log("AJAX_ERROR");
                }
            });
             
        });
         
        //글 삭제
        $("#delete").click(function(){
             
            var password = $("input[name='password']");
             
            if(password.val().trim() == ""){
                alert("패스워드를 입력하세요.");
                password.focus();
                return false;
            }
             
            //ajax로 패스워드 검수 후 수정 페이지로 포워딩
            //값 셋팅
            var objParams = {
                    id       : $("#board_id").val(),    
                    password : $("#password").val()
            };
                                 
            //ajax 호출
            $.ajax({
                url         :   "/board/del",
                dataType    :   "json",
                contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
                type        :   "post",
                async       :   false, //동기: false, 비동기: ture
                data        :   objParams,
                success     :   function(retVal){

                    if(retVal.code != "OK") {
                        alert(retVal.message);
                    }else{
                        alert("삭제 되었습니다.");
                        location.href = "/board/list";
                    }
                     
                },
                error       :   function(request, status, error){
                    console.log("AJAX_ERROR");
                }
            });
             
        });
         
    });
</script>
</head>
<style>

    #boardmain {
   		width : 100%;
    		word-break : break-all;
    		overflow : hidden;
    }
#boardmain tr:nth-child(2) td {
	border-bottom: 0.5px solid black;
	padding: 10px;
}

#boardmain tr:nth-child(3) td {
	border-bottom: 0.5px solid black;
	padding: 30px 10px;
}

#boardmain tr:nth-child(4) td {
	border-bottom: 0.5px solid black;
	padding: 10px;
}
</style>
<body>
	<%MemberDto memberInfo= (MemberDto)session.getAttribute("memberInfo"); %>


	<input type="hidden" id="board_id" name="board_id"
		value="${boardView.id}" />
	<div style="overflow : hidden;">
		<br> <br>
		<table width="" id="boardmain">
			<tr>
				<td colspan="2" align="right">
					<button id="modify" name="modify">글 수정</button>
					<button id="delete" name="delete">글 삭제</button>
				</td>
			</tr>
			<tr>
				<td colspan="2">작성자 : ${boardView.writer }</td>
			</tr>
			<tr>
				<td width="">제목 : ${boardView.title }</td>
			</tr>


			<tr height="500px">
				<td colspan="2" valign="top">${boardView.content }</td>
			</tr>
		</table>
		<!--  
		<table id="boardreply" width="1200px">
			<tr reply_type="all">
				<!--  뒤에 댓글 붙이기 쉽게 선언  
				<td colspan="4"></td>
			</tr> -
			<!-- 댓글이 들어갈 공간  -->
			<c:forEach var="replyList" items="${replyList }" varStatus="status">
			

			<div class="media">
		
 					 <div class="media-left">
					<a href="<%=request.getContextPath() %>/resources/imageupload/${replyList.ORIGINALIMAGEURL }"> <img class="media-object" src="<%=request.getContextPath() %>/resources/imageupload/${replyList.IMAGEURL }"
						alt="...">
					</a>
					</div>
				<div class="media-body">
					<h5 class="media-heading">${replyList.reply_writer }</h5>
					${replyList.reply_content }
				</div>
				
				</div>
				<!--  
				<tr
					reply_type="<c:if test="${replyList.depth == '0' }">main</c:if><c:if test="${replyList.depth == '1' }">sub</c:if>">
					<!--  댓글의 depth 표시  
					<td width="870px"><c:if test="${replyList.depth == '1' }"> → </c:if>${replyList.reply_content }
					</td>
					<td width="100px">${replyList.reply_writer }</td>

					<td>
						<button name="reply_del" reply_id="${replyList.reply_id }">삭제</button>
						<c:if test="${replyList.depth != '1' }">
							<button name="reply_reply" reply_id="${replyList.reply_id }">댓글</button>
							<!-- 첫 댓글에만 댓글이 추가 대댓글 불가 
						</c:if>
					</td>
				</tr>
				 -->
			</c:forEach>
			<!--  
		</table>
		-->
		<%if(memberInfo != null) { %>
		
		<div class="media">

	
	
 					 <div class="media-left">
					> <img class="media-object" id="reply_image" src="<%=request.getContextPath() %>/resources/imageupload/<%=memberInfo.getImageurl() %>"
						alt="...">
					
					</div>
				<div class="media-body">
					<h5 class="media-heading" id="reply_writer" name="reply_writer"><%=memberInfo.getNickname() %></h5>
					
					<textarea id="reply_content" name="reply_content" rows="4"
						cols="100" placeholder="댓글을 입력하세요."></textarea>

					<button id="reply_save" name="reply_save">댓글 등록</button>
					
				</div>
				
				</div>
				
				<%} %>
				
				<!-- 
		<table id="boardreplywrite" width="1200px" bordercolor="#46AA46">
			<tr>
				<td>사용자</td>
				<td><textarea id="reply_content" name="reply_content" rows="4"
						cols="100" placeholder="댓글을 입력하세요."></textarea></td>

				<td width="500px">

					<button id="reply_save" name="reply_save">댓글 등록</button>
				</td>


			</tr>

		</table>
		-->

	</div>


</body>
</html>