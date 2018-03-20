package com.almundo.callcenter.service;

import java.util.List;

import com.almundo.callcenter.handler.CallHandler;

/**
 * @author Wilson Salamanca
 *
 */
public interface CallCenterBuilderService {
	
	public CallHandler buildCallHandler(String name, boolean isAvailable, 
			List<CallHandler> callHandlers, String type);
	
	
}
