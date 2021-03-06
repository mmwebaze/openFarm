package org.bashemera.openfarm.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OpenFarmAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	        HttpServletResponse response, Authentication authentication)
	        throws IOException, ServletException {
	    //set our response to OK status
	    response.setStatus(HttpServletResponse.SC_OK);

	    for (GrantedAuthority auth : authentication.getAuthorities()) {
	    	System.out.println(auth.getAuthority());
	        if ("ADMIN".equals(auth.getAuthority())) {
	            response.sendRedirect("/dashboard");
	            break;
	        }
	        else if ("MANAGER".equals(auth.getAuthority())) {
	        	System.out.println("Redirect to dashboard: MANAGER");
	        	response.sendRedirect("/dashboard");
	        	break;
	        }
			else if ("DATA".equals(auth.getAuthority())) {
				System.out.println("Redirect to dashboard: DATA");
			  response.sendRedirect("/dashboard");
			  break;
			} 
	        else {
	        	throw new IllegalStateException();
	        }
	    }
	}
}
