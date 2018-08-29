package com.elevysi.site.blog.config.security;
 
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;

import com.elevysi.site.blog.soa.client.AuthFeignClient;
import com.elevysi.site.blog.soa.client.SocialFeignClient;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.dto.UserDTO;
import com.elevysi.site.commons.pojo.ActiveUser;

/**
 * Spring @EnableResourceServer vs @EnableOAuth2Sso
https://stackoverflow.com/questions/42938782/spring-enableresourceserver-vs-enableoauth2sso

 * These annotations mark your services with different OAuth 2.0 roles.

@EnableResourceServer annotation means that your service (in terms of OAuth 2.0 - Resource Server) expects an access token in order to precess the request. Access token should be obtained from Authorization Server by OAuth 2.0 Client before calling the Resource Server.

@EnableOAuth2Sso: marks your service as an OAuth 2.0 Client. This means that it will be responsible for redirecting Resource Owner (end user) to the Authorization Server where the user has to enter their credentials. After it's done the user is redirected back to the Client with Authorization Code (don't confuse with Access Code). Then the Client takes the Authorization Code and exchanges it for an Access Token by calling Authorization Server. Only after that, the Client can make a call to a Resource Server with Access Token.

Also, if you take a look into source code of @EnableOAuth2Sso annotation you will see two interesting things:

@EnableOAuth2Client. This is where your service becomes OAuth 2.0 Client. It makes it possible to forward access token (after it has been exchanged for Authorization Code) to downstream services in case you are calling those services via OAuth2RestTemplate.
@EnableConfigurationProperties(OAuth2SsoProperties.class). OAuth2SsoProperties has only one property String loginPath which is /login by default. This will intercept browser requests to the /login by OAuth2ClientAuthenticationProcessingFilter and will redirect the user to the Authorization Server.
Should I be using @EnableOAuth2Sso?

It depends:

If you want your API gateway to be an OAuth 2.0 client which interacts with the browser using Authorization Code Flow or Resource Owner Password Credentials Flow, then the answer is yes, you probably should. I said probably as I am not sure if @EnableOAuth2Sso supports Resource Owner Password Credentials Flow very well. Anyway, I would suggest you moving with Authorization Code Flow unless you have really (like really!) good reasons not to do so. BTW, when using Authorization Code Flow you may want to mark your downstream microservices as  @EnableResourceServer. Then the API Gateway will be OAuth 2.0 Client, and your microservices will be OAuth 2.0 Resource Servers which seems logical to me.
If you do not need interaction with the browser (e.g. Client Credentials Flow) or you have SPA that makes use of Implicit Flow then you should use @EnableResourceServer, meaning that it will accept requests with valid Access Token only.
 * @author elvis
 *
 */

//@EnableWebSecurity //Need to declare this application context for it to work so the other beans knoow
//@EnableGlobalMethodSecurity(securedEnabled=true, jsr250Enabled=true, prePostEnabled=true)
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    MySavedUrlAuthenticationSuccessHandler mySavedUrlAuthenticationSuccessHandler;
	
	@Autowired
	SocialFeignClient socialFeignClient;
	
	@Autowired
	AuthFeignClient authFeignClient;
	
	@Autowired
	private ModelMapper modelMapper;
        
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        
	        .requestMatchers()
    		.antMatchers("/login/**", "/logout/**", "/ui/**", "/public/**", "/publication/**", "/restricted/**", "/uploads/**",
    				"/item/**", "/posts/**", "/plays/**", "/dossiers/**", "/comments/**", "/dba/**", "/admin/**")
    		.and()
	        	.authorizeRequests()
	        		.antMatchers("/").permitAll()
					.antMatchers("/restricted/**").access("hasRole('USER')")
					.antMatchers("/public/**").permitAll()
					.antMatchers("/item/view/**").permitAll()
					.antMatchers("/publication/view/**").permitAll()
					.antMatchers("/posts/view/**").permitAll()
					.antMatchers("/plays/view/**").permitAll()
					.antMatchers("/dossiers/view/**").permitAll()
					.antMatchers("/comments/public/**").permitAll()
					.antMatchers("/uploads/download/**").permitAll()
					
					.antMatchers("/login/**").permitAll()
					.antMatchers("/logout/ajax/**").permitAll()
					
					.antMatchers("/dba/**").access("hasRole('ADMIN') or hasRole('DBA')")
					.antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('DBA')")
					
					.anyRequest().authenticated()
					.and()
					.formLogin()
						.loginPage("/login")
//					.and()
//					 .logout()
//		            	.logoutSuccessUrl("/logout/ajax/success")
//		            	.logoutUrl("/logout")
//		            	.invalidateHttpSession(true)
				;
	        
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
	            .antMatchers("/resources_2_5/**")
	            .antMatchers("/thematic_1_9/**");
	        
	    }
//	    https://stackoverflow.com/questions/30545067/spring-security-oauth2-default-login-success-url
	
    
	@Configuration
    @Order(4)
    public static class ModalWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    	
    	@Autowired
        ModalUrlAuthenticationSuccessHandler modalUrlAuthenticationSuccessHandler;
    	
    	 protected void configure(HttpSecurity http) throws Exception {
    		 http
         	.antMatcher("/modal/**")
         	.authorizeRequests()
 				.antMatchers("/modal/login").permitAll()
 				.antMatchers("/modal/**").authenticated()
 				;
    	 }
    }
	
	//http://mmkay.pl/2017/03/19/spring-boot-saving-oauth2-login-data-in-db-using-principalextractor/
	@Bean
    public PrincipalExtractor principalExtractor() {
        return map -> {
        	String principalUsername = (String) map.get("user");
        	
        	UserDTO user = authFeignClient.getUserByUsername(principalUsername);
        	
        	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        	
        	
        	//Get the user's profile
        	Object profileObject = socialFeignClient.getProfileByUser(user.getId().toString());
        	ProfileDTO profile = modelMapper.map(profileObject, ProfileDTO.class);
        	
        	 ActiveUser activeUser = new ActiveUser(
        			 user.getUsername(),
	            	 	"dummyPassword",
	            		true,
	            		true,
	            		true,
	            		true,
	            		authorities,
	            		user.getFirst_name(),
	            		null,
	            		null,
	            		null
     		);
        	 
        	 activeUser.setUserProfile(profile);
        	 activeUser.setActiveProfile(profile);
        	 activeUser.setProfiles(null);
        	 
        	return activeUser;
        };
    }
}