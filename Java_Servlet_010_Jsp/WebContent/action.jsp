<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Action动作</title>
</head>
<body>
	第三种句法元素是动作(Action),它们被编译成执行某个操作的Java代码，例如访问某个Java对象，
	或者调用某个方法。除标准动作之外，还可以创建定制的标签，用来执行某些操作。
	<p>
		<strong>useBean</strong>
		这个动作创建一个与某个Java对象相关的脚本变量。它是将表现逻辑与业务逻辑分割开来的最容易的方法之一。
		但是有了像定制标签和Expression Language这类技术之后，现在已经很少使用useBean了。
	</p>
	<jsp:useBean id="totay" class="java.util.Date"></jsp:useBean>
	<%=totay %>
	<hr/>
	<p>
		<strong>setProperty和getPrperty</strong>
		setProperty动作像是在一个Java对象中保存一个数据，getProperty则是获取一个Java对象的属性。
	</p>
	<jsp:useBean id="emploee" class="com.startcaft.jsp.model.Employee"></jsp:useBean>
	<jsp:setProperty property="firstName" name="emploee" value="startcaft"/>
	First Name:<jsp:getProperty property="firstName" name="emploee"/>
	<hr/>
	<p>
		<strong>include</strong>
		include动作用于动态地包含另一个资源，它可以包含另一个JSP页面，一个Servlet或者一个静态的HTML页面。
		理解include指令和include动作之间的区别是很重要的。使用include指令时，这种包含是发生在【页面转换的时候】。
		使用include动作，这种包含则是发生在【请求的时候】。因此可以利用include动作传递参数，而不是利用include指令。
		第二个区别在于，使用include指令时，被包含资源的文件扩展名并不重要。而使用include动作时，文件扩展名必须为jsp，
		一边它能够作为一个jsp页面进行处理。
	</p>
	<hr/>
	<p>
		<strong>forward</strong>
		forward动作是将当前页面跳转到另一个不同的资源。
		&lt;jsp:forward page="jspf/login.jsp"&gt;
			&lt;jsp:param name="text" value="please login"&gt;
		&lt;/jsp:forward page="jspf/login.jsp"&gt;
	</p>
	<hr/>
	<p>
		<strong>错误处理</strong>
		在JSP中错误处理支持的很好，可以利用try-catch语句处理Java代码，也可以指定一个页面，
		让它在应用程序遇到未补货的异常时显示出来。那么一旦发生异常，用户将会看到一张精心设计过的页面，
		解释目前发生了什么状况，而不是用一条错误消息打发用户。
		利用page指令的isErrorPage属性，就可以把一个JSP页面编程一个错误处理页面。该属性值必须为true。
		然后其他页面必须使用page指令的errorPage属性，将路径引向我们定义好的错误处理页面。
	</p>
</body>
</html>