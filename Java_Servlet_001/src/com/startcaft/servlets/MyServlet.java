package com.startcaft.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Serlvet是一个Java程序，一个Servelt应用程序包含一个或者多个Servlet，JSP页面会被翻译成Servelt，并进行编译。
 * Servelt应用程序是在Servlet容器中运行的，它不能自动运行。
 * Servelt容器将Web用户的请求传递给Servlet，并将Servlet程序的响应回传给用户。
 * 因此，将Java的Web应用程序称为"Servlet/JSP应用程序"比"Servlet应用程序"更为恰当一些。
 * 
 * ServletAPI有4个包:
 * 1,javax.servlet------包含定义Servlet与Servlet容器之间契约的类和容器
 * 2,javax.servlet.http------包含定义HTTP Servlet与Servlet容器之间契约的类和接口。
 * 3,javax.servlet.annotation------包含对Servlet，Filter和Listener进行标注的注解。
 * 4,javax.servelt.descriptor------包含为Web应用程序的配置信息提供编程时访问的类。
 * 
 * Servelt核心接口的定义:
 * 1,void init(ServletConfig config);
 * 2,void service(ServletRequest request,ServletResponse response);
 * 3,void destroy();
 * 4,String getServletInfo();
 * 5,ServletConfig getServletConfig();
 * 
 * Servlet容器根据以下原则调用init,service和destroy这三个生命周期方法
 * init---第一次请求Servlet时,Servlet容器就会调用这个方法，后续的请求中，将不再调用。
 * 		可以利用该方法进行一些应用程序的初始化工作。
 * service---每次请求Servlet时，容器都会调用这个方法。第一次请求Servlet时，容器会调用
 * 		init方法和service方法，后续的请求只会调用service方法。
 * destroy------要销毁Servlet时，容器就会调用这个方法。它通常发生在卸载应用程序，或者
 * 		关闭Servlet容器的时。一般来说，可以在这个方法里面编写一些清理资源的相关代码。
 * 
 * 两个非生命周期方法：
 * 1，getServletInfo()------返回Servlet的描述，可以是null。
 * 2，getServletConfig()------返回由容器传递给init方法的ServletConfig对象。
 * 
 * 注意：线程安全性，一个应用程序中的所有用户公用一个Servlet实例。
 * 
 * @author wow
 *
 */


//WebServlet注解声明一个Servlet，它告诉容器哪个URL调用这个Servlet。name属性是可选的,
//虽然urlPatterns属性也是可选的，但是几乎都会用到它。这里/my模式调用这个Servlet。
@WebServlet(name="MyServlet",urlPatterns={"/my"})
public class MyServlet implements Servlet {

	//transient关键字修饰的成员属性不会被序列化
	private transient ServletConfig servletConfig;
	
	@Override
	public void destroy() {
	}

	@Override
	public ServletConfig getServletConfig() {
		return this.servletConfig;
	}

	@Override
	public String getServletInfo() {
		return "My Servlet";
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		this.servletConfig = arg0;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		
		String servletNameString = servletConfig.getServletName();
		arg1.setContentType("text/html");
		PrintWriter writer = arg1.getWriter();
		writer.print("<html><head></head>"
				+ "<body>Hello from " + servletNameString
				+ "</body></html>");
	}
	
}
