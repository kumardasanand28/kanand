package com.cdi.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

//this means that an instance will be created only once for every request and will be shared by all the beans injecting it.
@RequestScoped
public class Soup {

	private String name = "Soup of the day";

	public String getName() {
		System.out.println(this.hashCode());
		return name;
	}
	
	 @PostConstruct
	    public void afterCreate() {
	        System.out.println("Soup created");
	    }

	public void setName(String name){
		System.out.println(this.hashCode());
		System.out.println(name);
		this.name = name;
	}

}
