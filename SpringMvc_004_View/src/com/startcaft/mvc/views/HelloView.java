package com.startcaft.mvc.views;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class HelloView implements View {

	/**返回内容类型**/
	@Override
	public String getContentType() {
		
		return "text/html";
	}

	/**渲染视图**/
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.getWriter().print("hello view,time ： " +  new Date());
	}

}
