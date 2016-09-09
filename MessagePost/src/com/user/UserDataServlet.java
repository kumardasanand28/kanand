package com.user;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.UserEntity;
import com.google.gson.Gson;
import com.messagesender.MessageSender;
import com.stateful.UserStateFul;



/**
 * Servlet implementation class UserDataServlet
 */
@WebServlet("/UserDataServlet")
public class UserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDataServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@EJB
	MessageSender messageSender;

	@EJB
	UserStateFul userStateful;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//postMessage(response);

		List<UserEntity> userList = null;
		try {
			userList = userStateful.getUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String json = new Gson().toJson(userList);

		System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String fullName = request.getParameter("fullname");
			String empId = request.getParameter("empid");
			if((fullName != null && !fullName.equalsIgnoreCase("")) || (empId != null && !empId.equalsIgnoreCase(""))){
				messageSender.postMessage(request.getParameter("fullname"), request.getParameter("empid"));	
			}else{
				response.getWriter().write("Validation Failure");
			}
			response.getWriter().write("Message Posting Success");
		}catch (Exception e) {
			System.out.println(e);
			response.getWriter().write("Message Posting Failed");
		}

	}

	public MessageSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public UserStateFul getUserStateful() {
		return userStateful;
	}

	public void setUserStateful(UserStateFul userStateful) {
		this.userStateful = userStateful;
	}

}
