<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello,SpringMVC</title>
</head>
<body>

	<h4>SpringMVC模型数据</h4>
	<a href="model/testModelAndView">testModelAndView</a>
	<hr>
	<a href="model/testMap">testMap</a>
	<hr>
	<a href="model/testSessionAttributes">testSessionAttributes</a>
	<hr>
	<h4>@ModelAttribute模拟修改操作,要求密码不能修改</h4>
	<form action="model/testModelAttributes" method="post">
		<input type="hidden" name="id" value="1"/>
		<br>
		username:<input type="text" name="username" value="Tom"/><br>
		email:<input type="text" name="email" value="tom@163.com"/><br>
		age:<input type="text" name="age" value="22"/><br>
		<input type="submit" value="Submit" />
	</form>
</body>
</html>