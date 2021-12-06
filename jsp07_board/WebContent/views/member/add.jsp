<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<%@ include file="../include/msg.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<script type="text/javascript">
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}

	function jusoCallBack(roadAddrPart1,addrDetail, zipNo){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			document.frmAdd.addr.value = roadAddrPart1;
			document.frmAdd.addrdetail.value = addrDetail;
			document.frmAdd.zipcode.value = zipNo;
	}		
	function addCheck() {
		var email = frmAdd.email.value;
		var userpw = frmAdd.userpw.value;
		if(email==''){
			alert('이메일을 입력하세요.')
			frmAdd.email.focus();
		}else if(userpw==''){
			alert('비밀번호를 입력하세요.')
			frmAdd.email.userpw();			
		}else{
			frmAdd.submit();
		}
	}
</script>
</head>

<body>
	<%@ include file="../header.jsp" %>
	<h2>회원등록</h2>
	<!-- enctype="multipart/form-data": 파일을 전송 -->
	<form action="${path}/member/add" name="frmAdd" enctype="multipart/form-data" method="post">
		<table border="1">
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="userpw"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type="text" name="zipcode" size="5">
					<button type="button" onclick="goPopup()">찾기</button>
					<hr>
					<input type="text" name="addr" size="30"> <br>
					<input type="text" name="addrdetail" size="30"> 
				</td>
			</tr>
			<tr>
				<th>사진</th>
				<td><input type="file" name="fileName"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onclick="addCheck()">등록</button>
					<button type="reset">취소</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>