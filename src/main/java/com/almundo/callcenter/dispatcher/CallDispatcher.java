package com.almundo.callcenter.dispatcher;

import java.util.List;

import com.almundo.callcenter.handler.CallHandler;
import com.almundo.callcenter.handler.Response;
import com.almundo.callcenter.model.Call;


/**
 * @author Wilson Salamanca
 *Clase encargada de atender las llamadas
 */
public class CallDispatcher implements Runnable{

	private CallHandler callHandler;
	private Call call;	
		
	public CallDispatcher(Call call, CallHandler callHandler) {
		this.call = call;
		this.callHandler = callHandler;
	}

	@Override
	public void run() {
		try {
			Response response = null;
			System.out.println("init----------");
			response = this.callHandler.dispatchCall(call);
			int callDuration = ((int)(Math.random() * 5) + 5);
			call.setDuration((double) callDuration);
			System.out.println(call.getId()+" "+call.getDuration());
			Thread.sleep(callDuration * 1000);
			this.callHandler.setAvailable(true);
			if(response != null){
				System.out.println(response.getRespose());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
