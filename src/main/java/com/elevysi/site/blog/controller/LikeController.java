package com.elevysi.site.blog.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elevysi.site.blog.entity.Like;
import com.elevysi.site.blog.service.LikeService;
import com.elevysi.site.commons.pojo.ActiveUser;
import com.elevysi.site.commons.pojo.ReturnValue;

@Controller
@RequestMapping(value="/likes/")
public class LikeController extends AbstractController{
	
	@Autowired
	private LikeService likeService;
	
	@RequestMapping(value="{itemType}/{itemId}", method= RequestMethod.POST)
	public  @ResponseBody ReturnValue likeItem(@ModelAttribute("like")Like like, @PathVariable("itemId")Integer itemId, @PathVariable("itemType")String itemType){
		
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(0);
		returnValue.setMessage("Failed to like the item!");
		
		//Can only like who is logged in
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)){
			ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
			
			if(activeUser != null){
//				Profile activeProfile = activeUser.getActiveProfile();
//				like.setLikeOwner(activeProfile);
//				
//				//Check if this profile has not liked the post yet
//				boolean hasProfileLiked =  likeService.isAlreadyLiked(like);
//				
//				if(! hasProfileLiked){
//					
//					Like savedLiked = likeService.saveLike(like);
//					if(savedLiked != null){
//						returnValue.setCode(1);
//						returnValue.setMessage("Successfully liked the item!");
//					}
//					
//				}else{
//					returnValue.setCode(2);
//					returnValue.setMessage("You have already liked this item!");
//				}
			}
		}
		
		return returnValue;
		
	}
	
	
	@RequestMapping(value="itemLikes/{itemType}/{itemId}", method= RequestMethod.GET)
	public  String itemLikes(Model model, @PathVariable("itemId")Integer itemId, @PathVariable("itemType")String itemType){
		
		//Find all likes for this Post
		List<Like> itemLikes = likeService.itemLikes(itemId, itemType);
		model.addAttribute("itemLikes", itemLikes);
		
		return "itemLikesModal";
		
	}

}
