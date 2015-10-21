<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello,SpringMVC</title>
</head>
<body>

	<a href="hello/helloworld">Hello World</a>
	<hr>
	<a href="springmvc/testRequestMapping">Test RequestMapping</a>
	<hr>
	<a href="springmvc/testMethod">Test Method，无法请求</a>
	<br>
	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="Test Method，可以请求">
	</form>
	<hr>
	<a href="springmvc/testParamsAndHeaders?username=startcaft&age=11">要求必须包含username参数，并且age参数不等于10</a>
	<hr>
	<a href="springmvc/testAntPath/startcaft/abc">Test AntPath</a>
	<hr>
	<a href="springmvc/testPathVariable/1">Test PathVariable</a>
	<hr>
	<h4>测试REST</h4>
	<a href="springmvc/testRest/1">Test Rest GET</a>
	<br>
	<br>
	<form action="springmvc/testRest" method="post">
		<input type="submit" value="Test Rest POST"/>
	</form>
	<br>
	<br>
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="Test Rest DELETE"/>
	</form>
	<br>
	<br>
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="Test Rest PUT"/>
	</form>
	
</body>
</html>