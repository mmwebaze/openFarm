package org.bashemera.openfarm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bashemera.openfarm.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class InstallationInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private SetupService setupService;
	
	@Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		
		
		if (!setupService.isSetup()) {
			System.out.println("Prehandler: Setup service: "+request.getRequestURI());
			
			response.sendRedirect("/install");
		}
		
		return true;
	}
	
	@Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) {
		
	}
}
