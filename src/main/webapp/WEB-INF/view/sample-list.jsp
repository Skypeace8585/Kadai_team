<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.ProductExam" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>商品一覧</h3>
	
	<table border="1">
		<tr>
			<th>品番</th>
			<th>商品名</th>
			<th>登録日</th>
			<th>商品説明</th>
		</tr>
		
	<%
			List<ProductExam> list = (List<ProductExam>)request.getAttribute("list");
	                 int index = 1;
					for(ProductExam ee : list){
			%>
		<tr>
			<td><%=index %></td>
			<td><%=ee.getProductName() %></td>
			<td><%=ee.getProductDate()%></td>
			<td><%=ee.getNote() %></td>
		</tr>
	<%
	index++;
					}
	%>
	</table>
	<a href="TopServlet">戻る</a>
</body>
</html>