package com.employee.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;
import com.employee.business.*;

public class EmployeeDB {

	@Resource(name = "jdbc/employeecrud")
	private static DataSource dataSource;

	public EmployeeDB(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static List<Employee> getEmployees() {

		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			conn = dataSource.getConnection();
			String queryString = "select * from employee order by firstName";
			stmt = conn.createStatement();

			// execute query..
			rset = stmt.executeQuery(queryString);

			// processing result set..
			while (rset.next()) {

				Employee employee = new Employee(rset.getInt("employeeId"),rset.getString("firstName"), rset.getString("lastName"),
												 rset.getString("email"), rset.getString("address"));

				employeeList.add(employee);

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception : " + e.getMessage());
			return null;

		} finally {
			close(conn,rset,stmt);

		}

		return employeeList;

	}

	public static List<Employee> filterEmployee(String searchText) {

		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			conn = dataSource.getConnection();
			String queryString = "select * from employee where firstName=? or lastName=?";
			pstmt = conn.prepareStatement(queryString);
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			

			// execute query..
			rset = pstmt.executeQuery();

			// processing result set..
			while (rset.next()) {

				Employee employee = new Employee(rset.getInt("employeeId"),rset.getString("firstName"), rset.getString("lastName"),
												 rset.getString("email"), rset.getString("address"));

				employeeList.add(employee);

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception : " + e.getMessage());
			return null;

		} finally {
			close(conn,rset,pstmt);

		}

		return employeeList;

	}
	
	
	public static Employee getEmployee(int employeeId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Employee employee=null;
		try {
			conn = dataSource.getConnection();
			String queryString = "select * from employee where employeeId=?";
			pstmt = conn.prepareStatement(queryString);
			pstmt.setInt(1, employeeId);
			// execute query..
			rset = pstmt.executeQuery();

			// processing result set..
			while (rset.next()) {

				 employee = new Employee(rset.getInt("employeeId"),rset.getString("firstName"), rset.getString("lastName"),
												 rset.getString("email"), rset.getString("address"));

				

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception : " + e.getMessage());
			return null;

		} finally {
			close(conn,rset,pstmt);

		}
		
		
		return employee;
		
	}
	private static void close(Connection conn, ResultSet rset, Statement stmt) {
		try {
			if(conn!=null) {
				conn.close();
			}
			if(rset!=null) {
				rset.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void addEmployee(Employee employee) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=dataSource.getConnection();
			String queryString="Insert Into employee(firstName, lastName, email,address) values(?,?,?,?)";
			pstmt=conn.prepareStatement(queryString);
			
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setString(3, employee.getEmail());
			pstmt.setString(4, employee.getAddress());
			
			pstmt.execute();
			
		} catch (Exception e) {
			System.out.println("Exception "+e.getMessage());
		}
		finally {
			close(conn, null, pstmt);
		}
		
	}

	public static void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=dataSource.getConnection();
			String queryString="Update employee set firstName=? , lastName=?, email=?,address=? where employeeId=?";
			pstmt=conn.prepareStatement(queryString);
			
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setString(3, employee.getEmail());
			pstmt.setString(4, employee.getAddress());
			pstmt.setInt(5, employee.getEmployeeID());

			pstmt.execute();
			
		} catch (Exception e) {
			System.out.println("Exception "+e.getMessage());
		}
		finally {
			close(conn, null, pstmt);
		}
	}

	public static void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=dataSource.getConnection();
			String queryString="Delete from employee  where employeeId=?";
			pstmt=conn.prepareStatement(queryString);
			pstmt.setInt(1, employeeId);
			pstmt.execute();
			
		} catch (Exception e) {
			System.out.println("Exception "+e.getMessage());
		}
		finally {
			close(conn, null, pstmt);
		}
	}

}
