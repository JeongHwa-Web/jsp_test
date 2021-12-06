<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<%@ include file="../include/msg.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("${path}/views/member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}

	function jusoCallBack(roadAddrPart1,addrDetail, zipNo){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			document.frmModi.addr.value = roadAddrPart1;
			document.frmModi.addrdetail.value = addrDetail;
			document.frmModi.zipcode.value = zipNo;
	}		
	function modifyCheck() {
		var email = frmModi.email.value;
		var userpw = frmModi.userpw.value;
		if(email==''){
			alert('이메일을 입력하세요.')
			frmModi.email.focus();
		}else if(userpw==''){
			alert('비밀번호를 입력하세요.')
			frmModi.email.userpw();			
		}else{
			frmModi.submit();
		}
	}
</script>
<body>
	<%@ include file="../header.jsp" %>
	<h2>내정보</h2>
	<form action="${path}/member/modify" name="frmModi" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email" value="${member.email}" readonly="readonly"></td>
			</tr>
			<tr>
				<th>현재비밀번호</th>
				<td><input type="password" name="userpw"></td>
			</tr>
			<tr>
				<th>변경비밀번호</th>
				<td><input type="password" name="newpw"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type="text" name="zipcode" size="5" value="${member.zipcode}">
					<button type="button" onclick="goPopup()">찾기</button>
					<hr>
					<input type="text" name="addr" size="30" value="${member.addr}"> <br>
					<input type="text" name="addrdetail" size="30" value="${member.addrdetail}"> 
				</td>
			</tr>
			<tr>
				<th>사진</th>
				<td>
					<img alt="" src="/saveImg/${member.fileName}" width="100px">
					사진 삭제<input type="checkbox" name="fileDelete"> <br>
					<a href="${path}/file/download?fileName=${member.fileName}">다운로드</a>
					<input type="text" name="fileName" value="${member.fileName}" readonly="readonly"> <br>
					<hr>
					<input type="file" name="file">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onclick="modifyCheck()">등록</button>
					<button type="reset">취소</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>