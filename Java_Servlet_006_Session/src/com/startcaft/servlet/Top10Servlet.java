package com.startcaft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Top10Servlet
 */
@WebServlet(name = "Top10Servlet", urlPatterns = { "/top10" })
public class Top10Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> londons, paris;

	// 重写GenericServlet中的init()方法用于初始化Top10Servlet类中的成员变量。 有点像模板方法设计模式了
	@Override
	public void init() throws ServletException {

		londons = new ArrayList<String>();
		paris = new ArrayList<String>();

		for (int i = 1; i <= 10; i++) {
			londons.add(String.format("London_%d", i));
			paris.add(String.format("Paris_%d", i));
		}
	}

	public Top10Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String cityName = request.getParameter("city");
		if (cityName != null && "london".equals(cityName)
				|| "paris".equals(cityName)) {
			showAttractions(request, response, cityName);
		} else {
			showMainPage(request, response);
		}
	}

	private void showMainPage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.printf(String
				.format("<html><head><title>Top 10 Attractions</title>"
						+ "</head><body>Please select a city:<br/>"
						+ "<a href='%s'>London</a><br/><a href='%s'>Paris</a></body></html>",
						"?city=london", "?city=paris"));
	}

	private void showAttractions(HttpServletRequest request,
			HttpServletResponse response, String cityName) throws IOException {
			
		int page = 1;
		String pageNumberString = request.getParameter("page");
		if (pageNumberString != null) {
			try {
				page = Integer.parseInt(pageNumberString);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if (page > 2) {
				page = 1;
			}
		}
		List<String> attractions= null;
		if (cityName.equals("london")) {
			attractions = londons;
		}
		else if (cityName.equals("paris")) {
			attractions = paris;
		}
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><head><title>Top 10 Attractions</title></head><body>");
		buffer.append("<a href='top10'>Select City</a>");//返回主界面top10
		buffer.append(String.format("<hr/>Page %d</hr></br>", page));//打印当前页

		
		int start = page * 5 - 5;
		for (int i = start; i < start + 5; i++) {
			buffer.append(attractions.get(i) + "<br/>");
		}
		buffer.append(String.format("<hr style='color:blue'><a href='?city=%s&page=1'>Page 1</a>", cityName));
		buffer.append(String.format("<hr style='color:blue'><a href='?city=%s&page=2'>Page 2</a>", cityName));
		buffer.append("</body></html>");
		
		writer.println(buffer.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
