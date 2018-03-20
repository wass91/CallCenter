package com.almundo.callcenter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.callcenter.dispatcher.Dispatcher;
import com.almundo.callcenter.json.JSONResponse;

@RestController
public class CallCenterController {
	
	@Autowired
	@Qualifier("Dispatcher")
	private Dispatcher dispatcher;
	
	@CrossOrigin(origins = "/**")
	@RequestMapping(value = ServiceDirectory.START, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONResponse start(){
		dispatcher.run();
		return new JSONResponse("test", true);
	}
}
