package com.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.stateful.UserStateFullBeanTest;

/**
 * Servlet implementation class TestSessionServlet
 */
//java:global/Sample/EJBMessageSend/UserStateFullBeanTest!com.stateful.UserStateFullBeanTestRemote
@WebServlet("/TestSessionServlet")
public class TestSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	UserStateFullBeanTest user = null;

	public TestSessionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			Context ctx = getSessionContext(request);
			System.out.println("Context : "+ctx);
			if(ctx == null){
				ctx = getInitialContext();
				System.out.println("Context 1: "+ctx);
			}
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.setAttribute("context", ctx);
			}
			user = (UserStateFullBeanTest) ctx.lookup("java:app/EJBMessageSend/UserStateFullBeanTest!com.stateful.UserStateFullBeanTest");	
			System.out.println("UserStateFullBeanTest Instance : "+user);
			Map<String, String> nameMap = user.getNameMap();
			String json = new Gson().toJson(nameMap);
			System.out.println(json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setRemoteValues(HttpServletRequest request,Context ctx) throws NamingException {
		user = (UserStateFullBeanTest) ctx.lookup("java:app/EJBMessageSend/UserStateFullBeanTest!com.stateful.UserStateFullBeanTest");	
		System.out.println("UserStateFullBeanTest Instance in POST: "+user);
		Map<String, String> nameMap = new HashMap<String, String>();
		nameMap.put(request.getParameter("key"), request.getParameter("value"));
		nameMap.putAll(user.getNameMap());
		user.setNameMap(nameMap);
		System.out.println(nameMap);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Context ctx = getSessionContext(request);
		try {
			System.out.println("Context POST : "+ctx);
			setRemoteValues(request,ctx);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private Context getInitialContext()
			throws javax.naming.NamingException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080/");
		return new javax.naming.InitialContext(jndiProperties);
	}


	private Context getSessionContext(HttpServletRequest request){
		Context ctx= null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			ctx =  (Context) session.getAttribute("context");
		}
		return ctx;
	}


}
