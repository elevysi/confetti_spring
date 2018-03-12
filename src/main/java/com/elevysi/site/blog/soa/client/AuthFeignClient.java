package com.elevysi.site.blog.soa.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elevysi.site.blog.soa.client.config.AuthSOAClientConfiguration;
import com.elevysi.site.blog.soa.client.entity.dto.JWTTokenDTO;
import com.elevysi.site.blog.soa.client.entity.dto.OauthGrantDTO;


@FeignClient(name="authservice", configuration=AuthSOAClientConfiguration.class)
public interface AuthFeignClient {
	
//	@RequestMapping(method= RequestMethod.POST, value="/oauth/token")
//	JWTTokenDTO getToken(OauthGrantDTO oauthGrantDTO);
	
	@RequestMapping(method= RequestMethod.GET, value="/user")
	Map<String, Object> getUser();
	
}
