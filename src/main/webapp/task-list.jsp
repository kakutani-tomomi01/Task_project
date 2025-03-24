<%@page import="model.entity.UserBean"%>
<%@page import="model.entity.TaskBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧表示</title>
</head>
<body>
<%
	// ユーザ名の取得
	UserBean user = (UserBean)session.getAttribute("user");
	// taskListの取得
	List<TaskBean> taskList = (List)request.getAttribute("taskList");
%>
<%=user.getUserId() %> さん<br>
<h1>タスク一覧表示</h1>
<table>
	<tr>
		<th>タスク名</th>
		<th>カテゴリ情報</th>
		<th>期限</th>
		<th>担当者情報</th>
		<th>ステータス情報</th>
		<th>メモ</th>
	</tr>
	<%
		for (TaskBean task : taskList) {
	%>
			<tr>
				<td><%=task.getTaskName() %></td>
				<td><%=task.getCategoryName() %></td>
				<td><%=task.getLimitDate() %></td>
				<td><%=task.getUserName() %></td>
				<td><%=task.getStatusName() %></td>
				<td><%=task.getMemo() %></td>
				<td>
					<form action="TaskFix" method="POST">
						<input type="hidden" name="<%=task.getTaskId() %>">
						<input type="submit" value="編集">
					</form>
				</td>
				<td>
					<form action="TaskDelete" method="POST">
						<input type="hidden" name="<%=task.getTaskId() %>">
						<input type="submit" value="削除">
					</form>
				</td>
			</tr>
	<%
		}
	%>
	<th>
</table>
<form action="menu" method="GET">
	<input type="submit" value="メニュー画面に戻る">
</form>


</body>
</html>