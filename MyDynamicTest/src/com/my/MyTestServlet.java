package com.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.session.MyFirstSessionBeanRemote;
import com.sun.xml.ws.util.StringUtils;

/**
 * Servlet implementation class MyTestServlet
 */
@WebServlet("/MyTestServlet")
public class MyTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyTestServlet() {
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
			bean.add("Anand Test MyFirstSessionBean"); 
		}catch (Exception e) {
			e.printStackTrace();
		}

		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		try {
			Context ctx = new InitialContext(props);
			MyFirstSessionBeanRemote bean = (MyFirstSessionBeanRemote) ctx.lookup("java:global/Sample/MyEJB/MyFirstSessionBean!com.session.MyFirstSessionBeanRemote");
			PrintWriter out = response.getWriter();
			List<String> list = bean.get();
			out.print("MyFirstSessionBean : @Stateless");
			if(!list.isEmpty()){
				out.println(list.get(0));
			}else{
				out.println("Nothing in the list");
			}
			out.println(bean.test());
		}catch (Exception e) {
			e.printStackTrace();
		}


		
		
		
		
		
		
		
		
		
		
		Properties jndiProps1 = new Properties();
		jndiProps1.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		jndiProps1.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		try {
			Context ctx = new InitialContext(jndiProps1);
			MyFirstSessionBeanRemote bean = (MyFirstSessionBeanRemote) ctx.lookup("java:global/Sample/MyEJB/MyStateFullSessionBean!com.session.MyFirstSessionBeanRemote");
			PrintWriter out = response.getWriter();
			bean.add("Anand Test MyStateFullSessionBean"); 
		}catch (Exception e) {
			e.printStackTrace();
		}

		Properties props1 = new Properties();
		props1.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		props1.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		try {
			Context ctx = new InitialContext(props1);
			MyFirstSessionBeanRemote bean = (MyFirstSessionBeanRemote) ctx.lookup("java:global/Sample/MyEJB/MyStateFullSessionBean!com.session.MyFirstSessionBeanRemote");
			PrintWriter out = response.getWriter();
			List<String> list = bean.get();
			out.print("MyStateFullSessionBean : @Stateful");
			if(!list.isEmpty()){
				out.println(list.get(0));
			}else{
				out.println("Nothing in the list");
			}
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
