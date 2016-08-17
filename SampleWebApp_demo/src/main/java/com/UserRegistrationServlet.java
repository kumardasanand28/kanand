package main.java.com;


import java.io.IOException;
import main.java.com.bean.User;
import main.java.com.constants.UserConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegistrationServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		User user = new User();
		populateUserDetails(request,user);

		/*response.setContentType("text/plain");
		response.getWriter().write(color);*/
	}



	private void populateUserDetails(HttpServletRequest request, User user) {
		user.setFullName(request.getParameter(UserConstants.FULL_NAME));
		user.setAge(Integer.parseInt(request.getParameter(UserConstants.AGE)));
		user.setQualification(request.getParameter(UserConstants.QUALIFICATION));
		user.setPercentage(Integer.parseInt(request.getParameter(UserConstants.PERCENTAGE)));
		user.setYearPassed(Integer.parseInt(request.getParameter(UserConstants.YEAR_PASSED)));
	}

}
