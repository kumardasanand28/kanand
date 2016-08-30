package com.my;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.session.MyFirstSessionBean;
import com.singleton.SingletonBean;

/**
 * Servlet implementation class MyFirstServlet
 */
@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public MyFirstServlet() {
		// TODO Auto-generated constructor stub
	}

	@EJB(mappedName = "java:global/Sample/MyEJB/MyFirstSessionBean!com.session.MyFirstSessionBean")
	MyFirstSessionBean bean;

	@EJB
	SingletonBean sBean;

	@Override
	public void init(){
		sBean.put("name", "Hello World");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Called");
		PrintWriter out = response.getWriter();
		out.println(bean.test());  
		out.println(sBean.get("name"));  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
