package com.elevysi.site.blog.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elevysi.site.blog.config.security.ActiveUser;
import com.elevysi.site.blog.entity.Comment;
import com.elevysi.site.blog.entity.Like;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.entity.Share;
import com.elevysi.site.blog.pojo.ReturnValue;
import com.elevysi.site.blog.service.CommentService;
import com.elevysi.site.blog.service.LikeService;
import com.elevysi.site.blog.service.ShareService;

@Controller
@RequestMapping(value = "/comments", method= RequestMethod.GET)
public class CommentContrroller extends AbstractController{
	
	@Autowired
	private  CommentService commentService;
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private ShareService shareService;
	
	
	private final static int NO_POST_COMMENTS = 20;
	
	@RequestMapping(value = "public/section/{objectType}/{objectId}")
	public String commentSection(@PathVariable("objectId")Integer objectId, @PathVariable("objectType")String objectType, Model model, @RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber){
		
		/**
		 * Find all previous comments
		 */
		long noComments = 0;
		Integer noLikes = 0;
		Integer noShares = 0;
		
		Set<Like> postLikes = new HashSet<Like>();
		Set<Share> postShares = new HashSet<Share>();
		Set<Comment> allItemComments = new HashSet<Comment>();
		
		
		Page<Comment> itemCommentsPage;
		List<Comment> itemComments = new ArrayList<Comment>();
		
		if(objectType.equals("posts")){
			itemCommentsPage = commentService.findPaginatedPostComments(objectId, pageNumber, NO_POST_COMMENTS, "id", SORT_DIRECTION);
			allItemComments = commentService.itemComments(objectId, "posts");
			noComments = allItemComments.size();
			
			postLikes = likeService.itemLikes(objectId, "posts");
			noLikes = postLikes.size();
			
			postShares = shareService.itemShares(objectId, "posts");
			noShares = postShares.size();
			
			itemComments = itemCommentsPage.getContent();
		}else if(objectType.equals("plays")){
			itemCommentsPage = commentService.findPaginatedPlayComments(objectId, pageNumber, NO_POST_COMMENTS, "id", SORT_DIRECTION);
			
			allItemComments = commentService.itemComments(objectId, "plays");
			noComments = allItemComments.size();
			
			postLikes = likeService.itemLikes(objectId, "plays");
			noLikes = postLikes.size();
			
			postShares = shareService.itemShares(objectId, "plays");
			noShares = postShares.size();
			
			itemComments = itemCommentsPage.getContent();
		}
		
		
		
		Comment comment = new Comment();
		Like like = new Like();
		Share share = new Share();
		
		
		comment.setItemId(objectId);
		comment.setItemType(objectType);
		
		/**
		 * Cannot use the basic approach because it has a number 
		 */
		
		model.addAttribute("comment", comment);
		model.addAttribute("noComments", noComments);
		model.addAttribute("itemComments", itemComments);
		
		model.addAttribute("like", like);
		model.addAttribute("noLikes", noLikes);
		model.addAttribute("postLikes", postLikes);
		
		model.addAttribute("share", share);
		model.addAttribute("noShares", noShares);
		model.addAttribute("postShares", postShares);
		
		model.addAttribute("objectId", objectId);
		model.addAttribute("objectType", objectType);
		
		return "postCommentSection";
		
	}
	
	
	@RequestMapping(value = "public/section/doSaveComment/{objectType}/{post_id}", method= RequestMethod.POST)
	public @ResponseBody ReturnValue doSavePostComment(@ModelAttribute("comment")Comment comment, BindingResult result){
		
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(0);
		returnValue.setMessage("Failed to save the comment");
		
		if(result.hasErrors()){
			return returnValue;
		}
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
			
			if(activeUser != null){
				Profile activeProfile = activeUser.getActiveProfile();
				commentService.setProfile(comment, activeProfile, true);
				comment.setProfile(activeProfile);
			}
		}
		
		Comment originalCmt = comment.getOriginalComment();
		if(originalCmt != null){
			Comment originalComment = commentService.findOne(originalCmt.getId());
			comment.setOriginalComment(originalComment);
		}
		
		
		Comment savedComment = commentService.save(comment);
		if(savedComment != null){
			returnValue.setCode(1);
			returnValue.setMessage("The comment was successfully added.");
		}
		

		return returnValue;
		
	}

}
