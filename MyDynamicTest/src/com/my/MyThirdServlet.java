package com.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.session.MyFirstSessionBeanRemote;

/**
 * Servlet implementation class MyThirdServlet
 */
@WebServlet("/MyThirdServlet")
public class MyThirdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyThirdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		jndiProps.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		 try {
			Context ctx = new InitialContext(jndiProps);
			MyFirstSessionBeanRemote bean = (MyFirstSessionBeanRemote) ctx.lookup("java:global/Sample/MyEJB/MyFirstSessionBean!com.session.MyFirstSessionBeanRemote");
			PrintWriter out = response.getWriter();
			out.println(bean.test());  
		 }catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
