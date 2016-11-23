package main.java.com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import main.java.com.constants.UserConstants;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String POST_URL = "http://10.0.66.14/SampleWebApp/rest/json/user/register";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
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
		try{

			String fullName = request.getParameter(UserConstants.FULL_NAME);
			String age = request.getParameter(UserConstants.AGE);
			JSONObject obj = new JSONObject();
			obj.put("name", fullName);
			obj.put("age", age);

			HttpClient httpClient = HttpClientBuilder.create().build();

			HttpPost postRequest = new HttpPost(POST_URL);
			postRequest.setHeader("Content-type", "application/json");
			StringEntity entity = new StringEntity(obj.toString());
			postRequest.setEntity(entity);

			long startTime = System.currentTimeMillis();
			
			HttpResponse httpResponse = httpClient.execute(postRequest);
			
			long elapsedTime = System.currentTimeMillis() - startTime;
			System.out.println("Time taken : "+elapsedTime+"ms");
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
