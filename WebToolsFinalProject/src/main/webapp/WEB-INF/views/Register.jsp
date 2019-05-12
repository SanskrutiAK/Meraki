<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<style>
img.avatar {
  width: 40%;
  border-radius: 50%;
}
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

.bgim{

background-image: url("https://i.pinimg.com/564x/66/87/10/668710af715ee34130943e1e72f96fda.jpg");

}

.signupbtn{
width:150px;
height:50px;
background-color: #FFA391;
font-weight: bold;
}
</style>



</head>
<body>
<div class="container bgim" style="width:500px; height:850px;">
<div class="imgcontainer">
      <img src="https://cdn1.iconfinder.com/data/icons/avatars-1-5/136/87-512.png" alt="Avatar" class="avatar">
</div>

<form:form commandName="user" action ="${pageContext.request.contextPath}/user/create.htm" method="POST">
<div class="form-group">
<label><b>FirstName:</b></label>
<!-- <input type="text" name="firstName" class="form-control" required="required"/> -->
<form:input  type="text" path="firstName" class="form-control" required="required"/>

</div>

<div class="form-group">
<label><b>LastName:</b></label>
<form:input path="lastName" class="form-control" required="required"/>
</div>

<div class="form-group">
<label><b>UserName:</b></label>
<form:input path="userName" class="form-control" required="required"/>
<b style="color:red"><form:errors path="userName"/></b>
</div>

<div class="form-group">
<label><b>Password:</b></label>
<form:input type="password" path="password" class="form-control" required="required"/>
<b style="color:red"><form:errors path="password"/></b>
</div>

<div class="form-group">
<b>SELECT A ROLE</b><br/><br/>
<input type="radio" value="Reader" name="role" required="required"> <b>Reader</b><br/><br/>
<input type="radio" value="Writer" name="role" required="required"> <b>Writer</b>
</div>

<div class="form-group">
<input type="Submit" value="SIGN UP" class="signupbtn"/>
</div>
</form:form>
</div>

</body>
</html>