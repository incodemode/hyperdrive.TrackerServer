package incodemode.hyperdrive.tracker.restserver.controllers;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import incodemode.hyperdrive.tracker.restserver.units.ExecutionResult;
import incodemode.hyperdrive.tracker.server.ClientIp;
import incodemode.hyperdrive.tracker.server.ServletRequestGetter;
@RestController
public class ProxyCheckController {
	    
	    @RequestMapping("/proxy/check")
	    public ExecutionResult<String> check(){
	    	HttpServletRequest servletRequest = (HttpServletRequest) ServletRequestGetter.getServletRequest();
	    			
	    	String ipAddress =  ClientIp.getClientIpAddress(servletRequest);
	    	ExecutionResult<String> executionResult = new ExecutionResult<String>(true, ipAddress, false, "null");
	    	return executionResult;
	    }
}
