<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		String error = request.getParameter("error");
		if(error != null){
			
	%>
		<p style="color:red">登録に失敗しました。</p>
		<h3>商品登録</h3>
		<form action="RegisterProductExecuteServlet" method="post">
			商品名：<input type="text" name="product_name" value="<%=request.getParameter("product_name") %>"><br>
			登録日：<input type="date" name="product_date"  value="<%=request.getParameter("product_date") %>"><br>
			商品説明：<br>
			<textarea name="note" rows="10" cols="50" placeholder="内容を入力"><%=request.getParameter("note") %></textarea><br>
			<input type="submit" value="登録">
		
		</form>
	<%	
		} else {
	%>
	<h3>商品登録</h3>
	<form action="RegisterProductExecuteServlet" method="post">
		商品名：<input type="text" name="company"><br>
		登録日：<input type="date" name="date"><br>
	    商品説明：<br>
		<textarea name="note" rows="10" cols="50" placeholder="内容を入力"></textarea><br>
		<input type="submit" value="登録">
	
	</form>
	<% } %>
	<a href="TopServlet">戻る</a>
	
</body>
</html>