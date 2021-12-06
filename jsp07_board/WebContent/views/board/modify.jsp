<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function modiCheck() {
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
	<h2>게시글 수정</h2>
	<form action="${path}/board/modify" name="frmModi" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>No.</th>
				<td> <input type="number" name="bnum" value="${map.board.bnum}" readonly="readonly"> </td>
			</tr>
			<tr>
				<th>이메일</th>
				<td> <input type="email" name="email" value="${map.board.email}" readonly="readonly"> </td>
			</tr>
			<tr>
				<th>제목</th>
				<td> <input type="text" name="subject" value="${map.board.subject}"> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="20" name="content">${map.board.content}</textarea> </td>
			</tr>
			<tr>
				<th>파일</th>
				<td>
					<c:forEach var="i" items="${map.boardFile}">
						${i.fileName} <input type="checkbox" name="filedel" value="${i.fnum}">삭제 <br>
					</c:forEach>
					<input type="file" name="file1"> <br>
					<input type="file" name="file2"> <br>
					<input type="file" name="file3"> <br>
				</td>
			</tr>
		</table>
		<button type="button" onclick="modiCheck()">등록</button>
	</form>
</body>
</html>