<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
    function ajaxEvent() {

        var xmlHttp;
        try // Firefox, Opera 8.0+, Safari
        {
            xmlHttp = new XMLHttpRequest();
        } catch (e) {
            try // Internet Explorer
            {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {
                    alert("Your browser does not support AJAX!");
                    return false;
                }
            }
        }

        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4) {
               document.getElementById("genrediv").innerHTML = xmlHttp.responseText;
            }
        }
        
        var queryString = document.getElementById("queryString").value;

        xmlHttp.open("POST", "../ajaxservice.htm?course="+queryString, true);
        xmlHttp.send();
    }
</script>

</head>
<body>
<div class="container">
<br/><br/>
<p class="display-4">Hello ${sessionScope.loggeduser.firstName},</p>
<hr/>
<div style="float: right;">
<a href="${pageContext.request.contextPath}/user/logout.htm">Logout</a>
</div>
<div style="width:500px">
<p>What genre Books/articles do you want to read today?</p>
<form action ="${pageContext.request.contextPath}/user/readerdb.htm" method="post">
<div class="form-group">
<input type="text" id="queryString" size="30" name="genre" onkeyup="ajaxEvent()" class="form-control" required="required"/>
<div id="genrediv">
</div>
</div>
<div class="form-group">
<br/>
<input type="submit" value="SEARCH"/>
</div>
</form>
</div>
</div>
<p>Your Favourites List</p>
<table class="table table-hover">
<tr>
<td>ID</td>
<td><b>WRITER</b></td>
<td><b>TITLE</b></td>
<td><b>GENRE</b></td>
<td><b>ARTWORK</b></td>
<td>PDF</td>


</tr>
<c:forEach var="writer" items="${favs}">
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
<%-- <form action="${pageContext.request.contextPath}/user/delete.htm">
<p>Do you want to Remove AnyThing From Your Favourite List?</p>
<p>Enter the id From The Above Table-</p>
<input type="text" name="title"/>
<input type="submit" value="REMOVE"/>
</form>  --%>

<%-- Do you want to Remove AnyThing From Your Favourite List?
<p>Enter the id From The Above Table-</p><br/>
<input type="text" name="title"/>
<button  name="btn" onclick="${pageContext.request.contextPath}/user/delete.htm">Remove</button> --%>
</body>
</html>