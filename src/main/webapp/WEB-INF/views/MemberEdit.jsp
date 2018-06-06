<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>GETTING STARTED WITH BRACKETS</title>
        <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
        <script>
            $(document).ready(function() {
                //파일업로드 ajax 
                $("#fileupload").on("click",function(event) {
                    event.preventDefault();
                    var formData = new FormData();
                    formData.append("file",$("#uploadFile")[0].files[0]);
                    
                    
                    $.ajax({
                        
                        type            :       "post",
                        url             :       "<%=request.getContextPath()%>/member/image/upload",
                        data            :       formData,
                        dataType        :       "text",
                        processData     :       false,
                        contentType     :       false,
                        success         :       function(data) {
                            
                      
                      
                        
                            
                       
                        }
                    })
                    
                    
                })
                                 
            })
            
              
     //재입력 비밀번호 체크하여 가입버튼 비활성화 또는 맞지않음을 알림.
   	    function checkPwd() {
        var inputed = $("#password").val();
        var reinputed = $("#password1").val();
        console.log("첫번째 비밀번호 : " + inputed);
        console.log("두번째 비밀번호 : " + reinputed);
        
    
        if(reinputed == "" && (inputed != reinputed || inputed == reinputed)) {
           
            console.log("틀림 땡!!");
            $("#message").css({"color": "red" , "font-size": "10pt"});
            $("#message").text("비밀번호가 일치하지 않습니다.");
        } else if (inputed == reinputed) {
            console.log("비번 맞았음.");
            $("#message").css({"color": "black" ,"font-size": "10pt"});
            $("#message").text("비밀번호가 일치합니다.");
   			 }
		}
        </script>
    <style>
        
        #memberC {
            width: 600px ;
            height: 300px;
        
            
            
        }
        
        #memberC td {
            border: 1px solid black;
        }
        
        #memberC td:first-child {
            background: rgb(182,178,193);
                text-align: center;
        
            
        }
        
        #memberC td:last-child {
            width: 400px;
        }
        </style>

    </head>
    
    <body>
        <h3>회원정보 수정</h3>
          <hr>
    
    <form id="memberCheck" action="<%=request.getContextPath()%>/member/check" method="POST">
    <table id="memberC">
 
        <tr><td>아이디</td> <td><input type="text" id="id" name="id" length="12" readonly="readonly" value="${member.id }">   </td></tr>
        <tr><td>닉네임</td> <td><input type="text" id="nickname" name="nickname" length="12" readonly="readonly" value="${member.nickname }">   </td></tr>
        <tr><td> 비밀번호 </td> <td><input type="password" id="password" name="password" placeholder="패스워드" oninput="checkPwd()"> </td></tr>
        <tr> <td> 비밀번호 확인  </td><td><input type="password" id="password1" name="password1" placeholder="패스워드" oninput="checkPwd()" >  <span id="message"> </span></td>
        <tr> <td> e-mail</td><td> <input type="text" id="email" name="email" value="${member.email }"></td>
        </tr>
        <tr><td>이미지</td><td><img src="<%=request.getContextPath() %>/resources/imageupload/${member.imageurl }" width="64px" heigh="64px"> 
        
         <input type="file" name="uploadFile" id="uploadFile" accept="image/*"> 
         <input type="button" value="업로드" name="fileupload" id="fileupload" />
        
         </td></tr>

    </table>
        <hr>
             
	        <input type="submit" name="memberSubmit" id="memberSubmit" value="수정하기">        
       </form>

    
    </body>
</html>
