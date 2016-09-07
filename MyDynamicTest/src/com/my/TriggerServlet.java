package com.my;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.singleton.PropertyRegistryBeanManaged;
import com.utils.LookUpUtils;

/**
 * Servlet implementation class TriggerServlet
 */
@WebServlet("/TriggerServlet")
public class TriggerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LookUpUtils lookup = new LookUpUtils();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TriggerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//testing PropertyRegistryBeanManaged
		PrintWriter out = response.getWriter();
		PropertyRegistryBeanManaged propertyRegistryBeanManaged = (PropertyRegistryBeanManaged) lookup.lookUp("java:global/Sample/MyEJB/PropertyRegistryBeanManaged!com.singleton.PropertyRegistryBeanManaged");
		out.println(propertyRegistryBeanManaged.getProperties());  
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
