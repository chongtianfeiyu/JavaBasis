package com.startcaft.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieInfoServlet
 */
@WebServlet(name="CookieInfoServlet",urlPatterns={"/cookieInfo"})
public class CookieInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		StringBuilder styles = new StringBuilder();
		styles.append(".title {");
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				if (name.equals("titleFontSize")) {
					styles.append("font-size:" + value + ";");
				}
				else if (name.equals("titleFontWeight")) {
					styles.append("font-weight:" + value + ";");
				}
				else if (name.equals("titleFontStyle")) {
					styles.append("font-style:" + value + ";");
				}
			}
		}
		styles.append("}");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head>" + "<title>Cookie Info</title>"
				+ "<style>" + styles.toString() + "</style>"
				+ "</head><body>" + PreferenceServlet.MENU_STRING
				+ "<div class='title'>"
				+ "Session Management with Cookies:</div>");
		writer.print("<div>");
		if (cookies == null) {
			writer.print("No cookie in this HTTP response.");
		}
		else {
			writer.print("<rb/>Cookies in this HTTP response:");
			for (Cookie cookie : cookies) {
				writer.print("<br/>" + cookie.getName() + ":"
						+ cookie.getValue());
			}
		}
		writer.print("</div></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
