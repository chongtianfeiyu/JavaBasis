package com.startcaft.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//注意注解的urlPatterns
@WebFilter(filterName="ImageFilter",urlPatterns={
		"*.jpg","*.png","*.gif"
})
public class ImageFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ImageFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("ImageFilter");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		//查看referrer标头
		String referrer = httpServletRequest.getHeader("referer");
		System.out.println("referrer:" + referrer);
		if (referrer != null) {
			chain.doFilter(request, response);
		}
		else {
			throw new ServletException("Image not available");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
