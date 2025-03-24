<%@page import="model.entity.StatusBean"%>
<%@page import="model.entity.UserBean"%>
<%@page import="model.entity.CategoryBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<CategoryBean> categoryList = (List)request.getAttribute("categoryList");
    List<StatusBean> statusList = (List)request.getAttribute("statusList");
	UserBean user = (UserBean)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<h2>タスク登録画面</h2>
	<form action="TaskRegister" method="post">
	タスク名<input type="text" name="taskName"><br>
	カテゴリ
	<select name="category">
		<%for(CategoryBean category : categoryList){ %>
			<option value=<%=category.getCategoryId() %>><%=category.getCategoryName() %></option>
		<%} %>
	</select><br>
	期限<input type="date" name="limitDate"><br>
	担当者 <%=user.getUserName() %><br>
	ステータス
	<!-- DAOいじれるようになったら追加 -->
	<select name="status">
		<%for(StatusBean status : statusList){ %>
			<option value=<%=status.getStatusCode() %>><%=status.getStatusName() %></option>
		<%} %>
		
	</select><br>
	メモ<br>
	<textarea name="memo"></textarea><br>
	<input type="submit" value="登録">
	</form>
</body>
</html>