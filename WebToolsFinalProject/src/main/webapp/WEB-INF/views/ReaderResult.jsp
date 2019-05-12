<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
</head>
<body>
<div class="container" style="padding:20px;">
<p class="display-4">${sessionScope.loggeduser.firstName}, Your Search result!</p>
<table class="table table-striped">
<td>ID</td>
<td>NAME</td>
<td>TITLE</td>
<td>GENRE</td>
<td>ARTWORK</td>
<td>LINK FOR THE ARTWORK</td>
<c:forEach var="writer" items="${work}">
<tr>
<td>${writer.wid}</td>
<td>${writer}</td>
<td>${writer.title}</td>
<td>${writer.genre}</td>
<c:forEach var="art" items="${writer.article}">
<td><a href="${pageContext.request.contextPath}/report.htm?article=${art.article}">${art.article}</a></td>

</c:forEach>
</tr>
</c:forEach>
</table>
<form action ="${pageContext.request.contextPath}/user/readerfav.htm" method="post">
Enter The id of the Article you want to add to favourites-
<input type="number" name="fave"/>
<input type="submit" value="ADD TO CART"/>
</form>
</div>
</body>
</html>