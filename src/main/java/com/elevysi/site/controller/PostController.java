package com.elevysi.site.controller;


import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Category;
import com.elevysi.site.entity.Dossier;
import com.elevysi.site.entity.Dossier_;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.PostStatus;
import com.elevysi.site.entity.Post_;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Upload;
import com.elevysi.site.entity.User;
import com.elevysi.site.pojo.LatestPost;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.pojo.SessionMessage;
import com.elevysi.site.security.ActiveUser;
import com.elevysi.site.service.CategoryService;
import com.elevysi.site.service.DossierService;
import com.elevysi.site.service.PostService;
import com.elevysi.site.service.PostStatusService;
import com.elevysi.site.service.UserService;


@Controller
@RequestMapping(value="/posts")
public class PostController extends AbstractController{
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostStatusService postStatusService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private DossierService dossierService;
	
	
	private final static  int NO_LATEST_OWN_POSTS = 2;
	
	
	
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "categories", new CustomCollectionEditor(Set.class)
          {
            @Override
            protected Object convertElement(Object element)
            {
                Integer id = null;

                if(element instanceof String && !((String)element).equals("")){
                    //From the JSP 'element' will be a String
                    try{
                        id = Integer.parseInt((String) element);
                    }
                    catch (NumberFormatException e) {
//                        System.out.println("Element was " + ((String) element));
                        e.printStackTrace();
                    }
                }
                else if(element instanceof Integer) {
                    //From the database 'element' will be an Integer
                    id = (Integer) element;
                }

                return id != null ? categoryService.findOne(id) : null;
            }
          });
    }
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model){
		
		List<Post> posts = postService.findAllPosts();
		
		model.addAttribute("posts", posts);
		
		return "indexposts";
	}
	
	@RequestMapping(value = "/indexpublic", method = RequestMethod.GET)
	public String indexpublic(Model model, @RequestParam(defaultValue="1", required=false, value="page")Integer pageNo){
//		Page<Post> postPage = postService.findPaginatedAllPostsWithOwner(pageNo, NO_INDEX_ITEMS, SORT_FIELD, SORT_DIRECTION);
//		List<Post> posts = postPage.getContent();
		List<Post> posts = postService.findAllPosts2();
		
		model.addAttribute("posts", posts);
		return "indexpublicposts";
	}
	
	@RequestMapping(value = "add", method= RequestMethod.GET)
	public String addPost(Model model){
		
		/**
		 * Check if some of the posts are in draft mode
		 */
		
		Post newPost = new Post();
		String uuid = newPost.generateUUID();
		newPost.setUuid(uuid);
		
		/**
		 * Find the Post Categories
		 */
		List<Category> foundCategories = categoryService.findAll();
		HashMap<Integer, String> categories = new HashMap<Integer, String>();
		for (Category category : foundCategories) {
			categories.put(category.getId(), category.getName());
		}
		
		com.elevysi.site.pojo.Page dossiersPage = dossierService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_DOSSIERS, Dossier_.created, SortDirection.DESC);		
		List<Dossier> dossiers = dossierService.getDossiers(dossiersPage);
		
		model.addAttribute("post", newPost);
		model.addAttribute("dossiers", dossiers);
		model.addAttribute("categories", categories);
		return "addpost";
	}
	
	
	
	
	
	
	@RequestMapping(value = "addModal", method= RequestMethod.GET)
	public String addModalPost(Model model){
		
		model.addAttribute("post", new Post());
		return "addpost";
	}
	
	
	@RequestMapping(value = "add", method= RequestMethod.POST)
	public String doAddPost(Model model, @Valid @ModelAttribute("post") Post post, BindingResult result, Principal principal, @RequestParam("action") String action){		
		
		PostStatus postStatus;
		if(action.equals("draft")){
			postStatus = postStatusService.findDraftPostStatus();
			
		}else if(action.equals("publish")){
			postStatus = postStatusService.findPublishedPostStatus();
		}else if(action.equals("findToBePublishedPostStatus")){
			postStatus = postStatusService.findToBePublishedPostStatus();
		}else{
			
			/**
			 * Cancel Button
			 */
			
			return "redirect:/";
		}
		
		post.setPostStatus(postStatus);
		postService.savePost(post);
		return "redirect:/profile";
	}
	
	@RequestMapping(value = "addSimple", method= RequestMethod.POST)
	public String doAddSimple(Model model, @Valid @ModelAttribute("post") Post post, BindingResult result, RedirectAttributes redirectAttributes,
			 @RequestParam("action") String action){
		if(result.hasErrors()){
			return "addpost";
		}
		
		PostStatus postStatus;
		if(action.equals("draft")){
			postStatus = postStatusService.findDraftPostStatus();
			
		}else if(action.equals("publish")){
			postStatus = postStatusService.findPublishedPostStatus();
		}else if(action.equals("findToBePublishedPostStatus")){
			postStatus = postStatusService.findToBePublishedPostStatus();
		}else{
			
			/**
			 * Cancel Button
			 */
			
			return "redirect:/";
		}
		
		post.setPostStatus(postStatus);
		
		/**
		 * Record the post to the profile Editing it
		 */
		
		
		Profile owningProfile = postService.getActiveProfile();
		post.setProfile(owningProfile);
		
		if(post.getDossier().getId() == null){
			post.setDossier(null);
		}
		
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
		Post post = postService.getPost(id);
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
				Profile postOwner = post.getProfile();
				com.elevysi.site.pojo.Page page = postService.buildOffsetPage(FIRST_PAGE, NO_LATEST_OWN_POSTS, Post_.created, SortDirection.DESC);
//				List<Post> profileLatestPosts = postService.findLatestPostsForProfile(postOwner, post, 0, NO_LATEST_OWN_POSTS, SORT_FIELD, SORT_DIRECTION);
				List<Post> profileLatestPosts = postService.getLatestPostsForProfile(postOwner, post, page);
				
				/**
				 * Can edit post
				 */
				Profile activeProfile = postService.getActiveProfile();
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
		Post post = postService.getPost(id);
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
				Profile postOwner = post.getProfile();
				Profile activeProfile = postService.getActiveProfile();
				boolean canEditPost = postService.canEditPost(postOwner, activeProfile);
				
				if(canEditPost){
					
					model.addAttribute("post", post);
					/**
					 * Find the last two posts from the same author
					 * Must not be the one in viewing
					 */
					
					/**
					 * Find the Post Categories
					 */
					List<Category> foundCategories = categoryService.findAll();
					HashMap<Integer, String> categories = new HashMap<Integer, String>();
					for (Category category : foundCategories) {
						categories.put(category.getId(), category.getName());
					}
					
					model.addAttribute("categories", categories);
					model.addAttribute("canEditPost", canEditPost);
					model.addAttribute("sessionMessage", sessionMessage);
					
					com.elevysi.site.pojo.Page dossiersPage = dossierService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_ITEMS, Dossier_.created, SortDirection.DESC);		
					List<Dossier> dossiers = dossierService.getDossiers(dossiersPage);
					model.addAttribute("dossiers", dossiers);
					
					
					
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
	public String doEdit(@PathVariable("id")Integer postID,Model model, @Valid @ModelAttribute("post")Post post, BindingResult result, RedirectAttributes redirectAttributes){
		
		List<Category> foundCategories = categoryService.findAll();
		HashMap<Integer, String> categories = new HashMap<Integer, String>();
		for (Category category : foundCategories) {
			categories.put(category.getId(), category.getName());
		}
		
		model.addAttribute("categories", categories);
		com.elevysi.site.pojo.Page dossiersPage = dossierService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_ITEMS, Dossier_.created, SortDirection.DESC);		
		List<Dossier> dossiers = dossierService.getDossiers(dossiersPage);
		model.addAttribute("dossiers", dossiers);
		
		if(result.hasErrors()){
			return "editpost";
		}
		
		SessionMessage dangerMsg = new SessionMessage("The post was not found");
		dangerMsg.setDangerClass();
		
		Integer postedID = post.getId();
		if(postedID == null){
			dangerMsg.setMsgText("Could not find the post!");
			redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
			return "redirect:/";
		}else{
			if(postedID != postID){
				dangerMsg.setMsgText("Please verify the posted data!");
				redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
				return "redirect:/posts/view/"+postID+"/";
			}
		}
		
		Post dbPost = postService.getPost(post.getId());
		Profile postOwner = dbPost.getProfile();
		Profile activeProfile = postService.getActiveProfile();
		
		if(! postService.canEditPost(postOwner, activeProfile)){
			dangerMsg.setMsgText("You do not have the right to execute this action.");
			redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
			return "redirect:/posts/view/"+postID+"/"+dbPost.getPublication().getFriendlyUrl();
		}else{
						
			dbPost.setTitle(post.getTitle());
			dbPost.setDescription(post.getDescription());
			dbPost.setCategories(post.getCategories());
			dbPost.setContent(post.getContent());
			
			if(post.getDossier().getId() != null){
				dbPost.setDossier(post.getDossier());
			}else{
				dbPost.setDossier(null);
			}
			
			Post savedPost = postService.saveEditedPost(dbPost);
			Post reloadPost = postService.getPost(savedPost.getId());
			
			SessionMessage successMsg = new SessionMessage("The post was successfully edited.");
			successMsg.setSuccessClass();
			redirectAttributes.addFlashAttribute("sessionMessage",successMsg);
			
			return "redirect:/posts/view/"+postID+"/"+reloadPost.getPublication().getFriendlyUrl();
			
		}
		
	}
	
	@RequestMapping(value="delete/{id}", method=RequestMethod.POST)
	public String doDeletePost(@PathVariable("id")Integer id, Model model, RedirectAttributes redirectAttributes){
		SessionMessage sessionMessage;
		
		Post post = postService.getPost(id);
		if(post != null){
			postService.deletePost(post);
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
