package com.cdi.stateless.applicationscope;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cdi.bean.applicationscope.SoupApScope;

@Stateless
public class WaiterApScope {

	@Inject
	private SoupApScope soup;

	@EJB
	private ChefApScope chef;

	public String orderSoup(String name){
		soup.setName(name);
		return chef.prepareSoup().getName();
	}
	
	
	 public String orderWhatTheOtherGuyHad() {
		 System.out.println("orderWhatTheOtherGuyHad Called");
	        String name = soup.getName();
	        return name;
	    }

}
