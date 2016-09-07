package com.cdi.bean.applicationscope;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

//this means that an instance will be created only once for every application and will be shared by all the beans injecting it.
@ApplicationScoped
public class SoupApScope {

	private String name = "Soup of the day";

	public String getName() {
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
