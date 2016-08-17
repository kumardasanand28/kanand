package main.java.com;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.bean.User;
import main.java.com.constants.UserConstants;

public class UserRegistrationServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		User user = new User();
		response.setContentType("text/html;charset=UTF-8");
		boolean isValid = validateUserDetails(request);
		if(isValid){
			populateUserDetails(request,user);

			try {
				insertIntoDataBase(user);


				response.getWriter().write("SuccessFully Registered");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write("Registration Failed");
			}
		}

	}



	private void insertIntoDataBase(User user) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = getMySqlConnection();

			String insertQuery = " insert into USERS (FULL_NAME, AGE, QUALIFICATION, PASSED_YEAR, GENDER, INTERESTS)"
					+ " values (?, ?, ?, ?, ?,?)";

			PreparedStatement preparedStmt = conn.prepareStatement(insertQuery);
			preparedStmt.setString(1,user.getFullName());
			preparedStmt.setInt(2, user.getAge());
			preparedStmt.setString(3, user.getQualification());
			preparedStmt.setInt(4, user.getYearPassed());
			preparedStmt.setString(5, user.getGender());
			preparedStmt.setString(6, getInterestList(user.getInterests()));

			// execute the preparedstatement
			preparedStmt.execute();

			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}  


	}


	public String getInterestList(String [] interests) 
	{
		String list = Arrays.toString(interests);
		return list.substring(1,list.length()-1);
	}


	private boolean validateUserDetails(HttpServletRequest request) {
		return true;

	}


	private Connection getMySqlConnection() throws SQLException{
		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/mysampleapp","root","admin");
		return con;  

	}


	private void populateUserDetails(HttpServletRequest request, User user) {
		user.setFullName(request.getParameter(UserConstants.FULL_NAME));
		user.setAge(Integer.parseInt(request.getParameter(UserConstants.AGE)));
		user.setQualification(request.getParameter(UserConstants.QUALIFICATION));
		user.setPercentage(Integer.parseInt(request.getParameter(UserConstants.PERCENTAGE)));
		user.setYearPassed(Integer.parseInt(request.getParameter(UserConstants.YEAR_PASSED)));
		user.setGender(request.getParameter(UserConstants.GENDER));
		user.setInterests(request.getParameterValues(UserConstants.INTERESTS));
	}

}
