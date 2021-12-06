<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.reply{
		display: flex;
	}
</style>
<script type="text/javascript">
	function removeCheck() {
		if('${sessionScope.email}'!='${map.board.email}'){			
			alert('권한이없습니다.');
			return;
		}
		var remove = confirm('삭제하시겠습니까?');
		if(remove){
			location.href="${path}/board/remove?bnum=${map.board.bnum}";
		}
	}
	
	function replyRemoveCheck(rnum,email) {
		if('${sessionScope.email}'!=email){			
			alert('권한이없습니다.');
			return;
		}
		var remove = confirm('삭제하시겠습니까?');
		if(remove){
			location.href="${path}/reply/remove?rnum="+rnum+"&bnum=${map.board.bnum}";
		}else{
			alert('취소되었습니다.');
		}
	}
	
	function modiformCheck() {
		if('${sessionScope.email}'=='${map.board.email}'){			
			location.href='${path}/board/modiForm?bnum=${map.board.bnum}';
		}else{
			alert('권한이없습니다.');
		}
	}
	
	function replyModiformCheck(bnum,email) {
		console.log(email);
		if('${sessionScope.email}'==email){			
			location.href='${path}/reply/modiform?bnum='+bnum+'&rnum=${map.board.bnum}';
		}else{
			alert('권한이없습니다.');
		}
	}
</script>
<body>
	<%@ include file="../header.jsp" %>
	<h2>상세조회</h2>
	<table border="1">
		<tr>
			<th>No.</th>
			<td>${map.board.bnum}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${map.board.email}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${map.board.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><pre>${map.board.content}</pre></td>
		</tr>
		<tr>
			<th>파일</th>
			<td>
				<c:forEach var="i" items="${map.boardFile}">
					${i.fileName} <button type="button" onclick="location.href='${path}/file/download?fileName=${i.fileName}'">다운로드</button> <br>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${map.board.readcnt}</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td><fmt:formatDate value="${map.board.regidate}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<th>수정일자</th>
			<td><fmt:formatDate value="${map.board.modidate}" pattern="yyyy-MM-dd"/></td>
		</tr>
	</table>
	<button type="button" onclick="modiformCheck()">수정</button>
	<button type="button" onclick="removeCheck()">삭제</button>
	<button type="button" onclick="location.href='${path}/views/board/replyAdd.jsp?ref=${map.board.ref}&restep=${map.board.restep}&relevel=${map.board.relevel}'">댓글</button>
	<hr>
	<c:forEach var="reply" items="${map.rlist}">
		<div class="reply">
			<div>
				<c:forEach var="i" begin="1" end="${reply.relevel}">
					<img alt="" src="${path}/img/reply.png" width="20">
				</c:forEach>
			</div>
			<div>
				번호: ${reply.bnum} <br>
				작성자: ${reply.email} <br>
				제목: ${reply.subject} <br>
				내용: ${reply.content} <br>
				작성일자: <fmt:formatDate value="${reply.regidate}" pattern="yyyy-MM-dd HH-mm"/> <br>
				수정일자: <fmt:formatDate value="${reply.modidate}" pattern="yyyy-MM-dd HH-mm"/> <br>
				<button type="button" onclick="location.href='${path}/views/board/replyAdd.jsp?ref=${reply.ref}&restep=${reply.restep}&relevel=${reply.relevel}'">답글</button>
				<button type="button" onclick="replyModiformCheck(${reply.bnum},'${reply.email}')">수정</button>
				<button type="button" onclick="replyRemoveCheck(${reply.bnum},'${reply.email}')">삭제</button>
			</div>		
		</div>
		<hr>
	</c:forEach>
</body>
</html>