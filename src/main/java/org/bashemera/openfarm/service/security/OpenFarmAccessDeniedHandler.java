package org.bashemera.openfarm.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bashemera.openfarm.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class OpenFarmAccessDeniedHandler implements AccessDeniedHandler {
	
	@Autowired
	LoggerService loggerService;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			loggerService.warn("User: " + auth.getName() + " attempted to access the protected URL: "+ request.getRequestURI(), OpenFarmAccessDeniedHandler.class);
			
			response.sendRedirect(request.getContextPath() + "/system/access_denied");
		}
		
	}

}
