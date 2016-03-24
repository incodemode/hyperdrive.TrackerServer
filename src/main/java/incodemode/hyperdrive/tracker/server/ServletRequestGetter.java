package incodemode.hyperdrive.tracker.server;

import javax.servlet.ServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletRequestGetter {
	public static ServletRequest getServletRequest(){
		ServletRequest servletRequest = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return servletRequest;
	}
}
