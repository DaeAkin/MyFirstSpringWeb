<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>회원가입</title>
   <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
        <script>
            $(document).ready(function() {
                //파일업로드 ajax 
                
                $("#uploadFile").on("change",function(event) {
                    event.preventDefault();
                    var formData = new FormData();
                    formData.append("file",$("#uploadFile")[0].files[0]);
                    
                    
                   
                    
                    $.ajax({
                        
                        type            :       "post",
                        url             :       "<%=request.getContextPath()%>/member/image/upload",
                        data            :       formData,
                        dataType        :       "json",
                        processData     :       false,
                        contentType     :       false,
                        success         :       function(data) {
                     
                    
                    /* hidden으로 원래 이미지 , 크롭한 이미지 이름 적어주고 
                       파일 올렸으면 그파일로 src 변경*/
                        $("#profileImageView").attr("src","<%=request.getContextPath()%>/resources/profileImage/" + data.IMAGEURL) 
                   		
                        $("#ORIGINALIMAGEURL").val(data.ORIGINALIMAGEURL)
                        $("#IMAGEURL").val(data.IMAGEURL)
                   		
                        }
                    })
                    
                    
                })
                                 
            })
  
  
  
      

  </script>
  
      <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/memberSignupForm.css">

  
</head>

<body>

  	 

 <form id="memberCheck" action="<%=request.getContextPath()%>/member/memberSignupFromInsert" method="POST" enctype="multipart/form-data" >

  <h2>회원가입</h2>
 
 	<c:set var="isInvalidId" value="${returnJsp.isInvalid.isInvalidId}">
 	</c:set>
 	

    
		<p>
			<label for="Email" class="floatLabel">아이디</label>
			<input id="memberSignform_id" name="id" type="text" value="${returnJsp.id }">
			
		    <c:if test="${returnJsp.isInvalid.isInvalidId == false}">
		   아이디가 중복되었습니다.
		    </c:if>
			
		</p>
    		<p>
			<label for="Email" class="floatLabel">닉네임</label>
			<input id="memberSignform_nickname" name="nickname" type="text" value="${returnJsp.nickname }">
			    <c:if test="${returnJsp.isInvalid.isInvalidNickname == false}">
   				닉네임이 중복되었습니다.
   				 </c:if>
		</p>
    		<p>
			<label for="Email" class="floatLabel">이메일</label>
			<input id="memberSignform_email" name="email" type="text" value="${returnJsp.email }">
		</p>
		<p>
			<label for="password" class="floatLabel">비밀번호</label>
			<input id="password" name="password" type="password" value="${returnJsp.password }">
			<span>비밀번호는 8자 이상 필요합니다.</span>
		</p>
		<p>
			<label for="confirm_password" class="floatLabel">비밀번호 확인</label>
			<input id="confirm_password" name="confirm_password" type="password" value="${returnJsp.password }">
			<span>비밀번호가 맞지 않습니다.</span>
		</p>
    

        <div>
            사용자 이미지 업로드
            <input type="hidden" id="IMAGEURL" name="IMAGEURL" value=""/>
            <input type="hidden" id="ORIGINALIMAGEURL" name="ORIGINALIMAGEURL" value=""/>
            <input type="file" name="uploadFile" id="uploadFile" accept="image/*"> 
        
            <img id="profileImageView" src="<%=request.getContextPath() %>/resources/profileImage/64x64.svg" width="64px" heigh="64px"> 
        </div>
    <hr>
		<p>
			<input type="submit" value="계정 생성" id="submit">
            
		</p>
    
	</form>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

  

    <script  src="<%=request.getContextPath() %>/resources/js/memberSignupForm.js"></script>




</body>

</html>
