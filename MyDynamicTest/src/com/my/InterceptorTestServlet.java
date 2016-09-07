package com.my;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdi.interceptors.beans.BookShow;

/**
 * Servlet implementation class InterceptorTestServlet
 */
@WebServlet("/InterceptorTestServlet")
public class InterceptorTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private BookShow book;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InterceptorTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(book.getMoviesList());  
		out.println(book.getDiscountedPrice(100)); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
