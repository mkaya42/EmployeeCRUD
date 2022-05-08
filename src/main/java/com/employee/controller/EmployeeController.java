package com.employee.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.employee.business.Employee;
import com.employee.data.EmployeeDB;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet(name = "employeeServlet", urlPatterns = { "/employeeServlet" })
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmployeeDB employeeDB;
	@Resource(name = "jdbc/employeecrud")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			employeeDB = new EmployeeDB(dataSource);
		} catch (Exception e) {
			throw new ServletException();
		}

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeController() {
		super();

	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String command = request.getParameter("command");
		String url = "";
		
		if (command == null) {
			url = listEmployees(request, response);
		}

		else if (command.equals("add")) {

			url = addEmployee(request, response);
		}

		else if (command.equals("load")) {

			url = loadEmployee(request, response);
		}

		else if (command.equals("update")) {

			url = updateEmployee(request, response);
		}
		
		else if (command.equals("delete")) {

			url = deleteEmployee(request, response);
		}
		else if(command.equals("filter")) {
			
			url = filterEmployee(request, response);

		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	private String filterEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String searchText=request.getParameter("filterText");
		List<Employee> employeeList = EmployeeDB.filterEmployee(searchText);
		request.setAttribute("employeeList", employeeList);
		String url = "/listEmployees.jsp";
		return url;
	}

	private String deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int employeeId=Integer.parseInt(request.getParameter("employeeId"));
		EmployeeDB.deleteEmployee(employeeId);
		return listEmployees(request, response);
	}

	private String loadEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		Employee employee = EmployeeDB.getEmployee(employeeId);
		request.setAttribute("employee", employee);
		return "/updateEmployee.jsp";
	}

	private String updateEmployee(HttpServletRequest request, HttpServletResponse response) {

		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		Employee employee = new Employee(employeeId,firstName, lastName, email, address);
		
		EmployeeDB.updateEmployee(employee);
		
		return listEmployees(request, response);
	}

	private String addEmployee(HttpServletRequest request, HttpServletResponse response) {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		Employee employee = new Employee(firstName, lastName, email, address);
		EmployeeDB.addEmployee(employee);

		// TODO Auto-generated method stub
		return listEmployees(request, response);
	}

	private String listEmployees(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Employee> employeeList = EmployeeDB.getEmployees();

		request.setAttribute("employeeList", employeeList);
		String url = "/listEmployees.jsp";
		return url;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
