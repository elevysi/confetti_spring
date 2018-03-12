package com.elevysi.site.blog.controller.ws.soap.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elevysi.site.blog.ws.soap.consumer.client.ArticleClient;

@Controller
@RequestMapping("/ws/soap")
public class ShopSOAPClientController {
	
	ArticleClient articleClient;
	
	@Autowired
	public ShopSOAPClientController(ArticleClient articleClient){
		this.articleClient= articleClient;
	}
	
//	@RequestMapping("/article/{id}")
//	public @ResponseBody com.elevysi.site.ws.shop.soap.consumer.Article getArticle(@PathVariable("id")String id){
//		
//		Long castedId = Long.parseLong(id);
//		ArticleDetailsResponse response = articleClient.callGetArticle(castedId);
//		return response.getArticle();
//	}

}
