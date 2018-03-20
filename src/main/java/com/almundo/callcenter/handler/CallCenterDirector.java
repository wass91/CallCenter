package com.almundo.callcenter.handler;

import java.util.List;

import com.almundo.callcenter.model.Call;

/**
 * @author Wilson Salamanca
 *
 */
public class CallCenterDirector extends CallHandler{
	
	public CallCenterDirector(String name, boolean isAvailable, List<CallHandler> callHandlers) {
		super(name, isAvailable, callHandlers);
	}

	@Override
	public synchronized Response dispatchCall(Call call) {
		if(this.isAvailable()){
			System.out.println(this.getClass().getName()+": "+this.getName()+" dipatches "+call.getCustomerName());
			this.isAvailable = false;
			return new Response(this, this.getClass().getName()+": "+this.getName()+" free");
		}
		System.out.println("All employes are busy");
		return new Response(null, "");
	}
}
