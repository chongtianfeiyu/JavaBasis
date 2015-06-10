package com.startcaft.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="PreferenceServlet",urlPatterns={"/Preference"})
public class PreferenceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String MENU_STRING = 
			"<div style='background:#e8e8e8;"
			+ "padding:15px;'>"
			+ "<a href='cookieClass'>Cookie Class</a>&nbsp;&nbsp;"
			+ "<a href='cookieInfo'>Cookie Info</a>&nbsp;&nbsp;"
			+ "<a href='preference'>Preference</a>" + "</div>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html><head>" + "<title>Preference</title>"
				+ "<style>table {" + "font-size:small;"
				+ "background:NavajoWhite }</style>"
				+ "</head><body>"
				+ MENU_STRING
				+ "Please select the values below:"
				+ "<form method='post'>"
				+ "<table>"
				+ "<tr><td>Title Font Size: </td>"
				+ "<td><select name='titleFontSize'>"
				+ "<option>large</option>"
				+ "<option>x-large</option>"
				+ "<option>xx-large</option>"
				+ "</select></td>"
				+ "</tr>"
				+ "<tr><td>Title Style & Weight :</td>"
				+ "<td><select name='titleStyleAndWeight' multiple>"
				+ "<option>italic</option>"
				+ "<option>bold</option>"
				+ "</select></td>"
				+ "</tr>"
				+ "<tr><td>Max. Records in Table: </td>"
				+ "<td><select name='maxRecords'>"
				+ "<option>5</option>"
				+ "<option>10</option>"
				+ "</select></td>"
				+ "</tr>"
				+ "<tr><td rowspan='2'>"
				+ "<input type='submit' value='setPreference'></td>"
				+ "</tr>"
				+ "</table>" + "</form>" + "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String maxRecords = request.getParameter("maxRecords");
		String[] titleStyleAndWeight = request.getParameterValues("titleStyleAndWeight");
		String titleFontSize = request.getParameter("titleFontSize");
		
		response.addCookie(new Cookie("maxRecords", maxRecords));
		response.addCookie(new Cookie("titleFontSize", titleFontSize));
		
		//Cookie对象的setMaxAge(int)方法表示cookie在客户端存储的时间
		//负值表示cookie不会被持久存储，将在Web浏览器退出时删除。
		//0值会导致删除cookie。只能用这个方法来删除一个Cookie对象。
		Cookie cookie = new Cookie("titleStyleAndWeight", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		cookie = new Cookie("titleFontStyle", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		if (titleStyleAndWeight != null) {
			for(String mySytle : titleStyleAndWeight){
				if (mySytle.equals("bold")) {
					response.addCookie(new Cookie("titleFontWeight", "bold"));
				}
				else if (mySytle.equals("italic")) {
					response.addCookie(new Cookie("titleFontStyle", "italic"));
				}
			}
		}
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html><head><title>Preference</title>"
				+ "</head><body>" + MENU_STRING
				+ "Your preference has bee set."
				+ "<br/><br/>Max. Records in Table:" + maxRecords
				+ "<br/>Title Font Size: " + titleFontSize
				+ "<br/>Title Font Style & Weight: ");
		
		if (titleStyleAndWeight != null) {
			writer.println("<ul>");
			for (String str : titleStyleAndWeight) {
				writer.print("<li>"+ str +"</li>");
			}
			writer.println("</ul>");
		}
		writer.println("</body></html>");
	}

}
