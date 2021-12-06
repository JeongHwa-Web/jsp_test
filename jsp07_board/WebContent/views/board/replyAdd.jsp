<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function replyCheck() {
		var email = frmReply.email.value;
		var subject = frmReply.subject.value;
		var content = frmReply.content.value;
		if(email==''){
			alert('이메일을 입력해주세요.');
			frmReply.email.focus();
		}else if(subject==''){
			alert('제목을 입력해주세요.');
			frmReply.subject.focus();
		}else if(content==''){
			alert('내용을 입력해주세요.');
			frmReply.content.focus();
		}else{
			frmReply.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>댓글</h2>
	<form action="${path}/reply/add" name="frmReply" method="get">
		부모<input type="text" value="${param.ref}" name="ref">
		순서<input type="text" value="${param.restep}" name="restep">
		레벨<input type="text" value="${param.relevel}" name="relevel">
		<table border="1">
			<tr>
				<th>이메일</th>
				<td> <input type="email" name="email" value="${sessionScope.email}"> </td>
			</tr>
			<tr>
				<th>제목</th>
				<td> <input type="text" name="subject"> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="20" name="content"></textarea> </td>
			</tr>
		</table>
		<button type="button" onclick="replyCheck()">등록</button>
	</form>
</body>
</html>