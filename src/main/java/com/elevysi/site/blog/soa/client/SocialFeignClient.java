package com.elevysi.site.blog.soa.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elevysi.site.commons.dto.ProfileDTO;

@FeignClient("socialservice")
public interface SocialFeignClient {
	
	@RequestMapping(method= RequestMethod.GET, value="/api/profile/user/{userID}")
	ProfileDTO getProfileByUser(@PathVariable("userID") String userID);
	
//	@RequestMapping(method= RequestMethod.GET, value="/api/profile/profile/{profileID}")
//	ProfileDTO getProfileByID(@PathVariable("profileID") long profileID);
	
//	@RequestMapping(method= RequestMethod.GET, value="/api/public/profile/{profileID}")
	@RequestMapping(method= RequestMethod.GET, value="/ui/public/profile/profileID/{profileID}")
	ProfileDTO getProfileByID(@PathVariable("profileID") long profileID);

}
