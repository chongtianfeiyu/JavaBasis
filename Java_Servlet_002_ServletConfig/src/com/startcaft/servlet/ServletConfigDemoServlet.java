package com.startcaft.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;


/**
 * 对于每一个HTTP请求，容器都会创建一个ServletRequest实例，并将它传递给Servlet的service方法。
 * ServletRequest封装了有关HTTP请求的信息.
 * ServletRequest接口中几个比较常用的方法：
 * 1，public int getContentLength()------返回请求主体的字节数，如果不知道长度，则返回-1。
 * 2，public String getContentType()------返回请求主体的MIME类型，如果不知道类型，返回null。
 * 3，public String getParameter(String name)返回指定请求参数的值。
 * 4，public String getProtocol()------返回HTTP请求的协议名称和版本号。
 * 
 * getParameter是最常用的方法，通常用来返回一个HTML表单域的值，也可以用来获取查询字符串的值，
 * 如果指定的参数值不存在，则返回null。
 * 
 * 
 * javax.servlet.ServletResponse接口表示一个Servlet相应，在调用一个Servlet的service方法之前，
 * 容器会创建一个ServletResponse对象，并传递给service方法。ServletResponse隐藏了将响应
 * 发送给浏览器的复杂性。
 * 1，ServletResponse中定义的其中一个方法getWriter()，它返回可以将文本传递给客户端的java.io.PrintWrtier对象，
 * 在默认情况下，PrintWriter对象采用ISO-8859-1编码格式。
 * 2，还有一个方法可以用来将文本输出给浏览器：getOutputStream。但是这个方法是用来传输二进制数据的，
 * 大多数我们都是用getWriter。
 * 
 * 
 * 在Servlet容器初始化Servlet时，Servlet容器将ServletConfig传递给Servlet的init方法。
 * ServletConfig封装可以通过@WebServlet或者部署描述符传给一个Servlet的配置信息。
 * 为了从一个Servlet内部获取某个初始参数的值，应该有Servlet容器传递给Servlet的init方法，
 * Servlet调用getInitParameter方法获取。
 * 1，String getInitParameter(String name)---获取指定名称的参数
 * 2，Enumeration<String> getInitParameterNames()---获取所有参数
 * 3，ServletContext getServletContext()---获取ServletContext对象。
 * 
 * @author wow
 *
 */

//这里使用@WebServlet的initParams属性初始化了两个参数。
@WebServlet(name="ServletConfigDemoServlet",urlPatterns={"/myConfig"},
initParams={
		@WebInitParam(name="admin",value="startcaft"),
		@WebInitParam(name="email",value="startcaft@example.com")
})
public class ServletConfigDemoServlet implements Servlet {

	private transient ServletConfig config;
	
	@Override
	public void destroy() {
	}

	@Override
	public ServletConfig getServletConfig() {
		return this.config;
	}

	@Override
	public String getServletInfo() {
		return "ServletConfig demo";
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		this.config = arg0;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String admin = config.getInitParameter("admin");
		String email = config.getInitParameter("email");
		arg1.setContentType("text/html");
		PrintWriter writer = arg1.getWriter();
		writer.print("<html><head></head><body>" + 
					"Admin:" + admin + 
					"<br/>Email:" + email + 
					"</body></html>");
	}

}
