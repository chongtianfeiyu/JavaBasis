package com.startcaft.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.startcaft.model.Address;
import com.startcaft.model.Employee;


@WebServlet(urlPatterns={"/employee"})
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Address address = new Address();
		address.setStreetyName("Rue D'Anjou");
		address.setStreetNumber("5090B");
		address.setCity("Broassard");
		address.setState("Quebec");
		address.setZipCode("A1A B2B");
		address.setCountry("Canada");
		
		Employee emp = new Employee();
		emp.setId(1099);
		emp.setName("startcaft");
		emp.setAddress(address);
		
		request.setAttribute("employee", emp);
		java.util.Map<String, String> capitals = new HashMap<String,String>();
		capitals.put("China", "Beijing");
		capitals.put("Austria", "Vienna");
		capitals.put("Canada", "Ottawa");
		request.setAttribute("capitals", capitals);
		
		RequestDispatcher rd = request.getRequestDispatcher("/employee.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
