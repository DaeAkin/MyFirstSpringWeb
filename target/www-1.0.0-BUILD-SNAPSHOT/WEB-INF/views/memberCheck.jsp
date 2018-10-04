<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
    	
       //아이디 중복확인하기.
       $("#memberIdcheck").click(function() {
    	 		
       	//버튼식으로 할 예정. 실시간으로 하면 DB에 무리에 갈듯하다.
       	//ajax로 아이디 검수후 알려주기.
       	
       	console.log("클릭 되었니?");
       	
       if($("#id").val().trim() == "") {
    	   	alert("아이디를 입력해주세요");
    	   	return false;
       } 
       	
       	
       	var objParams = {
       			id			:	$("#id").val()
       	};
       	
       	//ajax 호출
       	$.ajax ({
       		url			:	"<%=request.getContextPath()%>/member/idCheck",
       		dataType		:	"json",
       		contentType	:	"application/x-www-form-urlencoded; charset=UTF-8",
       		type			:	"post",
       		async		:	false, //동기식으로 처리하는 방법 동기식 false, 비동기식 true
       		/*동기식으로 처리하면 
       		[Deprecation] Synchronous XMLHttpRequest on the main thread is 
       		deprecated because of its detrimental effects to the end user's
       		experience. For more help, check https://xhr.spec.whatwg.org/. 
       		이런 알림이 뜬다.
       		*/
       		data			:	objParams,
       		success		:	function(reVal) {
       			
       			if(reVal.code != "OK") {
       				alert("사용불가능한 아이디입니다. 다른아이디를 입력해주세요.");
       				
       				console.log("사용 불가능한 아이디.");
       			} else {
       				
       				// ajax가 성공하면 할일
       				alert("사용가능한 아이디입니다.");
       			
       							console.log("사용 가능한 아이디.");
       		} 
       			},
       		error		:	function(request, status, error) {
       			console.log("AJAX_ERROR");
       		}
       		
       			
       		
       		
       		
       	});
     
       	
       })
        
           $("#memberSubmit").click(function() {
            
            $("#membercheck").submit();
            
        })
        
        
        /* 닉네임 중복확인 */
        
        
        
        $("#memberNickcheck").click(function() {
    	 		
       	//버튼식으로 할 예정. 실시간으로 하면 DB에 무리에 갈듯하다.
       	//ajax로 아이디 검수후 알려주기.
       	
       	console.log("클릭 되었니?");
       	
       if($("#nickname").val().trim() == "") {
    	   	alert("닉네임을 입력해주세요");
    	   	return false;
       } 
       	
       	
       	var objParams = {
       			nickname			:	$("#nickname").val()
       	};
       	
       	//ajax 호출
       	$.ajax ({
       		url			:	"<%=request.getContextPath()%>/member/nickCheck",
       		dataType		:	"json",
       		contentType	:	"application/x-www-form-urlencoded; charset=UTF-8",
       		type			:	"post",
       		async		:	false, //동기식으로 처리하는 방법 동기식 false, 비동기식 true
       		/*동기식으로 처리하면 
       		[Deprecation] Synchronous XMLHttpRequest on the main thread is 
       		deprecated because of its detrimental effects to the end user's
       		experience. For more help, check https://xhr.spec.whatwg.org/. 
       		이런 알림이 뜬다.
       		*/
       		data			:	objParams,
       		success		:	function(reVal) {
       			
       			if(reVal.code != "OK") {
       				alert("사용불가능한 닉네임입니다. 다른닉네임을 입력해주세요.");
       				
       				console.log("사용 불가능한 닉.");
       			} else {
       				
       				// ajax가 성공하면 할일
       				alert("사용가능한 닉네임입니다.");
       			
       							console.log("사용 가능한 아이디.");
       		} 
       			},
       		error		:	function(request, status, error) {
       			console.log("AJAX_ERROR");
       		}
       		
       			
       		
       		
       		
       	});
     
       	
       })
        
           $("#memberSubmit").click(function() {
            
            $("#membercheck").submit();
            
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
    

    <h3>회원 가입</h3>
    <hr>
    
    <form id="memberCheck" action="<%=request.getContextPath()%>/member/check" method="POST">
    <table id="memberC">
        <tr><td>아이디</td> <td><input type="text" id="id" name="id" length="12" placeholder="아이디">    <input type="button" value="아이디 중복확인" id="memberIdcheck" name="memberIdcheck"> </td></tr>
        <tr><td>닉네임</td> <td><input type="text" id="nickname" name="nickname" length="12" placeholder="닉네임">    <input type="button" value="닉네임 중복확인" id="memberNickcheck" name="memberNickcheck"> </td></tr>
        <tr><td> 비밀번호 </td> <td><input type="password" id="password" name="password" placeholder="패스워드" oninput="checkPwd()"> </td></tr>
        <tr> <td> 비밀번호 확인  </td><td><input type="password" id="password1" name="password1" placeholder="패스워드" oninput="checkPwd()" >  <span id="message"> </span></td>
        <tr> <td> e-mail</td><td> <input type="email" id="email" name="email" placeholder="이메일"></td>
        </tr>

    </table>
        <hr>
             
	        <input type="submit" name="memberSubmit" id="memberSubmit" value="회원가입하기">        
       </form>

         
    
</body>
