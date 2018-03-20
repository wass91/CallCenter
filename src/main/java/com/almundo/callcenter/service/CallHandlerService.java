package com.almundo.callcenter.service;

import java.util.List;

import com.almundo.callcenter.handler.CallHandler;

public interface CallHandlerService {
	public static final String JSON_PROPERTY_NAME = "name";
	public static final String JSON_PROPERTY_TYPE = "type";
	public static final Boolean EMPLOYEE_AVAILABLE = true;
	
	public List<CallHandler> loadEmployees(String type, List<CallHandler> nextHandlers);
}
