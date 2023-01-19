<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>ここは検索用の画面です。</h3>
	<form action="SerchProductServlet" method="post">
	<h3>商品名:<input type="text" name="product_name"></h3><br>
	<input type="submit" value="検索" class="botton">
	<a href="TopServlet">戻る</a>
	</form>
</body>
</html>