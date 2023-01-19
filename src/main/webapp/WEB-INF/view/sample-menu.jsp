<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Account" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Account ac = (Account)session.getAttribute("root");
	%>
	<h3>商品管理システム</h3>
	<p>ようこそ<%=ac.getName() %>さん</p>
	<a href="RegisterProductServlet">新規商品登録</a><br>
	<a href="ListProductServlet">商品一覧</a><br>
	<a href="SerchServlet">商品検索</a><br>
	<a href="DeleteProductServlet">商品削除</a><br>
	<a href="UpdateProductServlet">商品更新</a><br>
	<a href="LogoutServlet">ログアウト</a>
</body>
</html>