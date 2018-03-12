package com.elevysi.site.blog.controller;

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

import com.elevysi.site.blog.config.security.ActiveUser;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.entity.Share;
import com.elevysi.site.blog.pojo.ReturnValue;
import com.elevysi.site.blog.service.ShareService;

@Controller
@RequestMapping(value="/shares/")
public class ShareController {
	
	@Autowired
	private ShareService shareService;
	
	@RequestMapping(value="{itemType}/{itemId}")
	public @ResponseBody ReturnValue shareItem(@ModelAttribute("share")Share share, @PathVariable("itemId")Integer itemId, @PathVariable("itemType")String itemType){
		
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(0);
		returnValue.setMessage("Failed to like the item!");
		
		
		//Can only like who is logged in
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)){
			ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
			
			if(activeUser != null){
				Profile activeProfile = activeUser.getActiveProfile();
				share.setShareOwner(activeProfile);
				
				//Check if this profile has not liked the post yet
				boolean hasProfileShared =  shareService.hasAlreadyShared(share);
				
				if(! hasProfileShared){
					
					Share savedShare = shareService.saveShare(share);
					if(savedShare != null){
						returnValue.setCode(1);
						returnValue.setMessage("Successfully shared the item!");
					}
					
				}else{
					returnValue.setCode(2);
					returnValue.setMessage("You have already liked this item!");
				}
			}
		}
		
		return returnValue;
	}
	
	@RequestMapping(value="itemShares/{itemType}/{itemId}", method= RequestMethod.GET)
	public  String itemShares(Model model, @PathVariable("itemId")Integer itemId, @PathVariable("itemType")String itemType){
		
		//Find all likes for this Post
		Set<Share> itemShares = shareService.itemFullShares(itemId, itemType);
		model.addAttribute("itemShares", itemShares);
		
		return "itemLikesModal";
		
	}
}
