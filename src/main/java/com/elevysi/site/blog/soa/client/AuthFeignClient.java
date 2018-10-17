package com.elevysi.site.blog.soa.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.elevysi.site.blog.soa.client.config.AuthSOAClientConfiguration;
import com.elevysi.site.blog.soa.client.config.FeignClientConfiguration;
import com.elevysi.site.blog.soa.client.entity.dto.JWTTokenDTO;
import com.elevysi.site.blog.soa.client.entity.dto.OauthGrantDTO;
import com.elevysi.site.commons.dto.UserDTO;


//@FeignClient(name="authservice", configuration=AuthSOAClientConfiguration.class)
//@FeignClient(name="authservice", configuration = FeignClientConfiguration.class)
@FeignClient(name="authservice")
public interface AuthFeignClient {
	
//	@RequestMapping(method= RequestMethod.POST, value="/oauth/token")
//	Object getToken(@RequestBody OauthGrantDTO oauthGrantDTO);
	
	@RequestMapping(method= RequestMethod.POST, value="/oauth/token")
	Object getToken(
			@RequestParam(value="grant_type", required=false)String grant_type,
			@RequestParam(value="scope", required=false)String scope,
			@RequestParam(value="username", required=false)String username,
			@RequestParam(value="password", required=false)String password
	);
	
	@RequestMapping(method= RequestMethod.GET, value="/user")
	Map<String, Object> getUser();
	
	
//	@RequestMapping(method= RequestMethod.GET, value="/api/public/user/{username}")
	@RequestMapping(method= RequestMethod.GET, value="/api/user/{username}")
	UserDTO getUserByUsername(@PathVariable("username") String username);
}
