package com.singleton;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@LocalBean
@Singleton
public class SingletonBean{

	private HashMap cache;

	@PostConstruct
	public void initCache(){
		this.cache = new HashMap();
	}

	public Object get(String key){
		return this.cache.get(key);
	}


	public void put(String key,Object value){
		this.cache.put(key, value);
	}

}
