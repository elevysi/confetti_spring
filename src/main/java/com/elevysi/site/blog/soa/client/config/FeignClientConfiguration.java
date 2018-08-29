package com.elevysi.site.blog.soa.client.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import feign.RequestInterceptor;

//https://stackoverflow.com/questions/34677470/spring-feignclient-with-oauth2feignrequestinterceptor-not-working

@Configuration
public class FeignClientConfiguration {
	
	@Value("${security.oauth2.client.userAuthorizationUri}")
	private String authorizeUrl;

	@Value("${security.oauth2.client.accessTokenUri}")
	private String accessTokenUri;

	@Value("${security.oauth2.client.clientId}")
	private String clientId;
	
	@Value("${security.oauth2.client.clientSecret}")
	private String clientSecret;
	
    @Value("${security.oauth2.client.scope}")
    private String scope;


	// See https://github.com/spring-cloud/spring-cloud-netflix/issues/675
//	@Bean
//	public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext){
//	    return new OAuth2FeignRequestInterceptor(oauth2ClientContext, resource());
//	}
//
//	@Bean
//	protected OAuth2ProtectedResourceDetails resource() {
////	    AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
////	    ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//	    ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
//	    resource.setAccessTokenUri(accessTokenUri);
////	    resource.setUserAuthorizationUri(authorizeUrl);
//	    resource.setClientId(clientId);
//	    resource.setClientSecret("secret");
//	    resource.setScope(Arrays.asList(scope));
////	    resource.setUsername("elevysi");
////	    resource.setPassword("Fields2017");
//	    return resource;
//	}
	
	@Bean
    public OAuth2FeignRequestInterceptor oAuth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
        return new OAuth2FeignRequestInterceptor(oauth2ClientContext,details);
    }

}
