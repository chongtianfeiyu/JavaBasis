<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello,SpringMVC</title>
</head>
<body>

	<h4>请求参数的绑定</h4>
	<a href="hello/testRequestParam?username=startcaft&age=33">testRequestParam</a>
	<hr>
	<a href="hello/testRequestHeader">testRequestHeader</a>
	<hr>
	<a href="hello/testCookieValue">testCookieValue</a>
	<hr>
	<h4>POJO参数绑定</h4>
	<form action="hello/testPojo" method="post">
		username:<input type="text" name="username"/>
		<br>
		password:<input type="password" name="password" />
		<br>
		email:<input type="text" name="email"/>
		<br>
		age:<input type="text" name="age"/>
		<br>
		city:<input type="text" name="address.city"/>
		<br>
		province:<input type="text" name="address.province"/>
		<br>
		<input type="submit" value="Submit"/>
	</form>
	<hr>
	<a href="hello/testServletApi">testServletApi</a>
</body>
</html>