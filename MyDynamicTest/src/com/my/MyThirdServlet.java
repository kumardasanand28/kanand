package com.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

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
		 
		 testSingleTonValues(response);
		 
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
