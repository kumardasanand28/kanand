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
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String random = "_One";
	private String random1 = "_Two";
	private String random2 = "_Three";
	private String random3 = "_Four";

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


		String action = request.getParameter("id");
		TestJPAService test = new TestJPAService();

		if(action != null && !StringUtils.isNullOrEmpty(action)){
			if(action.equalsIgnoreCase("persist")){

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
				addressMap.put("city"+random2, "Trivandrum");
				addressMap.put("city"+random3, "Calicut");
				vehicleMap.put(Constants.ADDRESS_VEHICLE, addressMap);

				test.persistSampleData(policyMap);

			}else if(action.equalsIgnoreCase("fetchtest")){
				test.fetchTest("testpolicyname1"+random);
			}else if(action.equalsIgnoreCase("delete")){
				test.delete("testpolicyname1"+random);
			}else if(action.equalsIgnoreCase("immutabletest")){
				test.loadImmutableEntity("testpolicyname1"+random);
			}else if(action.equalsIgnoreCase("immutableupdate")){
				test.removeEmmutableEntityCheck("testpolicyname1"+random);
			}else if(action.equalsIgnoreCase("projection")){
				test.testProjectionQuery("testpolicyname1"+random);
			}
		}


	}

}
