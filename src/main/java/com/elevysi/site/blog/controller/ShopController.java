package com.elevysi.site.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/shop")
public class ShopController {
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model){
		
		
		return "shopHome";
		
	}

}
