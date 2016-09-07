package com.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.session.MyFirstSessionBeanRemote;
import com.singleton.SingletonBean;

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


		testStatelessBeanRemote(response);
		appendSeparator(response);
		
		
		testStatefulBeanRemote(response);
		appendSeparator(response);
		
		
		testSingletonBean(response);
		appendSeparator(response);
		testSingleTonValues(response);
		
		

	}

	private void testSingletonBean(HttpServletResponse response) {
		Properties jndiProps1 = new Properties();
		jndiProps1.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		jndiProps1.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		try {
			Context ctx = new InitialContext(jndiProps1);
			SingletonBean bean = (SingletonBean) ctx.lookup("java:global/Sample/MyEJB/SingletonBean!com.singleton.SingletonBean");
			PrintWriter out = response.getWriter();
			bean.put("name", "Anand Test SingletonBean");
			bean.put("name1", "Anand Test1 SingletonBean");
			out.print("SingletonBean : @Singleton || adding");
			response.getWriter().append("\n");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		testSingleTonValues(response);
		
	}

	/**
	 * @param response
	 */
	private void testSingleTonValues(HttpServletResponse response) {
		Properties props1 = new Properties();
		props1.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		props1.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		try {
			Context ctx = new InitialContext(props1);
			SingletonBean bean = (SingletonBean) ctx.lookup("java:global/Sample/MyEJB/SingletonBean!com.singleton.SingletonBean");
			bean.put("name2", "Anand Test2 SingletonBean");
			PrintWriter out = response.getWriter();
			Map<String,String> map = bean.getMap();
			out.print("SingletonBean : @Singleton || printing");
			response.getWriter().append("\n");
			if(!map.isEmpty()){
				out.println(map.get("name"));
				out.println(map.get("name1"));
			}else{
				out.println("Nothing in the list");
			}
			out.print("SingletonBean : @Singleton || completed");
			response.getWriter().append("\n");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param response
	 * @throws IOException
	 */
	private void appendSeparator(HttpServletResponse response)
			throws IOException {
		response.getWriter().append("***********************************************************************");
		response.getWriter().append("***********************************************************************");
		response.getWriter().append("***********************************************************************");
		response.getWriter().append("\n");
	}

	/**
	 * @param response
	 */
	private void testStatefulBeanRemote(HttpServletResponse response) {
		Properties jndiProps1 = new Properties();
		jndiProps1.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		jndiProps1.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		try {
			Context ctx = new InitialContext(jndiProps1);
			MyFirstSessionBeanRemote bean = (MyFirstSessionBeanRemote) ctx.lookup("java:global/Sample/MyEJB/MyStateFullSessionBean!com.session.MyFirstSessionBeanRemote");
			PrintWriter out = response.getWriter();
			bean.add("Anand Test MyStateFullSessionBean"); 
			out.print("MyStateFullSessionBean : @Stateful || adding");
			response.getWriter().append("\n");
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
			out.print("MyStateFullSessionBean : @Stateful || printing");
			response.getWriter().append("\n");
			if(!list.isEmpty()){
				out.println(list.get(0));
			}else{
				out.println("Nothing in the list");
			}
			out.println(bean.test());
			out.print("MyStateFullSessionBean : @Stateful || completed");
			response.getWriter().append("\n");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param response
	 */
	private void testStatelessBeanRemote(HttpServletResponse response) {
		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		jndiProps.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		try {
			Context ctx = new InitialContext(jndiProps);
			MyFirstSessionBeanRemote bean = (MyFirstSessionBeanRemote) ctx.lookup("java:global/Sample/MyEJB/MyFirstSessionBean!com.session.MyFirstSessionBeanRemote");
			PrintWriter out = response.getWriter();
			bean.add("Anand Test MyFirstSessionBean"); 
			out.print("MyFirstSessionBean1 : @Stateless  |||| adding ");
			response.getWriter().append("\n");
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
			out.print("MyFirstSessionBean2 : @Stateless ||| printing");
			response.getWriter().append("\n");
			if(!list.isEmpty()){
				out.println(list.get(0));
			}else{
				out.println("Nothing in the list");
			}
			out.println(bean.test());
			out.print("MyFirstSessionBean2 : @Stateless  |||| completed ");
			response.getWriter().append("\n");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
