package com.elevysi.site.blog.soa.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elevysi.site.blog.soa.client.config.FeignClientConfiguration;
import com.elevysi.site.commons.dto.UserDTO;

//@FeignClient(name="authserviceop", configuration = FeignClientConfiguration.class)
public interface AuthResourceFeignClient {
	
	@RequestMapping(method= RequestMethod.GET, value="/api/public/user/{username}")
	UserDTO getUserByUsername(@PathVariable("username") String username);

}
