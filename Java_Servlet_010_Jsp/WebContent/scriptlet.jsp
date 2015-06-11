<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>脚本元素</title>
</head>
<body>
	<p>
		第二种JSP句法元素是脚本元素，它将Java代码合并成JSP页面的一部分。
		脚本元素有三种类型：Scriptlet，声明以及表达式，都很简单。
	</p>
	<hr/>
	<%
		for(Enumeration<String> e = request.getHeaderNames();e.hasMoreElements();){
			String header = e.nextElement();
			out.println(header + ": " + request.getHeader(header) + "</br>");
		}
		String message = "Thank You!";
	%>
	<hr/>
	<%--上面的代码块中定义的message对于后面的代码块是可见的。 --%>
	<%
		out.println(message + "<br/>");
	%>
	<hr/>
	<p>
		表达式(Expression)的运算结果会被填入隐式对象put的print方法中。
		表达式的语法：&lt;%= %&gt;，注意表达式的后面不需要分号。
	</p>
	Today is <%=Calendar.getInstance().getTime() %>
	<hr/>
	<p>
		在JSP页面中可以声明能够在页面中使用的变量和方法。声明要用&lt;%!和%&gt;包起来。
	</p>
	<%!
		public String getTodaysDate(){
			
			Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return format.format(new Date());
		}
	%>
	Today is <%=getTodaysDate() %>
	<hr/>
	<hr/>
	<p>
		<strong>
			随着JSP2.0中Expression Language的发展，开发中建议使用EL来访问服务器端的对象，
			而不是在JSP页面中编写Java代码。
		</strong>
		为此，原本开启的JSP2.0脚本元素，可以在web.xml文件的&lt;jsp-property-group&gt;中定义一个scripting-invalida元素，将其关闭。
	</p>
	&lt;jsp-property-group&gt;
		&lt;url_pattern&gt;*.jsp&lt;/jsp-property-group&gt;
		&lt;scripting-invalid&gt;true&lt;/scripting-invalid&gt;
	&lt;/jsp-property-group&gt;
</body>
</html>