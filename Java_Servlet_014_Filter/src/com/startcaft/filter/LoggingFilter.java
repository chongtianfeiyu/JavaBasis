package com.startcaft.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;


/**
 * Filter API
 * 包含Filter，FilterConfig和FilterChain。
 * 过滤器类必须实现javax.servlet.Filter接口，该接口暴露三个生命周期方法：init，doFilter和destroy。
 * 
 * 1，void init(FilterConfig filterConfig)
 * 该方法只被调用一次，应用程序启动时被调用，Servlet容器给init传递一个FilterConfig对象。
 * 所以init应该包含Filter初始化的代码。
 * 
 * 2，FilterConfig接口---每次过滤器时，Servlet容器都会调用Filter实例的doFilter方法。
 * 该方法会接受一个ServletRequest，ServletResponse和FilterChain(过滤链)。
 * doFilter方法的签名如下：
 * void doFilter(ServletRequest request,ServletResponse response,FilterChain filterChain)。
 * doFilter可以访问ServletRequest和ServletResponse。
 * 
 * doFilter方法实现的最后一行代码应该是调用FilterChain.doFilter(request,response)方法。
 * 
 * 3,Filter中的最后一个生命周期方法是destroy，其方法签名如下：
 * void destory()
 * 这个方法在过滤器即将终止服务之前，由Servlet容器调用，一般发生在应用程序停止的时候。
 * 
 * 
 * 除非一个过滤器类在web.xml的多个filter元素中进行了声明，否则Servlet容器将只给每一类过滤器创建一个实例。
 * 由于Servlet/JSP应用程序通常是多用户的应用，因此可以同时通过多个线程访问一个过滤器实例。
 * 
 * @author wow
 *
 */

@WebFilter(filterName="LoggingFilter",urlPatterns={"/*"},
	initParams={
		@WebInitParam(name="logFileName",value="log.txt"),
		@WebInitParam(name="prefix",value="URI:")
	}
)
public class LoggingFilter implements Filter {
	
	private PrintWriter logger;
	private String prefix;
	

    public LoggingFilter() {
    }
    
    
	public void destroy() {
		//清理资源
		System.out.println("destroying filter");
		if (logger != null) {
			logger.close();
		}
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("LoggingFilter.doFilter");
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		
		logger.println(new Date() + "" + prefix
				    + httpServletRequest.getRequestURI());
		logger.flush();
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		//使用FilterConfig对象从初始参数中获取值。
		prefix = fConfig.getInitParameter("prefix");
		String logFileNameString = fConfig.getInitParameter("logFileName");
		//使用FilterConfig对象获取ServletContext对象，并获取应用程序的根目录。
		String appPath = fConfig.getServletContext().getRealPath("/");
		System.out.println("logFileName:" + logFileNameString);
		
		//创建日志文件new File
		try {
			logger = new PrintWriter(new File(appPath,logFileNameString));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

}
