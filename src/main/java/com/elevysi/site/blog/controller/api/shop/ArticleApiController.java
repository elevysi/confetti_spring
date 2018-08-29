package com.elevysi.site.blog.controller.api.shop;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elevysi.site.blog.entity.Article;
import com.elevysi.site.blog.service.ArticleService;


@RestController
@RequestMapping(value="/api/shop/")
public class ArticleApiController {
	
//	@Autowired
//	private ArticleService articleService;
//	
//	@RequestMapping(value="greet", method=RequestMethod.GET)
//	public String greet(){
//		return "Hello World";
//	}
//	
//	@RequestMapping(value = "add", method= RequestMethod.POST)
//	public String doAddArticle(
//			@RequestParam("uuid") String uuid,
//			@RequestParam("name") String name,
//			@RequestParam("description") String description,
//			@RequestParam("price") String price
//	){
//		//generate the UUID
//		Article article = new Article();
//		article.setName(name);
//		article.setDescription(description);
//		article.setUuid(uuid);
//		
////		Profile seller = articleService.getActiveProfile();
////		article.setSeller(seller);
//		
//		Article savedArticle = articleService.save(article);
//		if(savedArticle != null){
//			return "{\"success\":1, \"uuid\": "+uuid+"}";
//		}
//		
//		return "{\"success\":0}";
//	}
//	
//	@RequestMapping(value="addArticleInit", method=RequestMethod.GET)
//	public String articleAddInit(){
//		String uuid = Article.generateStaticUUID();
//		return "{\"success\":1, \"uuid\":"+uuid+"}";
//	}
}
