<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<c:forEach var = "user" items="${userlist}">

<tr>
<td>${user.userid}</td>
<td>${user.firstName}</td>
<td>${user.lastName}</td>
<td>${user.userName}</td>
</tr>
<br>

</c:forEach>

</table>

<a href="${pageContext.request.contextPath}/report.htm/?typereport=pdf">PDFDownLoad</a>
</body>
</html>