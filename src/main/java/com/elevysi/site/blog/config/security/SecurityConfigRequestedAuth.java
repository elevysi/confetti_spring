package com.elevysi.site.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(2)
public class SecurityConfigRequestedAuth extends WebSecurityConfigurerAdapter{
	
	@Autowired
    RoleUrlAuthenticationSuccessHandler roleUrlAuthenticationSuccessHandler;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {

    	
    	http
    	.antMatcher("/auth/rqstd/**")
    	.authorizeRequests()
//			.antMatchers("/auth/rqstd/login").permitAll()
			.antMatchers("/auth/rqstd/failure").permitAll()
//			.antMatchers("/auth/rqstd/**").permitAll()
		.and()
		.formLogin()
			.loginPage("/auth/rqstd/login")
			;
    }

}
