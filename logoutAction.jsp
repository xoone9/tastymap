<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>맛 있을 지도!</title>
</head>
<body>
	<% 
		session.invalidate(); //이페이지 접속 회원의 세션을 빼앗아 로그아웃
	%>
	<script>
		location.href = 'main.jsp';
	</script>
</body>
</html>


﻿