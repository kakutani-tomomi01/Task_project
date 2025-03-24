<%@page import="model.entity.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>
	<%
		UserBean user = (UserBean)session.getAttribute("user");
	%>
	ようこそ、<%=user.getUserId() %> さん<br>
	<h1>メニュー画面</h1>
	<form action="task" method="POST">
		<input type="hidden" name="role" value="taskList">
		<input type="submit" value="タスク一覧表示">
	</form>	
	<!-- 登録用form -->
	<form action="TaskRegister" method="get">
		<input type="submit" value="登録画面へ">
	</form>
</body>
</html>