/**
 * 
 */
package com.java.register.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.register.bean.User;
import com.java.register.constants.UserConstants;
import com.java.register.service.UserService;
import com.mysql.jdbc.StringUtils;



/**
 * @author 203328
 *
 */
public class UserRegisterServlet  extends HttpServlet{
	
	UserService userService = new UserService();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setDefaultResponseHeaders(response);
		
		String action = request.getParameter("action");
		if (action != null && action.equalsIgnoreCase("register")) {
			User user = new User();
			response.setContentType("text/html;charset=UTF-8");
			boolean isValid = validateUserDetails(request);
			if (isValid) {
				populateUserDetails(request, user);
				try {
					userService.insertIntoDataBase(user);
					response.getWriter().write("SuccessFully Registered");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write("Registration Failed");
				}
			}
		}else{
			
		}
		
	}
	
	
	private void setDefaultResponseHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	}
	
	

	private void populateUserDetails(HttpServletRequest request, User user) {
		user.setFullName(request.getParameter(UserConstants.FULL_NAME));
		user.setAge(Integer.parseInt(request.getParameter(UserConstants.AGE)));
		user.setQualification(request.getParameter(UserConstants.QUALIFICATION));
		user.setYearPassed(Integer.parseInt(request.getParameter(UserConstants.YEAR_PASSED)));
		user.setGender(request.getParameter(UserConstants.GENDER));
		user.setStreet(request.getParameter(UserConstants.STREET));
		user.setState(request.getParameter(UserConstants.STATE));
		user.setCity(request.getParameter(UserConstants.CITY));
		user.setZip(request.getParameter(UserConstants.ZIP_CODE));
		user.setAddressNickName(request.getParameter("aNickName"));
	}
	

	private boolean validateUserDetails(HttpServletRequest request) {

		String fullName = request.getParameter(UserConstants.FULL_NAME);
		if (StringUtils.isNullOrEmpty(fullName)) {
			return false;
		}

		String age = request.getParameter(UserConstants.AGE);
		if (StringUtils.isNullOrEmpty(age) || Pattern.matches("[a-zA-Z]+", age)) {
			return false;
		}

		String qualification = request.getParameter(UserConstants.QUALIFICATION);
		if (StringUtils.isNullOrEmpty(qualification)) {
			return false;
		}

		String yearPassed = request.getParameter(UserConstants.YEAR_PASSED);
		if (StringUtils.isNullOrEmpty(yearPassed) || Pattern.matches("[a-zA-Z]+", yearPassed)) {
			return false;
		}

		String gender = request.getParameter(UserConstants.GENDER);
		if (StringUtils.isNullOrEmpty(gender)) {
			return false;
		}

		return true;

	}

	
}
