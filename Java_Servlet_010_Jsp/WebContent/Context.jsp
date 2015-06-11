<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>隐式对象</title>
</head>
<body>
<%
	for(Enumeration<String> e = request.getHeaderNames();e.hasMoreElements();){
		
		String header = e.nextElement();
		out.println(header + ":" + request.getHeader(header) + "<br/>");
	}
%>
	<hr/>
<%
	out.println("Buffer size: " + response.getBufferSize() + "<br/>");
	out.println("Session id: " + session.getId() + "<br/>");
	out.println("Servlet name: " + config.getServletName() + "<br/>");
	out.println("Server Info: " + application.getServerInfo() + "<br/>");
%>
	<p>
		Servlet/JSP容器将几个对象传递给它所运行的Servlet。例如，在Servlet的service
		方法中获得HttpServletRequest和HttpServletResponse，并在init方法中获得
		ServletConfig，此外，还可以通过在HttpServletRequest对象中调用getSession
		获得一个HttpSession。
	</p>
	<p>
		在JSP中，可以通过使用<strong>隐式对象</strong>来距喔去上述这些对象。
	</p>
	<p>
		pageContext是指为JSP页面创建的javax.servlet.jsp.PageContext对象，
		它提供了有用的context信息，并通过一些名如其义的方法来访问与Servlet有关的各种对象，
		例如，getRequest，getResponse，getServletContext，getServletConfig及getSession。
		这些方法在脚本块&lt;%...%&gt;中作用不大，因为它们所返回的对象可以通过隐式对象
		request，response，session以及application更直接的访问到。
	</p>
	<table border="1">
		<tr>
			<th colspan="2">JSP隐式对象</th>
		</tr>
		<tr>
			<td style='text-align:center'>对象</td>
			<td style='text-align:center'>类型</td>
		</tr>
		<tr>
			<td style='text-align:center'>request</td>
			<td style='text-align:center'>javax.servlet.http.HttpServletRequest</td>
		</tr>
		<tr>
			<td style='text-align:center'>response</td>
			<td style='text-align:center'>javax.servlet.http.HttpServletResponse</td>
		</tr>
		<tr>
			<td style='text-align:center'>out</td>
			<td style='text-align:center'>javax.servlet.jsp.JspWriter</td>
		</tr>
		<tr>
			<td style='text-align:center'>session</td>
			<td style='text-align:center'>javax.servlet.http.HttpSession</td>
		</tr>
		<tr>
			<td style='text-align:center'>application</td>
			<td style='text-align:center'>javax.servlet.ServletContext</td>
		</tr>
		<tr>
			<td style='text-align:center'>config</td>
			<td style='text-align:center'>javax.servlet.ServletConfig</td>
		</tr>
		<tr>
			<td style='text-align:center'>pageContext</td>
			<td style='text-align:center'>javax.servlet.jsp.PageContext</td>
		</tr>
		<tr>
			<td style='text-align:center'>page</td>
			<td style='text-align:center'>javax.servlet.jsp.HttpJspPage</td>
		</tr>
		<tr>
			<td style='text-align:center'>exception</td>
			<td style='text-align:center'>javax.lang.Throwable</td>
		</tr>
	</table>
	<hr/>
	<ol>
		<li>
			PageContext提供的一些重要的方法就是存取属性，如getAttribute和setAttribute方法。属性
			可以保存在以下4种范围中:page，request，session及application。
			page范围最小，只能在同一个JSP页面中使用。
			PageContext中的setAttribute方法具有以下签名:
			public abstract void setAttribute(String name,Object value,int scopt)
			scopr取值为任意一个PageContext中的static final int值：PAGE_SCOPR,
			REQUEST_SCOPE,SESSION_SCOPE及APPLICATION_SCOPE。
			另外：public abstract void setAttribute(String name,object value)则直接
			把属性保存在page范围中。
		</li>
		<li>
			隐式对象out类似于在 HttpServletResponse中调用getWirter()之后得到的java.io.PrintWriter对象。
			作用都是将消息发送到浏览器。
		</li>
	</ol>
</body>
</html>