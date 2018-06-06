<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>​
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.js"></script>
<content tag="local_script">
<script>
	$(document).ready(function() {
		
		//--페이지 셋팅
		var totalPage = ${totalPage}; // 전체 페이지
		var startPage = ${startPage}; // 현재 페이지
		
		var pagination = "";
		
		//--페이지네이션에 항상 10개가 보이도록 조절
		var forStart = 0;
		var forEnd = 0;
		
		if((startPage-5) < 1) {
			forStart = 1;
		} else {
			forStart = startPage -5;
		}
		
		if(forStart == 1) {
			if(totalPage > 9) {
				forEnd = 10;
			} else {
				forEnd = totalPage;
			}
		}else {
			if((startPage+4) > totalPage) {
				forEnd = totalPage;
				
				if(forEnd > 9) {
					forStart = forEnd - 9
				}
			}else {
				forEnd = startPage + 4;
			}
		}
		//--페이지네이션에 항상 10개가 보이도록 조
		
		//전체 페이지 수를 받아 돌린다.
		
		for(var i = forStart ; i<= forEnd ; i++) {
			if(startPage == i) {
				pagination += '<button name="page_move" start_page="'+i+'" disabled>'+i+'</button>';
			} else {
				pagination += '<button name="page_move" start_page="'+i+'" style="cursor:pointer;" >'+i+'</button>';
			}
		}
		
		
		//하단 페이지 부분에 붙인다.
		$("#pagination").append(pagination);
		//--페이지 셋팅
		
		$("a[name='writer']").click(function() {
			location.href = "<%=request.getContextPath()%>/board/view?id="+$(this).attr("content_id");
		});
		
		$("#write").click(function() {
			location.href = "<%=request.getContextPath()%>/board/write";
		});
		
		$(document).on("click","button[name='page_move']", function(){
			
			var visiblePages = 30; // 리스트 보여줄 페이지
			
			$('#startPage').val($(this).attr("start_page")); // 보고 싶은 페이지
			$('#visiblePages').val(visiblePages);
			
			$("#frmSearch").submit();
		});
		
		
	
		
		
	});
	
</script>
</content>
<style>
.mouseOverHighlight {
	border-bottom: 1px solid blue;
	cursor: pointer !important;
	color: blue;
	pointer-events: auto;
}

    #board tr th {
        border: 1px solid black;
    }
#board tr td{
	border: 1px solid black;
}

    #board tr:nth-child(odd) td {
        background: rgb(208, 215, 226);
    }

</style>

</head>
<body>
	<form class="form-inline" id="frmSearch" action="<%=request.getContextPath()%>/board/list">
		<input type="hidden" id="startPage" name="startPage" value="">
		<!-- 페이징을 위한 hidden 타입 추가 -->
		<input type="hidden" id="visiblePages" name="visiblePages" value="">
		<!-- 페이징을 위한 hidden타입 추가  -->
		<div align="center">
			<table width="1200px">
				<tr>
					<td align="right">
						<button type="button" id="write" name="write">글 작성</button>
					</td>
				</tr>
			</table>
			<table id="board" width="1200px">
				<tr>
					<th width="40px" style="word-break: break-all">No</th>
					<th width="890px" >제목</th>
					<th width="20px">작성자</th>
					<th width="50px">작성일</th>
				</tr>
				<c:choose>
					<c:when test="${fn:length(boardList) == 0}">
						<tr>
							<td colspan="4" align="center">조회결과가 없습니다.</td>
						</tr>
					</c:when>
					
					<c:otherwise>
						<c:forEach var="boardList" items="${boardList }" varStatus="status">
							<tr>
								<td width="40px" style="word-break: break-all" align="center">${boardList.id}</td>
								<td style="padding-left: 20px"><a name="writer" class="mouseOverHighlight" content_id="${boardList.id }">${boardList.title }</a></td>
								<td  width="100px" align="center" style="word-break: break-all">${boardList.writer }</td>
								<td width="100px" style="word-break: break-all" align="center">
								</td>

							</tr>
                            
                        
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<br>
			<div id="pagination"></div>
		</div>

	</form>










</body>
</html>