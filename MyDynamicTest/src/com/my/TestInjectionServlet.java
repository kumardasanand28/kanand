package com.my;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdi.stateless.Waiter;
import com.stateless.Course;

/**
 * Servlet implementation class TestInjectionServlet
 */
@WebServlet("/TestInjectionServlet")
public class TestInjectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private Course course;
	
	@EJB
	private Waiter waiter;
	
	@EJB
	private com.cdi.stateless.applicationscope.WaiterApScope waiterApp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestInjectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//testing CDI inject
		out.println(course.testCourseStateless());  
		
		
		//testing CDI request  scope
		out.println("CDI request  scope :  "+waiter.orderSoup("chicken soup"));  
		out.println("CDI request  scope :  "+waiter.orderWhatTheOtherGuyHad());  
		
		
		//testing CDI application scope
		out.println("CDI application scope :  "+waiterApp.orderSoup("Sweet corn soup")); 
		out.println("CDI application scope  : "+waiterApp.orderWhatTheOtherGuyHad()); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
