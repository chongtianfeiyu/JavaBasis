<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	<strong>JSP概述</strong>
	<p>
		JSP页面起始就是一个Servlet。但是，使用JSP页面则比使用Servlet要容易得多，这有两个原因：
		<ul>
			<li>第一，不需要编译JSP页面</li>
			<li>第二，JSP页面一般是扩展名为jsp的文本文件，可以利用任何文本编辑器来编写</li>
		</ul>
	</p>
	<p>
		JSP页面是在JSP容器中运行的。Servlet容器一般也是JSP容器，例如，Tomcat就是一个Servlet/JSP容器。
		第一次请求一个JSP页面时，容器需要做两件事情：
		<ul>
			<li>
				1,将JSP页面转换成一个JSP页面实现类，这是一个实现javax.servlet.jsp.JspPage接口或者
				其子接口javax.servlet.jsp.HttpjspPage的Java类。
				JspPage是javax.servlet.Servlet的子接口，这样才能保证每一个JSP页面都是一个Servlet。
				所生成的Servlet的类名取决于容器。如果转换过程中出错，错误消息会输出到客户端。
			</li>
			<li>
				2，如果转换成功，Servlet/JSP容器将会编译这个Servlet类，之后，容器加载和实例化Java字节码，
				并执行它通常对Servlet所做的生命周期操作。
			</li>
		</ul>
	</p>
	<p>
		对于同一个JSP页面的后续请求，Servlet/JSP容器会查看这个JSP页面自从最后一次转换依赖是否修改过，
		如果修改过，就会重新转换，重新编译，并执行。如果没有，则执行内存中已经存在的JSP Servlet。
		所以，第一次调用JSP页面的事件总是会比后续的请求更长，因为它需要转换和编译。为了解决这个问题，
		可以采用以下任意一种措施:
		<ul>
			<li>
				配置应用程序，以便在应用程序启动之时，调用所有的JSP页面(实际上是指转换和编译)，
				而不是在初始请求时才调用。
			</li>
			<li>
				预先编译JSP页面，并将它们以Servlet的方式进行部署。
				
			</li>
		</ul>
	</p>
	<p>
		JSP中有一个API，其中包含4个包：
		<li>
			javax.servlet.jsp------包含核心类和接口，Servlet/JSP容器用他们将JSP页面转换成
			Servlet。JspPage和HttpJspPage接口是这两个包的重要成员。所有JSP页面实现类都必须
			实现JspPage或HttpJspPage。在HTTP环境下，显然是选择HttpJspPage。
		</li>
		<li>
			javax.servlet.jsp.tagext------包含用于开发定制标签的类型
		</li>
		<li>
			javax.el------为Unified Expression Language提供API。
		</li>
		<li>
			javax.servlet.jsp.el------提供Servlet/JSP容器必须支持的类，以便支持JSP的Expression Language。
		</li>
	</p>
	<p>
		<strong>除了javax.servlet.jsp.tagext之外，很少需要直接用到JSP API。</strong>事实上，在编写JSP页面时，
		相对于JSP API本身，我们会更关注Servlet API。当然，我们还是需要掌握JSP的语法。
	</p>
</body>
</html>