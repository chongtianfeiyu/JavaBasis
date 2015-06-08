package com.startcaft.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 001和002的例子都是通过实现自定义类实现Servlet接口来编写Servlet程序，
 * 这样做很麻烦，因为要手动实现Servlet的5个接口。而且还要一个成员变量来保存ServletConfig对象。
 * 
 * 值得庆幸的是，我们有GenericServlet抽象类，它实现了Servlet和ServletConfig，并完成以下工作：
 * 1，将init方法中的ServletConfig赋给一个类级变量，可以通过getServletConfig来获取。
 * 2，为Servlet接口中的所有方法提供了默认实现
 * 3，提供方法来包装ServletConfig中的方法。
 * 
 * 这是GenericServlet中的init初始化实现。
 * public void init(ServletConfig config){
 * 		this.servleConfig = config;
 * 		this.init();
 * }
 * 
 * 
 * 通过继承自GenericServlet抽象类，我们只需要复写service(request,response)方即可。
 * 
 * @author wow
 *
 */

@WebServlet(name="GenericServeltDemoServlet",urlPatterns={"/generic"},
initParams={
		@WebInitParam(name="admin",value="startcaft"),
		@WebInitParam(name="email",value="startcaft@example.com")
})
public class GenericServletDemoServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		ServletConfig config = getServletConfig();
		String adminString = config.getInitParameter("admin");
		String email = config.getInitParameter("email");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head></head><body>" + 
					"Admin:" + adminString + 
					"<br/>Email:" + email + 
					"</body></html>");
	}

}
