package com.stateful;

import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface UserStateFullBeanTestRemote {
	
	public Map<String, String> getNameMap();

}
