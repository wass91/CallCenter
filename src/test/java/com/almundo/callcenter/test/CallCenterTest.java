package com.almundo.callcenter.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;

import com.almundo.callcenter.controller.CallCenterController;
import com.almundo.callcenter.dispatcher.Dispatcher;
import com.almundo.callcenter.handler.CallHandler;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.service.CallHandlerService;

@RunWith(MockitoJUnitRunner.class)
public class CallCenterTest {
	
	@Mock
	private CallHandlerService callHandlerService;
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
	private Dispatcher Dispatcher = new Dispatcher();
	
	
	@Test
	public void test(){
		assertEquals(null, null);
		System.out.println("asdfasdf");
	}
	

}
