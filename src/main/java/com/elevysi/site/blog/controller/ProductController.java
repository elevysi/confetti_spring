package com.elevysi.site.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elevysi.site.blog.entity.Product;

@Controller
@RequestMapping(value="/shop/products")
public class ProductController {
	
	@RequestMapping(value="/add")
	public String add(Model model){
		
		Product product = new Product();
		model.addAttribute("product", product);
		return "productAdd";
	}
	
	@RequestMapping(value="/view/{id}")
	public String view(Model model, @PathVariable("id")Integer id){
		
		
		return "productView";
	}

}
