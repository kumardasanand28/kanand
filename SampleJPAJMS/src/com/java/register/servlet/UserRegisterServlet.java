/**
 * 
 */
package com.java.register.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.java.register.bean.Address;
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
		String name = request.getParameter("name");

		if (action != null && action.equalsIgnoreCase("register")) {
			User user = new User();
			response.setContentType("text/html;charset=UTF-8");
			boolean isValid = validateUserDetails(request);
			if (isValid) {
				populateUserDetails(request, user,false);
				try {
					userService.insertIntoDataBase(user);
					response.getWriter().write("SuccessFully Registered");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write("Registration Failed");
				}
			}
		}else if (action != null && action.equalsIgnoreCase("update")){
			User user = new User();
			response.setContentType("text/html;charset=UTF-8");
			boolean isValid = validateUserDetails(request);
			if (isValid) {
				populateUserDetails(request, user,false);
				user.setId(Integer.parseInt(name));
				try {
					userService.updateUser(user);
					response.getWriter().write("SuccessFully Updated");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write("Updation Failed");
				}
			}else{
				response.getWriter().write("Validation Failure");
			}
		}else if (action != null && action.equalsIgnoreCase("addaddress")){
			User user = new User();
			response.setContentType("text/html;charset=UTF-8");
				populateUserDetails(request, user,true);
				user.setId(Integer.parseInt(name));
				try {
					userService.addAddress(user);
					response.getWriter().write("SuccessFully Added Address");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write("Address Addition failed");
				}
		}

	}


	private void setDefaultResponseHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	}



	private void populateUserDetails(HttpServletRequest request, User user, boolean populateOnlAddress) {

		if(!populateOnlAddress){
			user.setFullName(request.getParameter(UserConstants.FULL_NAME));
			user.setAge(Integer.parseInt(request.getParameter(UserConstants.AGE)));
			user.setQualification(request.getParameter(UserConstants.QUALIFICATION));
			user.setYearPassed(Integer.parseInt(request.getParameter(UserConstants.YEAR_PASSED)));
			user.setGender(request.getParameter(UserConstants.GENDER));
		}
		List<Address> addressList = new ArrayList<Address>();
		Address address = new Address();
		address.setStreet(request.getParameter(UserConstants.STREET));
		address.setState(request.getParameter(UserConstants.STATE));
		address.setCity(request.getParameter(UserConstants.CITY));
		address.setZip(request.getParameter(UserConstants.ZIP_CODE));
		address.setAddressNickName(request.getParameter("aNickName"));
		addressList.add(address);
		user.setAddressList(addressList);
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


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setDefaultResponseHeaders(response);
		String param = request.getParameter("getAllValues");

		if (param != null && param.equalsIgnoreCase("true")) {
			try {
				List<User> userList = userService.fetchUsers();

				String json = new Gson().toJson(userList);

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			String name = request.getParameter("name");
			if (!StringUtils.isNullOrEmpty(name)) {
				int id = Integer.parseInt(request.getParameter("name"));
				try {
					User user = userService.fetchUser(id);
					String json = new Gson().toJson(user);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}
