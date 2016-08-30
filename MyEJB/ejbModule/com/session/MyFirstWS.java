package com.session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;  

@Path("webservice")
public class MyFirstWS {
	
	@GET
	public String hello(){
		return "hello";
	}

}
