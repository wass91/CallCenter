package com.almundo.callcenter.dispatcher;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.config.AppConfig;
import com.almundo.callcenter.enums.EmployeeTypeEnum;
import com.almundo.callcenter.handler.CallHandler;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.service.CallHandlerService;

/**
 * @author Wilson Salamanca
 *
 */
@Service("Dispatcher")
public class Dispatcher implements Runnable{

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private CallHandlerService callHandlerService;
	
	private List<CallHandler> operators;
	
	private List<CallHandler> supervisors;
	
	@Autowired
	@Qualifier("threadPoolTaskExecutor")
	private TaskExecutor executor;
	
	private List<CallHandler> directors;
	
	private Queue<Call> queueCalls;
	
	
	/**
	 * Se inicia los empleados de prueba
	 */
	private void initEmployees(){
		this.directors = Collections.synchronizedList(callHandlerService.loadEmployees(EmployeeTypeEnum.TYPE_DIRECTOR.getName(),
				null));
		this.supervisors = Collections.synchronizedList(callHandlerService.loadEmployees(EmployeeTypeEnum.TYPE_SUPERVISOR.getName(),
				this.directors));
		this.operators = Collections.synchronizedList(callHandlerService.loadEmployees(EmployeeTypeEnum.TYPE_OPERATOR.getName(),
				this.supervisors));
	}
	
	/**
	 * Se inicia las llamadas de prueba
	 */
	public void initCalls(){
		this.queueCalls = new LinkedList<Call>();
		for(int i = 0; i < 10; i++){
			this.queueCalls.add(new Call(i, "Customer "+i));
		}
	}
	
	/**
	 * Se obtiene el empleado que atenderá la llamada
	 * @return CallHandler
	 */
	private synchronized CallHandler getCallHandler(){
		CallHandler callHandler = this.filterList(this.operators);
		return callHandler; 
	}
	
	/**
	 * Se filtra la lista de empleados para obtener el disponible
	 * @param callHandlers
	 * @return CallHandler
	 */
	private CallHandler filterList(List<CallHandler> callHandlers){
		if(callHandlers == null || callHandlers.isEmpty()){
			return null;
		}
		CallHandler callHandler = callHandlers.stream()
			.filter(x -> x.isAvailable())
			.findAny()
			.orElse(null);
		return callHandler == null? callHandlers.get(0).selectNextHandler():callHandler;
	}
	
	private synchronized void dispatchCall(Call call){
		CallDispatcher callDispatcher = new CallDispatcher(call,this.getCallHandler());
		executor.execute(callDispatcher);
	}
	
	@Override
	public void run() {
		logger.info("Init process");
		this.initEmployees();
		this.initCalls();
		while(!this.queueCalls.isEmpty()){
			try {
				Call call = this.queueCalls.poll();
				this.dispatchCall(call);
				Thread.sleep(AppConfig.NEXT_CALL_WAIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public List<CallHandler> getOperators() {
		return operators;
	}

	public List<CallHandler> getSupervisors() {
		return supervisors;
	}

	public List<CallHandler> getDirectors() {
		return directors;
	}

	public Queue<Call> getQueueCalls() {
		return queueCalls;
	}
	
}
