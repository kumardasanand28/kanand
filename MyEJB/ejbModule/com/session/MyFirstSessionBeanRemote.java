package com.session;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface MyFirstSessionBeanRemote {

	String test();
	void add(String name);
	List<String> get();

}
