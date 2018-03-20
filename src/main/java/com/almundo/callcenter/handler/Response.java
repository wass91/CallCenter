package com.almundo.callcenter.handler;

public class Response {

	private CallHandler callHandler;
	private String respose;
	
	public Response(CallHandler callHandler, String respose) {
		super();
		this.callHandler = callHandler;
		this.respose = respose;
	}
	public CallHandler getCallHandler() {
		return callHandler;
	}
	public void setCallHandler(CallHandler callHandler) {
		this.callHandler = callHandler;
	}
	public String getRespose() {
		return respose;
	}
	public void setRespose(String respose) {
		this.respose = respose;
	}
}
