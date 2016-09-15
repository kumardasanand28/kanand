package com.stateful;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class UserStateFullBeanTest
 */
@Stateless
@LocalBean
public class UserStateFullBeanTest implements UserStateFullBeanTestRemote {

    /**
     * Default constructor. 
     */
    public UserStateFullBeanTest() {
    	nameMap =  new HashMap<>(); 
    }
    
    
    private Map<String,String> nameMap;

	public Map<String, String> getNameMap() {
		return nameMap;
	}

	public void setNameMap(Map<String, String> nameMap) {
		this.nameMap = nameMap;
	}

}
