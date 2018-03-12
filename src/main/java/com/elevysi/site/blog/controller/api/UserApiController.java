package com.elevysi.site.blog.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elevysi.site.blog.entity.User;

@RestController
@RequestMapping(value="/api/user/")
public class UserApiController {

	@RequestMapping(value="authenticate", method=RequestMethod.POST)
	public User auhtenticateUser(){
		return null;
	}
}
