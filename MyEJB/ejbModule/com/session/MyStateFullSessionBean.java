package com.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class MyStateFullSessionBean
 */
@Stateful
@LocalBean
public class MyStateFullSessionBean  implements MyFirstSessionBeanRemote{
	
	private List<String> list = new ArrayList<>();

    /**
     * Default constructor. 
     */
    public MyStateFullSessionBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void add(String name){
    	this.list.add(name);
    }
    
    public List<String> get(){
    	return list;
    	
    }

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return "test: MyStateFullSessionBean";
	}

}
