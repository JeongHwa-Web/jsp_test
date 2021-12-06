<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function modifyCheck() {
		var subject = frmModi.subject.value;
		var content = frmModi.content.value;
		if(subject==''){
			alert('제목을 입력해주세요.');
			frmModi.subject.focus();
		}else if(content==''){
			alert('내용을 입력해주세요.');
			frmModi.content.focus();
		}else{
			frmModi.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>댓글수정</h2>
	<form action="${path}/reply/modify" name="frmModi">
		부모<input type="text" value="${board.ref}" name="ref">
		순서<input type="text" value="${board.restep}" name="restep">
		레벨<input type="text" value="${board.relevel}" name="relevel">
		<input type="hidden" name="ref" value="${rnum}">
		<table border="1">
			<tr>
				<th>No.</th>
				<td> <input type="text" name="bnum" value="${board.bnum}" readonly="readonly"> </td>
			</tr>
			<tr>
				<th>이메일</th>
				<td> <input type="email" name="email" value="${board.email}" readonly="readonly"> </td>
			</tr>
			<tr>
				<th>제목</th>
				<td> <input type="text" name="subject" value="${board.subject}"> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="20" name="content">${board.content}</textarea> </td>
			</tr>
		</table>
		<button type="button" onclick="modifyCheck()">등록</button>
	</form>
</body>
</html>