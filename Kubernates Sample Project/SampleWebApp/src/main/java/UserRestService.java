package main.java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import main.java.com.db.UserService;

@Path("/json/user")
public class UserRestService {


	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJSON(InputStream incomingData){
		StringBuilder builder = new StringBuilder();
		try{
			//BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
			String line = null;
			while ((line = in.readLine()) != null) {
				builder.append(line);
			}
		
			UserService service = new UserService();
			
			JSONObject obj = new JSONObject(builder.toString());
			String name = obj.getString("name");
			String age = obj.getString("age");
			service.persistTestFor3Tier(name,age);
			
			
			System.out.println("Data Received: " + builder.toString());
			System.out.println("Name" +name);
			System.out.println("Age" +age);


		}catch (Exception e) {
			System.out.println(e);
			return Response.status(500).entity(builder.toString()).build();
		}
		return Response.status(200).entity(builder.toString()).build();


	}
}
