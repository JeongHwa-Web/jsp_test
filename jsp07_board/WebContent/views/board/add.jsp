<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<%@ include file="../include/sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function addCheck() {
		var email = frmAdd.email.value;
		var subject = frmAdd.subject.value;
		var content = frmAdd.content.value;
		if(email==''){
			alert('이메일을 입력해주세요.');
			frmAdd.email.focus();
		}else if(subject==''){
			alert('제목을 입력해주세요.');
			frmAdd.subject.focus();
		}else if(content==''){
			alert('내용을 입력해주세요.');
			frmAdd.content.focus();
		}else{
			frmAdd.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>게시글 등록</h2>
	<form action="${path}/board/add" name="frmAdd" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>이메일</th>
				<td> <input type="email" name="email" value="${sessionScope.email}" readonly="readonly"> </td>
			</tr>
			<tr>
				<th>제목</th>
				<td> <input type="text" name="subject"> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="20" name="content"></textarea> </td>
			</tr>
			<tr>
				<th>파일</th>
				<td>
					<input type="file" name="file1"> <br>
					<input type="file" name="file2"> <br>
					<input type="file" name="file3"> <br>
				</td>
			</tr>
		</table>
		<button type="button" onclick="addCheck()">등록</button>
	</form>
</body>
</html>