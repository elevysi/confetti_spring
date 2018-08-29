package com.elevysi.site.blog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elevysi.site.blog.soa.client.AuthFeignClient;
import com.elevysi.site.blog.soa.client.ShopFeignClient;
import com.elevysi.site.blog.soa.client.entity.dto.JWTTokenDTO;
import com.elevysi.site.blog.soa.client.entity.dto.OauthGrantDTO;
import com.elevysi.site.commons.pojo.Article;

@Controller
//@RequestMapping("/trials")
public class TrialController extends AbstractController {
	
	@Autowired
	AuthFeignClient authFeignClient;
	
	@Autowired
	ShopFeignClient shopFeignClient;
	
	@RequestMapping(value="/public/getToken", method=RequestMethod.GET)
	public ResponseEntity<Object> getToken(){
		
		OauthGrantDTO ouGrantDTO = new OauthGrantDTO("password", "read", "elha88", "Fields2017");
		
		Object token = authFeignClient.getToken("password", "read", "elha88", "Fields2017");
		HttpStatus status;
		if(token != null)
			status = HttpStatus.CREATED;
		else	status = HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<Object>(token, status);
	}
	
	@RequestMapping(value="/public/article/{id}", method=RequestMethod.GET)
	public @ResponseBody Article getArticle(@PathVariable("id")String id){
		Article article = shopFeignClient.getArticle(id);
		return article;
	}
	
	@RequestMapping(value="/public/loggedUser", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getUser(){
		Map<String, Object> user = authFeignClient.getUser();
		return user;
	}
}
