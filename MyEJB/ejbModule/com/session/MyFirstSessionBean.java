package com.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class MyFirstSessionBean
 */
@Stateless
@LocalBean
public class MyFirstSessionBean   implements MyFirstSessionBeanRemote{

	private List<String> list = new ArrayList<>();
	/**
	 * Default constructor. 
	 */
	public MyFirstSessionBean() {
		// TODO Auto-generated constructor stub
	}

	public String test() {
		return "test: MyFirstSessionBean";

	}

	public void add(String name){
		this.list.add(name);
	}

	public List<String> get(){
		return list;

	}

}
