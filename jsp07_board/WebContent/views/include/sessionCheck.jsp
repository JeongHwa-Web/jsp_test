<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includeFile.jsp" %>
<script type="text/javascript">
	if('${sessionScope.email}'==''){
		alert('로그인을 하십시오.');
		location.href='${path}/views/member/login.jsp';
	}
</script>