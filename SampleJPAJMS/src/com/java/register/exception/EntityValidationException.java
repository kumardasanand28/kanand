package com.java.register.exception;

import java.util.HashMap;
import java.util.Map;

public class EntityValidationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String, Object> errorMessageMap = new HashMap<String, Object>();


	public EntityValidationException(String exception){
		super(exception);
		errorMessageMap.put("defaultError", exception);
	}

	public EntityValidationException(String exception, String field){
		errorMessageMap.put(field, exception);
	}

	public static Map<String, Object> getErrorMessageMap() {
		return errorMessageMap;
	}

	public static void setErrorMessageMap(Map<String, Object> errorMessageMap) {
		EntityValidationException.errorMessageMap = errorMessageMap;
	}

}
