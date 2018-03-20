package com.almundo.callcenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.almundo.callcenter.enums.EmployeeTypeEnum;
import com.almundo.callcenter.handler.CallCenterDirector;
import com.almundo.callcenter.handler.CallCenterOperator;
import com.almundo.callcenter.handler.CallCenterSupervisor;
import com.almundo.callcenter.handler.CallHandler;
import com.almundo.callcenter.service.CallCenterBuilderService;

/**
 * @author Wilson Salamanca
 *
 */
@Service
public class CallCenterBuilderServiceImpl implements CallCenterBuilderService{

	@Override
	public CallHandler buildCallHandler(String name, boolean isAvailable, 
			List<CallHandler> callHandlers, String type) {
		if(EmployeeTypeEnum.TYPE_OPERATOR.getName().equals(type)){
			return new CallCenterOperator(name, isAvailable, callHandlers);
		}
		if(EmployeeTypeEnum.TYPE_SUPERVISOR.getName().equals(type)){
			return new CallCenterSupervisor(name, isAvailable, callHandlers);
		}
		if(EmployeeTypeEnum.TYPE_DIRECTOR.getName().equals(type)){
			return new CallCenterDirector(name, isAvailable, callHandlers);
		}
		return null;
	}
}
