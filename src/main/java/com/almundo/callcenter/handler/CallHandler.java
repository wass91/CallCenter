package com.almundo.callcenter.handler;

import java.util.List;

import com.almundo.callcenter.model.Call;

/**
 * Clase de la cual heredan todos los empleados
 * @author Wilson Salamanca
 *
 */
public abstract class CallHandler{
	
	public static boolean OK_RESPONSE = true;
	public static boolean NOK_RESPONSE = false;
	protected String name;
	protected List<CallHandler> callHandlers;
	protected boolean isAvailable;
	
	/**
	 * @param name
	 * @param isAvailable
	 */
	public CallHandler(String name, boolean isAvailable, List<CallHandler> callHandlers) {
		this.name = name;
		this.isAvailable = isAvailable;
		this.callHandlers = callHandlers;
	}

	/**
	 * Método abstracto cuyo comportamiento cambia de acuerdo al empleado que lo implemente
	 * @param call
	 * @return boolean
	 */
	public abstract Response dispatchCall(Call call);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CallHandler> getCallHandlers() {
		return callHandlers;
	}

	public void setCallHandlers(List<CallHandler> callHandlers) {
		this.callHandlers = callHandlers;
	}

	public synchronized boolean isAvailable() {
		return isAvailable;
	}

	public synchronized void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	/**
	 * Se selecciona el siguiente empleado disponible para atender una llamada
	 * @return CallHandler
	 */
	public synchronized CallHandler selectNextHandler(){
		CallHandler callHandler = null;
		if(this.getCallHandlers() != null && !this.getCallHandlers().isEmpty()){
			callHandler = this.getCallHandlers().stream()
					.filter(x -> x.isAvailable)
					.findAny()
					.orElse(null);
			callHandler = callHandler == null? this.getCallHandlers().get(0) : callHandler;
		}
		return callHandler;
	}

	@Override
	public String toString() {
		return "CallHandler [name=" + name + 
				", callHandlers=" + callHandlers + 
				", isAvailable=" + isAvailable + "]";
	}
}
