package com.elevysi.site.blog.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elevysi.site.blog.entity.Message;

@Controller
public class MessageController extends AbstractController{
	
	@ModelAttribute("message")
	public Message constructMsg(){
		return new Message();
	}
	
	@RequestMapping(value="/message/add", method= RequestMethod.GET)
	public String add(){
		return "newMessage";
		
	}
	
	
	@RequestMapping(value = "/message/add", method= RequestMethod.POST)
	public String doAdd(Model model, @Valid @ModelAttribute("message") Message message, BindingResult result, Principal principal){		
		
		return "redirect:/profile";
	}
	

}
