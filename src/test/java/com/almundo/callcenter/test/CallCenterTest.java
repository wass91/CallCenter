package com.almundo.callcenter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;

import com.almundo.callcenter.dispatcher.Dispatcher;
import com.almundo.callcenter.enums.EmployeeTypeEnum;
import com.almundo.callcenter.handler.CallCenterDirector;
import com.almundo.callcenter.handler.CallHandler;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.service.CallHandlerService;

@RunWith(MockitoJUnitRunner.class)
public class CallCenterTest {
	
	@Mock
	private CallHandlerService callHandlerService  ;
	
	@Mock
	private List<CallHandler> operators;
	
	@Mock
	private List<CallHandler> supervisors;
	
	@Mock
	private TaskExecutor executor;
	
	@Mock
	private List<CallHandler> directors;
	
	@Mock
	private Queue<Call> queueCalls;
	
	@InjectMocks
	@Qualifier("Dispatcher")
	private Dispatcher dispatcher = new Dispatcher();
	
	
	@Test
	public void test(){
		
		List<CallHandler> value = new ArrayList<>();
		
		when(callHandlerService.loadEmployees(EmployeeTypeEnum.TYPE_DIRECTOR.getName(),
				null)).thenReturn(value);
		
		//prueba para verificar las llamadas 
		dispatcher.initCalls();
		//metodo de junity para validar 
		//Verifica que se crean las llamadas
		assertNotNull(dispatcher.getQueueCalls());
		//Verifica que se crean 10
		assertEquals(10, dispatcher.getQueueCalls().size());;
		
		dispatcher.run();
		
	}
	

}
