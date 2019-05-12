<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype HTML>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
<br/>
<div class="display-4 ">Hello ${sessionScope.loggeduser.firstName},</div>
<div style="float: right;">
<a href="${pageContext.request.contextPath}/user/logout.htm">Logout</a>
</div>
<form:form commandName="writer" enctype="multipart/form-data" action ="${pageContext.request.contextPath}/user/writerdb.htm" method="post">
<hr/>
<p style="font-size: 30px;">What do you want to do ?</p>
<div class="row">
<div class="col-md-6">
<div style="width:500px; height:400px; border: 1px solid black">
<div style="padding:15px;">
<p>Upload A new Piece of Work?</p><br/>
<div class="form-group">
Title:<form:input type="text" path="title" class="form-control" required="required"/>
</div>
<div class="form-group">
genre:<form:input type="text" path="genre" class="form-control" required="required"/>
</div>
<div class="form-group">
Article:<br/><form:input type="file" path="pdf" required="required"/><br/><br/>
</div>
<input type="submit" value="SUBMIT"/>
</div>
</div>
</div>
</form:form>

<div class="col-md-6" style="width:500px; height:400px; border: 1px solid black">
<form:form commandName="article" enctype="multipart/form-data" action ="${pageContext.request.contextPath}/user/writerupdate.htm" method="post">
<br/><p>Add to Your old work</p>
Enter the Id of the Artwork where you want to upload a new Document:
<div class="form-group">
<input type="number" name="wid" class="form-control" required="required"/>
</div>
Now upload the document:
<br/><br/>
<div class="form-group">
Article:<br/><form:input type="file" path="pdf" required="required"/><br/><br/>
<input type="submit" value="ADD"/>
</div>
</form:form>
</div>
</div>
</div>
<br/><br/>
<p class="display-4">----------------Your Previous Works-----------------</p>
<div class="container">
<table class="table table-hover">
<td><b>ID</b></td>
<td><b>WRITER</b></td>
<td><b>TITLE</b></td>
<td><b>GENRES</b></td>
<td><b>ARTWORK</b></td>
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
</div>

</body>
</html>