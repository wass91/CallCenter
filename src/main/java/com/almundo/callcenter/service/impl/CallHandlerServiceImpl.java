package com.almundo.callcenter.service.impl;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.enums.EmployeeTypeEnum;
import com.almundo.callcenter.handler.CallHandler;
import com.almundo.callcenter.service.CallCenterBuilderService;
import com.almundo.callcenter.service.CallHandlerService;

@Service
public class CallHandlerServiceImpl implements CallHandlerService{
	
	@Autowired
	private CallCenterBuilderService callCenterBuilderService;

	@Override
	public List<CallHandler> loadEmployees(String type, List<CallHandler> nextHandlers) {
		List<CallHandler> callHandlers = new ArrayList<>();
		int cant = EmployeeTypeEnum.TYPE_OPERATOR.getName().equals(type)?4:3;
		for (int i = 0; i < cant; i++) {
			callHandlers.add(callCenterBuilderService.buildCallHandler(type+i,
					true, nextHandlers, type));
		}
		return callHandlers;
	}  
	


}
