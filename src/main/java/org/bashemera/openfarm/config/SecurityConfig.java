package org.bashemera.openfarm.config;

import org.bashemera.openfarm.service.security.OpenFarmUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	OpenFarmAuthenticationSuccessHandler openFarmAuthenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/dashboard/**").hasAuthority("ADMIN")
		.anyRequest().authenticated().
		and()
		.csrf().disable()
		.formLogin()
		.successHandler(openFarmAuthenticationSuccessHandler)
		//.loginPage().permitAll()
		.and()
		.logout().permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    UserDetailsService userDetailsService = mongoUserDetails();
	    auth
	        .userDetailsService(userDetailsService)
	        .passwordEncoder(bCryptPasswordEncoder);

	}
	
	@Bean
	public UserDetailsService mongoUserDetails() {
	    return new OpenFarmUserDetailsService();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	        .ignoring()
	        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
//	protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//            .anyRequest()
//            .permitAll()
//            .and().csrf().disable();
//    }
}