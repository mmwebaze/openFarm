package org.bashemera.openfarm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.bashemera.openfarm.model.Config;
import org.bashemera.openfarm.service.ConfigService;
import org.bashemera.openfarm.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
//@Order(2)
public class InstallationStatusFilter implements Filter {
	
	@Autowired
	private LoggerService loggerService;
	
	@Autowired
	private ConfigService configService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse res = (HttpServletResponse) response;
		
		loggerService.warn("This application ***", InstallationStatusFilter.class);
		
		
		Config config = configService.getConfig();
		
		if (config == null) {
			loggerService.warn("This application requires setup and installation", InstallationStatusFilter.class);
			res.sendRedirect("/install");
			//return;
		}
		else
			chain.doFilter(request, response);
	}

}
