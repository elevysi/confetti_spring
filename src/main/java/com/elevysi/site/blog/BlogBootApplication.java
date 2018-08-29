package com.elevysi.site.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.context.request.RequestContextListener;



//https://stackoverflow.com/questions/42938782/spring-enableresourceserver-vs-enableoauth2sso
//http://www.baeldung.com/sso-spring-security-oauth2

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@RefreshScope
public class BlogBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogBootApplication.class, args);

	}
	
	@Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
	
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	
//	@Bean
//    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
//        return new OAuth2RestTemplate(details, oauth2ClientContext);
//    }

}
