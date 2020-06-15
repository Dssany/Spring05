<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" align="center">
	<tr>
		<th>번호</th><th>이름</th>
	</tr>
	<c:forEach items="${list }" var="dto">
		<tr>
			<th>${dto.id }</th><th>${dto.name }</th>
		</tr>
	</c:forEach>
	<tr>
		<th colspan="2">
			<a href="save_view">등록</a>
		</th>
	</tr>

</table>
</body>
</html>