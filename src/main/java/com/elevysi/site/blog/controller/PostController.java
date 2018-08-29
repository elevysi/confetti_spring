package com.elevysi.site.blog.controller;


import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.blog.entity.Category;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.PublicationStatus;
import com.elevysi.site.blog.entity.PublicationType;
import com.elevysi.site.blog.service.PostService;
import com.elevysi.site.blog.service.PublicationTypeService;
import com.elevysi.site.blog.soa.client.AuthFeignClient;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.dto.UserDTO;
import com.elevysi.site.commons.pojo.SessionMessage;
import com.elevysi.site.commons.pojo.Page.SortDirection;
import com.elevysi.site.blog.entity.Post_;
import com.elevysi.site.blog.entity.Publication;


@Controller
@RequestMapping(value="/posts")
public class PostController extends AbstractController{
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PublicationTypeService publicationTypeService;
	
	@Autowired
	AuthFeignClient authFeignClient;
	
	
	private final static  int NO_LATEST_OWN_POSTS = 2;
	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model){
		
		List<Post> posts = postService.findAllPosts();
		
		model.addAttribute("posts", posts);
		
		return "indexposts";
	}
	
	
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String add(Model model, @ModelAttribute("sessionMessage")SessionMessage sessionMessage){
		
		UserDTO user = authFeignClient.getUserByUsername("elevysi");
		
		/**
		 * Check if some of the posts are in draft mode
		 */
		
		Post newPost = new Post();
		String uuid = newPost.generateUUID();
		newPost.setUuid(uuid);
		
		Publication publication = new Publication();
		newPost.setPublication(publication);
		
		/**
		 * Find the Categories and Dossiers
		 */
		List<Category> categories = categoryService.findAll();
		List<Dossier> dossiers = dossierService.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("dossiers", dossiers);
		
		model.addAttribute("post", newPost);
		model.addAttribute("publication", publication);
		
		return "addpost";
	}
	
	
	
	
	@RequestMapping(value = "add", method= RequestMethod.POST)
	public String doAdd(Model model, @Valid @ModelAttribute("post") Post post, BindingResult result, RedirectAttributes redirectAttributes,
			 @RequestParam("action") String action){
		
		if(result.hasErrors()){
			
			List<Category> categories = categoryService.findAll();
			List<Dossier> dossiers = dossierService.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("dossiers", dossiers);
			
			SessionMessage dangerMsg = new SessionMessage();
			dangerMsg.setDangerClass();
			
			dangerMsg.setMsgText("Please address the errors before saving.");
			model.addAttribute("sessionMessage", dangerMsg);
			
			return "addpost";
		}
		
		PublicationStatus postStatus;
		if(action.equals("draft")){
			postStatus = poublicationStatusService.findDraftPostStatus();
			
		}else if(action.equals("publish")){
			postStatus = poublicationStatusService.findPublishedPostStatus();
		}else if(action.equals("findToBePublishedPostStatus")){
			postStatus = poublicationStatusService.findToBePublishedPostStatus();
		}else{
			
			/**
			 * Cancel Button
			 */
			
			return "redirect:/";
		}
		
		/**
		 * Record the post to the profile Editing it
		 */
		
		ProfileDTO owningProfile = postService.getActiveProfile();
		post.setProfile(owningProfile);
		post.setProfileID(owningProfile.getId());
		
		post.getPublication().setPublicationStatus(postStatus);
		post.getPublication().setProfileName(owningProfile.getName());
		post.getPublication().setProfileID(owningProfile.getId());
		PublicationType type = publicationTypeService.findByID(new Integer(1));
		post.getPublication().setType(type);
		
		Post savedPost = postService.savePost(post);
		
		SessionMessage sessionMessage = new SessionMessage("The post was successfully saved and published");
		sessionMessage.setSuccessClass();
		redirectAttributes.addFlashAttribute("sessionMessage",sessionMessage);
		
		return "redirect:/posts/view/"+savedPost.getId()+"/";
		
	}
	
	@RequestMapping(value={"view/{id}/*", "view/{id}"})
	public String view(@PathVariable("id")Integer id, Model model, @ModelAttribute("sessionMessage")SessionMessage sessionMessage, RedirectAttributes redirectAttributes){
		SessionMessage dangerMsg = new SessionMessage("The post was not found");
		dangerMsg.setDangerClass();
		Post post = postService.findByID(id);
		
		if(post == null){
			
			redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
			return "redirect:/";
		}else{
			if(! postService.canViewPost()){
				dangerMsg.setMsgText("You do not have the rights to view this post");
				redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
				return "redirect:/";
			}else{
				model.addAttribute("post", post);
				model.addAttribute("pageTitle", post.getTitle());
				model.addAttribute("pageDescription", post.getDescription());
				/**
				 * Find the last two posts from the same author
				 * Must not be the one in viewing
				 */
				ProfileDTO postOwner = post.getProfile();
				com.elevysi.site.commons.pojo.Page page = postService.buildOffsetPage(FIRST_PAGE, NO_LATEST_OWN_POSTS, Post_.created, SortDirection.DESC);
				List<Post> profileLatestPosts = postService.getLatestPostsForProfile(postOwner, post, page);
				
				/**
				 * Can edit post
				 */
				ProfileDTO activeProfile = postService.getActiveProfile();
				boolean canEditPost = postService.canEditPost(postOwner, activeProfile);
				model.addAttribute("canEditPost", canEditPost);
				model.addAttribute("profileLatestPosts", profileLatestPosts);	
				model.addAttribute("sessionMessage", sessionMessage);
				return "viewpost";
			}
		}
		
	}
	
	
	@RequestMapping(value={"edit/{id}/*", "edit/{id}"}, method=RequestMethod.GET)
	public String edit(@PathVariable("id")Integer id, Model model, @ModelAttribute("sessionMessage")SessionMessage sessionMessage, RedirectAttributes redirectAttributes){
		SessionMessage dangerMsg = new SessionMessage("The post was not found");
		dangerMsg.setDangerClass();
		Post post = postService.findByID(id);
		if(post == null){
			
			redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
			return "redirect:/";
		}else{
			if(! postService.canViewPost()){
				dangerMsg.setMsgText("You do not have the rights to view this post");
				redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
				return "redirect:/";
			}else{
				
				/**
				 * Can edit post
				 */
				ProfileDTO postOwner = post.getProfile();
				ProfileDTO activeProfile = postService.getActiveProfile();
				boolean canEditPost = postService.canEditPost(postOwner, activeProfile);
				
				if(canEditPost){
					
					model.addAttribute("post", post);
					model.addAttribute("publication", post.getPublication());
					
					
					post.getPublication().setDossiers(new HashSet<Dossier>()); //Because failed to bind on the form
					
					List<Category> categories = categoryService.findAll();
					List<Dossier> dossiers = dossierService.findAll();
					
					model.addAttribute("categories", categories);
					model.addAttribute("dossiers", dossiers);
					
					model.addAttribute("canEditPost", canEditPost);
					model.addAttribute("sessionMessage", sessionMessage);
					
					
					return "editpost";
					
				}else{
					dangerMsg.setMsgText("You do not have the rights to execute this action");
					redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
					return "redirect:/";
				}
				
				
			}
		}
	}
	
	@RequestMapping(value="edit/{id}", method=RequestMethod.POST)
	public String doEdit(@PathVariable("id")Integer postID, Model model, @Valid @ModelAttribute("post")Post post, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam("action") String action){
		
		SessionMessage dangerMsg = new SessionMessage();
		dangerMsg.setDangerClass();
		
		if(result.hasErrors()){
			
			List<Category> categories = categoryService.findAll();
			List<Dossier> dossiers = dossierService.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("dossiers", dossiers);
			
			dangerMsg.setMsgText("Please address the errors before saving.");
			model.addAttribute("sessionMessage", dangerMsg);
			return "editpost";
		}
		
		dangerMsg.setMsgText("The post was not found");
		
		Integer postedID = post.getId();
		if(postedID == null){
			dangerMsg.setMsgText("Could not find the post!");
			redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
			return "redirect:/";
		}
		
		Post dbPost = postService.findByID(post.getId());
		ProfileDTO postOwner = dbPost.getProfile();
		ProfileDTO activeProfile = postService.getActiveProfile();
		
		if(! postService.canEditPost(postOwner, activeProfile)){
			dangerMsg.setMsgText("You do not have the right to execute this action.");
			redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
			return "redirect:/posts/view/"+postID+"/"+dbPost.getPublication().getFriendlyUrl();
		}else{
			
			PublicationStatus postStatus;
			if(action.equals("draft")){
				postStatus = poublicationStatusService.findDraftPostStatus();
				
			}else if(action.equals("publish")){
				postStatus = poublicationStatusService.findPublishedPostStatus();
			}else if(action.equals("findToBePublishedPostStatus")){
				postStatus = poublicationStatusService.findToBePublishedPostStatus();
			}else{
				
				/**
				 * Cancel Button
				 */
				
				return "redirect:/posts/view/"+postID+"/"+post.getPublication().getFriendlyUrl();
			}
						
			dbPost.setTitle(post.getTitle());
			dbPost.setDescription(post.getDescription());
			dbPost.getPublication().setCategories(post.getPublication().getCategories());
			dbPost.getPublication().setDossiers(post.getPublication().getDossiers());
			dbPost.setContent(post.getContent());
			dbPost.getPublication().setPublicationStatus(postStatus);
			
			
			Post savedPost = postService.saveEditedPost(dbPost);
			
			SessionMessage successMsg = new SessionMessage("The post was successfully edited.");
			successMsg.setSuccessClass();
			redirectAttributes.addFlashAttribute("sessionMessage",successMsg);
			
			return "redirect:/posts/view/"+postID+"/"+savedPost.getPublication().getFriendlyUrl();
			
		}
		
	}
	
	@RequestMapping(value="delete/{id}", method=RequestMethod.POST)
	public String doDeletePost(@PathVariable("id")Integer id, Model model, RedirectAttributes redirectAttributes){
		SessionMessage sessionMessage;
		
		Post post = postService.findByID(id);
		if(post != null){
			postService.delete(post);
			sessionMessage = new SessionMessage("The post was successfully deleted.");
			sessionMessage.setSuccessClass();
		}else{
			sessionMessage = new SessionMessage("Could not find the post.");
			sessionMessage.setDangerClass();
		}
		
		redirectAttributes.addFlashAttribute("sessionMessage",sessionMessage);
		
		return "redirect:/public/posts";
	}
	
}
