package org.bashemera.openfarm.config;

import java.util.ArrayList;
import java.util.List;

import org.bashemera.openfarm.interceptor.InstallationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageConfig implements WebMvcConfigurer {
	
	@Autowired
	InstallationInterceptor installationInterceptor;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return bCryptPasswordEncoder;
	}
	
	@Bean
	public InstallationInterceptor installationInterceptor() {
		
		return new InstallationInterceptor();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**", "/css/**", "/js/**")
		.addResourceLocations("classpath:/static/images/", "classpath:/static/css/", "classpath:/static/js/");
	}
	
	@Override
    public void addInterceptors(final InterceptorRegistry registry) {
		List<String> includePatterns = new ArrayList<>();
		includePatterns.add("/");
		includePatterns.add("/account/*");
		includePatterns.add("/login");
		
		registry.addInterceptor(installationInterceptor).addPathPatterns(includePatterns);//.addPathPatterns("/");
	}
}
