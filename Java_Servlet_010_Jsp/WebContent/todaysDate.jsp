

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@page import="java.util.Date" %>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>今天是几号</title>
</head>
<body>
	<%
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd号(E)");
		String today = format.format(date);
		out.println("Today is " + today);
	%>
	<p>
		&lt;%...%&gt;在JSP页面中称为代码块，它里面包含一段可执行的Java代码。
	</p>
	<p>
		&lt;%!--...--%&gt;在JSP页面中称为注释，备注不能嵌套，不会发送给客户端。它由Servlet/JSP容器处理。
	</p>
	<p>
		&lt;!--...--&gt;是html的注释，这个注释不由容器处理，而是被发送到客户端。
	</p>
	<p>
		&lt;@page&gt;Page指令，比较常用的有import属性，导入java包。contentType属性。设置JSP文档的MIME类型。pageEncoding属性设置JSP页面编码，还有很多属性后面讲解。
	</p>
</body>
</html>