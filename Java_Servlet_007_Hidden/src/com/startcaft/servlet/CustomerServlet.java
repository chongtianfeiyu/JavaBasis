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

import com.startcaft.model.Customer;


@WebServlet(name = "CustomerServlet", 
	urlPatterns = { "/customer","/editCustomer","/updateCustomer" }
)
public class CustomerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private List<Customer> customers = new ArrayList<Customer>();

	public CustomerServlet() {
		super();
	}

	// 重写GenericServlet中的init()方法，进行本类的初始化工作。
	@Override
	public void init() throws ServletException {
		Customer c1 = new Customer();
		c1.setId(1);
		c1.setCity("WH");
		c1.setName("startcaft");
		customers.add(c1);

		Customer c2 = new Customer();
		c2.setId(2);
		c2.setCity("WH");
		c2.setName("pengxiaoling");
		customers.add(c2);
		System.out.println("init data done!");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String url = request.getRequestURL().toString();
		System.out.println("uri:" + uri);
		System.out.println("url" + url);
		if (uri.endsWith("/customer")) {
			sendCustomerList(response);
		}
		else if (uri.endsWith("/editCustomer")) {
			sendEditCustomerForm(request,response);
		}
	}
	
	/**
	 * 输出一个表单---用于修改Customer的信息
	 * 
	 * @param request Http请求
	 * @param response Http响应
	 * @throws IOException
	 */
	private void sendEditCustomerForm(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		int customerId = 0;
		try {
			customerId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
		}
		Customer customer = getCustomer(customerId);
		
		if (customer != null) {
			writer.println("<html></head>"
					+ "<title>Edit Customer</title></head>"
					+ "<body><h2>Edit Customer</h2>"
					+ "<form method='post' "
					+ "action='updateCustomer'>");
			//输入一个input type='hidden'隐藏于，用于存储被修改用户的 customerId
			writer.println("<input type='hidden' name='id' value='"
					+ customerId + "'/>");
			writer.println("<table>");
			writer.println("<tr><td>Name:</td><td>"
					+ "<input name='name' value='"+
					customer.getName().replaceAll("'", "$#39;")
					+"'/></td></tr>");
			writer.println("<tr><td>City:</td><td>"
					+ "<input name='city' value='" +
					customer.getCity().replaceAll("'", "$#39;")
					+"'/></td></tr>");
			writer.println("<tr>"
					+ "<td colspan='2' style='text-align:right'>"
					+ "<input type='submit' value='Update'/></td>"
					+ "</tr>");
			writer.println("<tr>"
					+ "<td colspan='2' style='text-align:right'>"
					+ "<a href='customer'>Customer List</2>"
					+ "</td></tr>");
			writer.println("</table>");
		}
		else {
			writer.println("No Customer Found");
		}
	}
	
	/**
	 * 根据customerId获取一个Customer对象
	 * @param customerId
	 * @return Customer对象或者null
	 */
	private Customer getCustomer(int customerId) {
		
		for (Customer customer : customers) {
			if (customer.getId() == customerId) {
				return customer;
			}
		}
		return null;
	}

	/**
	 * 输出一个Customer列表
	 * 
	 * @param response HTTP响应
	 * @throws IOException
	 */
	private void sendCustomerList(HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		writer.println("<html><head><title>Customer List</title></head>"
					+"<body><h2>Customers:</h2>");
		writer.println("<ul>");
		for (Customer customer : customers) {
			writer.println("<li>" + customer.getName()
					+ "(" + customer.getCity() + ") ("
					+ "<a href='editCustomer?id=" + customer.getId()
					+ "'>edit</a>)");
		}
		writer.println("</ul></body></html>");
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int customerId = 0;
		try {
			customerId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
		}
		Customer customer = getCustomer(customerId);
		if (customer != null) {
			customer.setName(request.getParameter("name"));
			customer.setCity(request.getParameter("city"));
		}
		sendCustomerList(response);
	}

}
