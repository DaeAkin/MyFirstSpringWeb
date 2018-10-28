<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>회원가입</title>
  
  
  
      <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/memberSignupForm.css">

  
</head>

<body>

  	 

 <form id="memberCheck" action="<%=request.getContextPath()%>/member/memberSignupFromInsert" method="POST" enctype="multipart/form-data" >
  <h2>회원가입</h2>
		<p>
			<label for="Email" class="floatLabel">아이디</label>
			<input id="memberSignform_id" name="id" type="text">
		</p>
    		<p>
			<label for="Email" class="floatLabel">닉네임</label>
			<input id="memberSignform_nickname" name="nickname" type="text">
		</p>
    		<p>
			<label for="Email" class="floatLabel">이메일</label>
			<input id="memberSignform_email" name="email" type="text">
		</p>
		<p>
			<label for="password" class="floatLabel">비밀번호</label>
			<input id="password" name="password" type="password">
			<span>비밀번호는 8자 이상 필요합니다.</span>
		</p>
		<p>
			<label for="confirm_password" class="floatLabel">비밀번호 확인</label>
			<input id="confirm_password" name="confirm_password" type="password">
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
