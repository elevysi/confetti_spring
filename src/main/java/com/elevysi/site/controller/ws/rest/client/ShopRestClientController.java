package com.elevysi.site.controller.ws.rest.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.elevysi.site.entity.Article;

import javassist.NotFoundException;

@Controller
@RequestMapping("/consumer/api")
public class ShopRestClientController {
	
	
//	public Article getArticle() throws NotFoundException{
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<Article> response = restTemplate.getForEntity("http://localhost:8080/api/article/1", Article.class);
//		
//		if(response.getStatusCode() != HttpStatus.OK) throw new NotFoundException("");
//		
//		return response.getBody();
//	}
	
	@RequestMapping("/article/{id}")
	public ResponseEntity<Object> getArticle(@PathVariable("id")String id) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> response = restTemplate.getForEntity("http://localhost:8000/api/article/"+id, Object.class);
		
		if(response.getStatusCode() != HttpStatus.OK) return new ResponseEntity<Object>(response.getBody(), HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<Object>(response.getBody(), HttpStatus.OK);
	}
	
	@RequestMapping("/doArticle")
	public ResponseEntity<Object> postArticle() {
		RestTemplate restTemplate = new RestTemplate();
		Article article = new Article();
		article.setName("Bodak");
		article.setDescription("Yellow");
		article.setPrice(new Double(2018));
		
		ResponseEntity<Object> response = restTemplate.postForEntity("http://localhost:8000/api/article/", article, Object.class);
		
		if(response.getStatusCode() != HttpStatus.OK) return new ResponseEntity<Object>(response.getBody(), HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<Object>(response.getBody(), HttpStatus.OK);
	}
}
