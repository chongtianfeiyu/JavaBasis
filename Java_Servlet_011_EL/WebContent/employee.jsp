<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee</title>
</head>
<body>
	accept-language: ${header['accept-language'] }
	<br/>
	session id: ${pageContext.session.id }
	<br/>
	employee: ${requestScope.employee.name }, ${employee.address.city }
	<br/>
	capital: ${capitals["Canada"] }
</body>
</html>