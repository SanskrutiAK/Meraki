<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
	<style>
	#maindiv{
	  position: absolute;
	  margin: auto;
	  top: 0;
	  right: 0;
	  bottom: 0;
	  left: 0;
	  width: 500px;
	  height: 600px;
	  background-color:wheat;
	  border-radius: 3px;
	  background-image: url("https://i.pinimg.com/564x/66/87/10/668710af715ee34130943e1e72f96fda.jpg");
	}
	#here{
	background-image: url("https://i.pinimg.com/564x/66/87/10/668710af715ee34130943e1e72f96fda.jpg");
	}
	img.avatar {
  width: 40%;
  border-radius: 50%;
}
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}
	</style>
	
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div id="maindiv">
<div class="imgcontainer">
      <img src="https://cdn1.iconfinder.com/data/icons/avatars-1-5/136/87-512.png" alt="Avatar" class="avatar">
</div>
<form action="${pageContext.request.contextPath}/user/login.htm" method="POST">

<div style="width:500px; height:500px;"/>
<div class="container" style="width:400px; height:400px;">
<div class="form-group">
<label><b>UserName<b></label>
<input type="text" name="userName" class="form-control" required="required"/><br/><br/>
</div>
<div class="form-group">
<label><b>Password<b></label>
<input type="password" name="password" class="form-control" required="required"/><br/><br/>
</div>
<div class="form-group">
<input type="submit" value="SUBMIT"/>
<a href="${pageContext.request.contextPath}/user/create.htm">SignUp</a>
</div>
</div>
</div>
</div>
</form>
</div>
</body>
</html>
