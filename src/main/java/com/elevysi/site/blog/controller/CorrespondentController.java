package com.elevysi.site.blog.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elevysi.site.blog.config.security.ActiveUser;
import com.elevysi.site.blog.entity.Correspondent;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.pojo.ListObject;
import com.elevysi.site.blog.service.ProfileService;

@Controller
@RequestMapping(value="/correspondents")
public class CorrespondentController {
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value="/friends")
	public @ResponseBody List<ListObject> allCorrespondents(){
		/**
		 * Record the post to the profile Editing it
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
		Profile activeProfile = activeUser.getActiveProfile();
		Set<Profile> profileFriends = profileService.findProfileFriends(activeProfile);
		List<ListObject> friends = new ArrayList<ListObject>();
		Iterator<Profile> iterator = profileFriends.iterator();
		while(iterator.hasNext()){
			Profile friend = iterator.next();
			friends.add(new ListObject(friend.getId(), friend.getName()));
		}
		
		return friends;
		
	}

}
