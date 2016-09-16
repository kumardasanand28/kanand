package com.java.register.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.register.constants.Constants;
import com.java.triggerjpa.TestJPAService;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String random = "_One";
	private String random1 = "_Two";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, Object> policyMap = new HashMap<>();
		policyMap.put(Constants.POLICY_NAME, "testpolicyname1"+random);
		
		
		
		Map<String, Object> partyMap = new HashMap<>();
		partyMap.put(Constants.PARTY_NAME+random, "testpartyname"+random);
		partyMap.put(Constants.PARTY_NAME+random1, "testpartyname"+random1);
		//partyMap.put(Constants.PARTY_POLICY, policyMap);
		policyMap.put(Constants.PARTY_POLICY, partyMap);
		
		Map<String, Object> vehicleMap = new HashMap<>();
		vehicleMap.put(Constants.VEHICLE_NAME+random, "testvehiclename"+random);
		vehicleMap.put(Constants.VEHICLE_NAME+random1, "testvehiclename"+random1);
		policyMap.put(Constants.VEHICLE_POLICY, vehicleMap);
		
		
		Map<String, Object> addressMap = new HashMap<>();
		addressMap.put("city"+random, "Cochin");
		addressMap.put("city"+random1, "Trissur");
		vehicleMap.put(Constants.ADDRESS_VEHICLE, addressMap);
		
		TestJPAService test = new TestJPAService();
		test.persistSampleData(policyMap);
		
	}

}
