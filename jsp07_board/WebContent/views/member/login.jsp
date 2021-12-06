<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<%@ include file="../include/msg.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<script type="text/javascript">
	function loginCheck() {
		var email = frmLogin.email.value;
		var userpw = frmLogin.userpw.value;
		if(email==''){
			alert('이메일을 입력하세요.');
			frmLogin.email.focus();
		}else if (userpw==''){
			alert('비밀번호를 입력하세요');
			frmLogin.userpw.focus();			
		}else{
			frmLogin.submit();
		}
	}
</script>
<body>
	<%@ include file="../header.jsp" %>
	<h2>로그인</h2>
	<form action="${path}/member/login" name="frmLogin" method="post">
		<table>
			<tr> 
				<th>이메일</th>
				<td> <input type="email" name="email" value="${cookie.email.value}"> </td>
			</tr>
			<tr> 
				<th>비밀번호</th>
				<td> <input type="password" name="userpw"> </td>
			</tr>
			<tr> 
				<td> <button type="button" onclick="loginCheck()">로그인</button> </td>
				<td> 아이디저장<input type="checkbox" name="idsave"> </td>
			</tr>
		</table>
	</form>
</body>
</html>