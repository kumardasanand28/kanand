package com.cdi.stateless;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cdi.bean.Soup;

@Stateless
public class Waiter {

	@Inject
	private Soup soup;

	@EJB
	private Chef chef;

	public String orderSoup(String name){
		soup.setName(name);
		return chef.prepareSoup().getName();
	}


	public String orderWhatTheOtherGuyHad() {
		String name = soup.getName();
		return name;
	}

}
