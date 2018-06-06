<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<content tag="local_script">

<script type="text/javascript"
	src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath() %>/resources/smarteditor/workspace/js/service/HuskyEZCreator.js">


</script>

<script>

var filestart = 1;


$(document).ready(function() {
				var oEditors = [];
				var sLang = "ko_KR"; // 언어 (ko_KR/ en_US/ ja_JP/ zh_CN/ zh_TW), default = ko_KR
				// 추가 글꼴 목록
				//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
				//Editor Setting
				nhn.husky.EZCreator
						.createInIFrame({
							oAppRef : oEditors,
							elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값 과 동일해야 함.
							sSkinURI : "<%=request.getContextPath()%>/resources/smarteditor/dist/SmartEditor2Skin.html", //Editor HTML
							htParams : {
								bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
								bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
								bUseModeChanger : true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
								//bSkipXssFilter : true,		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
								//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
								fOnBeforeUnload : function() {
									//alert("완료!");
								},
								I18N_LOCALE : sLang
							}, //boolean
							fOnAppLoad : function() {
								//예제 코드
								//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
							},
							fCreator : "createSEditor2"
						});
				function pasteHTML() {
					var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
					oEditors.getById["smarteditor"].exec("PASTE_HTML",
							[ sHTML ]);
				}
				function showHTML() {
					var sHTML = oEditors.getById["smarteditor"].getIR();
					alert(sHTML);
				}
				function submitContents(elClickedObj) {
					oEditors.getById["smarteditor"].exec(
							"UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.
					// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
					try {
						elClickedObj.form.submit();
					} catch (e) {
					}
				}
				function setDefaultFont() {
					var sDefaultFont = '궁서';
					var nFontSize = 24;
					oEditors.getById["smarteditor"].setDefaultFont(
							sDefaultFont, nFontSize);
				}
				
				//전송버튼 클릭 이벤트
				$("#savebutton").click(function() {
					//if(confirm("저장하시겠습니까?")) {
						//id가 smarteditor인 textarea에 에디터에서 대입
						oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
						
						//이 부분에 에디터 validation 검증
						
							$("#frm").submit();
						
					}
					
				)
				
				//텍스트 에리아 확인
				$("#refresh").click(function() {
					oEditors.getById["smarteditor"].exec("PASTE_HTML", ["hihihihihihi"]);
					
					})
				
				
				
				//파일 추가 버튼
				$("#uploadbutton").click(function() {
					
					filestart++;
					$('<div><input type="file" name="uploadFile'+filestart+'" accept="image/*" /></div>').insertBefore(this);
					
					
				
				})

                //파일업로드 ajax 
                $("#ajaxUpload").on("click",function(event) {
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
                        
                        str = "<div><a href='<%=request.getContextPath()%>/board/displayFile?fileName="+data.originalFile+"'>";
                        str += "<img src='<%=request.getContextPath()%>/board/displayFile?fileName="+data.makeThumbnail+"'></a></div>";
                        textstr = "<div><a href='<%=request.getContextPath()%>/board/displayFile?fileName="+data.originalFile+"'>";
                        textstr += "<img style='max-width:100%; height:auto'src='<%=request.getContextPath()%>/board/displayFile?fileName="+data.boardInsertImage+"'/></a>";
                    
                        
                            
                        $(".uploadList").append(str);
                        oEditors.getById["smarteditor"].exec("PASTE_HTML", [textstr]);
                        }
                    })
                    
                    
                })
				
		
			
				
			});

//필수값 Check
function validation() {

var contents = $.trim(oEditors[0].getContents());
if(contents === '<p>&bnsp;<p>' || contents === '') { //기본적으로 아무것도 입력하지 않아도 값이 입력되어 있음.

	alert("내용을 입력하세요.");
	oEditors.getById['smarteditor'].exec('FOCUS');
	return false;
	
}
return true;
}



	
	

</script>
</content>
<style>
    
    #boardwrite {
     width: 100%
  
    }
    #boardwrite tr{
        
  
    }
    
    #boardwrite tr td {
   
    }
    

    
</style>
</head>
<body>

		<form action="<%=request.getContextPath() %>/board/save" method="post" id="frm" encType="multipart/form-data">
            <table id="boardwrite">
                <tr>
                <td> 제목 </td>
                <td>
	<input type="text" id="title" name="title" placeholder="제목을 입력하세요."/> </td>
                </tr>
                
                
                <tr>
                    <td> 내용 </td>
                    <td><textarea name="smarteditor" id="smarteditor" rows="10" cols="100"
			style="width: 500px; height: 412px;">
	
	
	</textarea> </td>
                
                </tr>
                
                
                <tr>
                
                    <td> 사진 업로드</td>
                    <td><input type="file"  id="uploadFile" name="uploadFile" accept="image/*"/>
                    <input type="button"  id="ajaxUpload" name="ajaxUpload" value="사진 업로드"/>
                    </td>
                </tr>
                
                <tr>
                <td colspan="1"><div class="uploadList"> 썸네일 </div></td>
                </tr>
             
            </table>
	
	<input type="button" id="savebutton" name="savebutton" value="제출">
	</form>


</body>
</html>