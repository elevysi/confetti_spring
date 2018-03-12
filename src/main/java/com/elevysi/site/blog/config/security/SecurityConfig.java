package com.elevysi.site.blog.config.security;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
 

@Configuration
@EnableWebSecurity //Need to declare this application context for it to work so the other beans knoow
@EnableGlobalMethodSecurity(securedEnabled=true, jsr250Enabled=true, prePostEnabled=true)
public class SecurityConfig {
	
	@Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;
	
    
    
    @Autowired
    MySavedUrlAuthenticationSuccessHandler mySavedUrlAuthenticationSuccessHandler;
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
 
 
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
    
    @Configuration
	public static class GloablSecurity extends WebSecurityConfigurerAdapter {
    	
    	
    	@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        	.authorizeRequests()
	        	
					.antMatchers("/restricted/**").access("hasRole('USER')")
					.antMatchers("/").permitAll()
					.antMatchers("/public/**").permitAll()
					.antMatchers("/api/**").permitAll()
					.antMatchers("/item/view/**").permitAll()
					.antMatchers("/posts/view/**").permitAll()
					.antMatchers("/plays/view/**").permitAll()
//					.antMatchers("/albums/view/**").permitAll()
					.antMatchers("/dossiers/view/**").permitAll()
					.antMatchers("/comments/public/**").permitAll()
					.antMatchers("/uploads/download/**").permitAll()
					
					.antMatchers("/login/**").permitAll()
					.antMatchers("/logout/ajax/**").permitAll()
//					.antMatchers("/register/**").permitAll()
//					.antMatchers("/auth/**").permitAll() //DO not authorize this here as it will cause a fail in the second login strategy
					
					
					.antMatchers("/dba/**").access("hasRole('ADMIN') or hasRole('DBA')")
					.antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('DBA')")
					
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/login")
	                .failureUrl("/login/failure")
	                .defaultSuccessUrl("/auth/successlogin")
	                .usernameParameter("username")
	                .passwordParameter("password")
	                .and()
	            .rememberMe()
	            	.rememberMeParameter("remember-me")
	                .tokenValiditySeconds(86400)
	                .and()
	            .csrf()
	            	.and()
	        	.exceptionHandling()
	        		.accessDeniedPage("/denied")
	                .and()
	            .logout()
	            	.logoutSuccessUrl("/logout/ajax/success")
	            	.logoutUrl("/logout")
	            	.invalidateHttpSession(true)
//    	            	.deleteCookies(cookieNamesToClear).permitAll()
	            	.and()
	           .csrf();
    	        
    	        
    	    }
    	    
    	    @Override
    	    public void configure(WebSecurity webSecurity) throws Exception
    	    {
    	        webSecurity
    	            .ignoring()
    	            .antMatchers("/resources/**")
    	            .antMatchers("/resources_1_8/**")
    	            .antMatchers("/js/**")
    	            .antMatchers("/css/**")
    	            .antMatchers("/img/**")
    	            .antMatchers("/ng/**")
    	            .antMatchers("/assets/**")
    	            .antMatchers("/resources_1_9/**")
    	            .antMatchers("/resources_1_9_5/**")
    	            .antMatchers("/thematic_1_9/**");
    	        
    	    }
	}
    
    @Configuration
    @Order(1)
    public static class RequestedAuthWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    	
    	@Autowired
        RoleUrlAuthenticationSuccessHandler roleUrlAuthenticationSuccessHandler;
    	
        protected void configure(HttpSecurity http) throws Exception {

        	
        	http
        	.antMatcher("/auth/rqstd/**")
        	.authorizeRequests()
//				.antMatchers("/auth/rqstd/login").permitAll()
				.antMatchers("/auth/rqstd/failure").permitAll()
//				.antMatchers("/auth/rqstd/**").permitAll()
				.and()
			.formLogin()
				.loginPage("/auth/rqstd/login")
				.loginProcessingUrl("/auth/rqstd/doLogIn")
                .failureUrl("/auth/rqstd/failure")
                .successHandler(roleUrlAuthenticationSuccessHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .csrf();
        }
    }
    
    @Configuration
    @Order(2)
    public static class ModalWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    	
    	@Autowired
        ModalUrlAuthenticationSuccessHandler modalUrlAuthenticationSuccessHandler;
    	
    	 protected void configure(HttpSecurity http) throws Exception {
    		 http
         	.antMatcher("/modal/**")
         	.authorizeRequests()
 				.antMatchers("/modal/login").permitAll()
 				.antMatchers("/modal/**").authenticated()
 				.and()
 			.formLogin()
 				.loginPage("/modal/login")
 				.loginProcessingUrl("/modal/doLogIn")
                 .failureUrl("/auth/modal/failure")
                 .successHandler(modalUrlAuthenticationSuccessHandler)
                 .usernameParameter("username")
                 .passwordParameter("password")
                 .and()
             .csrf();
    	 }
    }
    
    
//    @Configuration
//    @Order(3)
//    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    	
//    	@Autowired
//        ModalUrlAuthenticationSuccessHandler modalUrlAuthenticationSuccessHandler;
    	
//    	 protected void configure(HttpSecurity http) throws Exception {
//    		 http
//         	.antMatcher("/api/**")
//         	.authorizeRequests()
// 				.antMatchers("/auth/rqstd/login").permitAll()
// 				.antMatchers("/auth/rqstd/failure").permitAll()
// 				.antMatchers("/auth/rqstd/**").permitAll()
// 				.and()
// 			.formLogin()
// 				.loginPage("//auth/rqstd/login")
// 				.loginProcessingUrl("/auth/rqstd/doLogIn")
//                 .failureUrl("/auth/rqstd/failure")
//                 .successHandler(modalUrlAuthenticationSuccessHandler)
//                 .usernameParameter("username")
//                 .passwordParameter("password")
//                 .and()
//             .csrf();
//    	 }
//    }
    
    
    
	
	
}