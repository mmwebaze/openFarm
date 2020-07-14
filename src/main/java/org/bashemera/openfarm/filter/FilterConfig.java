package org.bashemera.openfarm.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<InstallationStatusFilter> installationFilter() {
		System.out.println("Registering this filter");
		FilterRegistrationBean<InstallationStatusFilter> registrationBean = new FilterRegistrationBean<>();
		
		registrationBean.setFilter(new InstallationStatusFilter());

        //registrationBean.addUrlPatterns("/login");
        registrationBean.addUrlPatterns("/account/*");
        registrationBean.setOrder(2);

        return registrationBean;
		
	}
}
