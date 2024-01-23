<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String x = (String) request.getAttribute("errorMsg");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>에러 페이지</h1>
	
	<h2><%=x %></h2>

</body>
</html>