package org.bashemera.openfarm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageConfig implements WebMvcConfigurer {
	
	
	/*@Override
	 * public void addViewControllers(ViewControllerRegistry registry) {
	 * registry.addViewController("/signup").setViewName("signup");
	 * registry.addViewController("/login").setViewName("login");
	 * registry.addViewController("/dashboard").setViewName("dashboard");
	 * registry.addViewController("/logout").setViewName("redirect:/"); }
	 */
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return bCryptPasswordEncoder;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**", "/css/**", "/js/**")
		.addResourceLocations("classpath:/static/images/", "classpath:/static/css/", "classpath:/static/js/");
	}
}
