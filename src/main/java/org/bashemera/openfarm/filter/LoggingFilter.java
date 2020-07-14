package org.bashemera.openfarm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.bashemera.openfarm.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
//@Order(1)
public class LoggingFilter implements Filter {
	
	@Autowired
	private LoggerService loggerService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		//HttpServletResponse res = (HttpServletResponse) response;
		
		loggerService.info("Logging Request: "+req.getRequestURI(), LoggingFilter.class);
		chain.doFilter(request, response);
		//loggerService.info("Logging Response: "+res.getContentType(), LoggingFilter.class);
	}

}
