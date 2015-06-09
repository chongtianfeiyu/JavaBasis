package com.startcaft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 虽然GenericServlet是Servlet的增强版本，但是它并不常用，因为它不如HtpServlet高级，
 * 在日常开发中，真正使用的还是HttpServlet。
 * 
 * 我们所编写的Servlet应用程序，其中大多数都要使用HTTP协议。这意味着，可以利用HTTP提供的特性。
 * javax.servlet.http包是ServletAPI中的第二个包，其中包含了编写Servlet应用程序的类和接口。
 * javax.servlet.http中的许多类都覆盖了javax.servlet中的类。
 * 
 * 1，HttpServlet类覆盖了javax.servlet.GenericServlet类:
 * 		HttpServlet覆盖了javax.servlet.GenericServlet中的service方法，并用以下签名
 * 		添加了另一个service方法：
 * proteced void service(HttpServletRequest request,HttpServletResponse){
 * 		//todo
 * }
 * 		这个方法接受的参数类型为HttpServletRequest和HttpServletResponse。
 * 		所以HttpServlet中的service方法是如下：
 * public void service(ServletRequest req,ServletResponse res){
 * 		HttpServletRequest request;
 * 		HttpServletResponse response;
 * 		try{
 * 			request = (HttpServletRequest)req;//向下转型
 * 			response = (HttpServletResponse)res;
 * 		}
 * 		catch(ClassCastException e){
 * 			throw new ServletException("non-HTTP request or response");
 * 		}
 * 		service(request,response);
 * }
 * 
 * 		之后，HttpServlet中的新service方法会查看用来发送请求的HTTP方法，并调用以下某个方法
 * 		(doPost,doGet,doHead,doPut,doTrace,doOptions和doDelete)。其中doGet和
 * 		doPost是最常用的。
 * 		总之，HttpServlet中两个特性是GenericServlet所没有的：
 * 		1，不覆盖service方法，而是覆盖doGet,doPost或者两者都覆盖掉，在极少数情况下，
 * 		还要覆盖一下某个方法：doHead,doDelete,doTrace,doPut或doOptions。
 * 		2，用HttpServletRequest和HttpServletResponse代替ServletRequest和
 * 		HttpServletResponse。
 * 
 * 2，HttpServletRequest继承自javax.servlet.ServletRequest接口:
 * 		它在ServletRequest的基础上增加了几个方法：
 * 		1，String getContextPath()---返回请求context的请求URI部分。
 * 		2，Cookie[] getCookies()---返回一个Cookie对象数组。
 * 		3，String getHeader(String name)---返回指定http标头的值。
 * 		4，String getMethod()---返回请求的Http方法的名称。
 * 		5，String getQueryString()---返回请求URL中的查询字符串。
 * 		6，HttpSession getSession()---返回与这个请求相关的session对象，如果没找到，则创建新的session对象。
 * 		7，HttpSession getSession(boolean create)---如果没有找到，参数create为true，则创建新的session对象
 * 
 * 3，HttpServletResponse继承自javax.servlet.ServletResponse接口:
 * 		它在ServletResponse的基础上新增了几个方法：
 * 		1，void addCookie(Cookie cookie)---给这个响应添加一个Cookie。
 * 		2，void addHeader(String name,String value)---给响应添加一个标头。
 * 		3，void sendRedirect(String location)---发送响应代号，将浏览器重定向到指定的位置。
 * 
 * @author wow
 *
 */
@WebServlet(name="FormServlet",urlPatterns={"/form"})
public class HttpServletDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Order Form";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HttpServletDemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>" + TITLE + "</title></head>");
		writer.println("<bodyt><h1>" + TITLE + "</h1>");
		writer.println("<form method='post'>");
		writer.println("<table>");
		writer.println("<tr>");
		writer.println("<td>Name:</td>");
		writer.println("<td><input name='name'/></td>");
		writer.println("</tr>");
		writer.println("<tr>");
		writer.println("<td>Address:</td>");
		writer.println("<td><textarea name='address' cols='40' rows='5'></textarea></td>");
		writer.println("</tr>");
		writer.println("<tr>");
		writer.println("<td>Country:</td>");
		writer.println("<td><select name='country'>");
		writer.println("<option>United States</option>");
		writer.println("<option>Canada</option>");
		writer.println("</select>");
		writer.println("</tr>");
		writer.println("<tr>");
		writer.println("<td>Delivery Method:</td>");
		writer.println("<td><input type='radio' name='deliveryMethod' value='First Class'/>First Class");
		writer.println("<input type='radio' name='deliveryMethod' value='Second Class'/>Second Class</td>");
		writer.println("</tr>");
		writer.println("<tr>");
		writer.println("<td><input type='reset'/></td><td><input type='submit'/></td>");
		writer.println("</tr>");
		writer.println("</table>");
		writer.println("</form>");
		writer.println("</body>");
		writer.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.append("<html>");
		writer.append(String.format("<hear><title>%s</title></head>", TITLE));
		writer.append(String.format("<body><h1>%s</h1>", TITLE));
		writer.append("<tr>");
		writer.append(String.format("<td>Name:</td><td>%s</td>", request.getParameter("name")));
		writer.append("</tr>");
		writer.append("<tr>");
		writer.append(String.format("<td>Address:</td><td>%s</td>", request.getParameter("address")));
		writer.append("</tr>");
		writer.append("<tr>");
		writer.append(String.format("<td>Country:</td><td>%s</td>", request.getParameter("country")));
		writer.append("</tr>");
		writer.append("<tr>");
		writer.append(String.format("<td>Delivery Method:</td><td>%s</td>", request.getParameter("deliveryMethod")));
		writer.append("</tr>");
		writer.append("</table>");
		writer.append("<div style='border:1px solid #ddd;margin-top:40px;font-size:90%'>");
		writer.append("Debuf Info<br/>");
		Enumeration<String> paramterNames = request.getParameterNames();
		while(paramterNames.hasMoreElements()){
			String paramNameString = paramterNames.nextElement();
			writer.append(String.format("%s : ", paramNameString));
			String[] paramValues = request.getParameterValues(paramNameString);
			for(String pValueString : paramValues){
				writer.append(String.format("%s <br/>", pValueString));
			}
		}
		writer.append("</div></body></html>");
		writer.println();
	}

}
