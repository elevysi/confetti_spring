package com.elevysi.site.blog.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Category;
import com.elevysi.site.blog.entity.Comment;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.blog.pojo.Page;
import com.elevysi.site.blog.pojo.PostHighlight;
import com.elevysi.site.blog.pojo.SessionMessage;
import com.elevysi.site.blog.pojo.Page.SortDirection;
import com.elevysi.site.blog.service.AlbumService;
import com.elevysi.site.blog.service.DossierService;
import com.elevysi.site.blog.service.PlayService;
import com.elevysi.site.blog.service.PostService;
import com.elevysi.site.blog.service.PublicationService;
import com.elevysi.site.blog.entity.Post_;
import com.elevysi.site.blog.entity.Publication_;
import com.elevysi.site.blog.entity.Dossier_;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends AbstractController{
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PlayService playService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private DossierService dossierService;
	
	@Autowired
	private PublicationService publicationService;
	
	
	@Value("${relativePathToDefaultAvatar}")
	private String relativePathToDefaultAvatar;
	
	@Value("${fullPathToDefaultAvatar}")
	private String fullPathToDefaultAvatar;
	
	private static final int NO_FEATURED = 4;
	private static final int NO_SLIDER_POSTS= 3;
	private static final int NO_RESUME_CHARS = 50;
	private static final int NO_FIND_POSTS = 20;
	private static final int NO_FIND_COMMENTS = 10;
	
	private static final int NO_FEATURED_PLAYS_VIDEO = 8;
	private static final int NO_FEATURED_PLAYS_AUDIO = 3;
	
	
	@RequestMapping(value="/showroom", method = RequestMethod.GET)
	public String showroom(Model model){
		
		return "showroom";
	}
	
	@RequestMapping(value = {"/", "/index.html"}, method = RequestMethod.GET)
	public String homeBlog(
			@ModelAttribute("sessionMessage") 
			SessionMessage sessionMessage, 
			@RequestParam(value = "message", required = false)String message, 
			@RequestParam(value = "messageType", required = false)String messageType,
			Locale locale,
			Model model){
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		Page publicationPage = publicationService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_ITEMS, Publication_.modified, SortDirection.DESC);
		List<Publication> foundFeaturedPublications = publicationService.getFeaturedPublications(publicationPage);
		List<Publication> featuredPublications = new ArrayList<Publication>();
		
		for(Publication publication: foundFeaturedPublications){
			Post post = publication.getPost();
			Play play = publication.getPlay();
			Album album = publication.getAlbum();
			if(post != null){
				publication.setPost(postService.getPost(post.getId()));
				
			}else if(play != null){
				publication.setPlay(playService.getPlay(play.getId()));
			}else if(album != null){
				publication.setAlbum(albumService.findById(album.getId()));
			}
			
			featuredPublications.add(publication);
		}
		
		List<PostHighlight> lastPostsHighlight = new ArrayList<PostHighlight>();
		List<PostHighlight> sliderPosts = new ArrayList<PostHighlight>();
				
		
		Page page = postService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_ITEMS, Post_.created, SortDirection.DESC);
		List<Post> posts = postService.getAllPosts(page);
		
		
		List<Play> videoPlays = playService.findLatestFeaturedPlaysByType(1, 1, NO_FEATURED_PLAYS_VIDEO, SORT_FIELD, SORT_DIRECTION);
		List<Dossier> dossiers = dossierService.getDossiers(dossierService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_DOSSIERS, Dossier_.created, SortDirection.DESC));		
		for (Post post : posts) {
			
			if(sliderPosts.size() >= NO_SLIDER_POSTS) break;
			
			String imageKey = "";
			
			Set<Upload> postUploads = post.getUploads();
			if(postUploads != null && postUploads.size() > 0){
				/**
				 * Only images to be shown should be considered
				 */
				Iterator<Upload> it = postUploads.iterator();
				while(it.hasNext()){
					Upload nextUpload = it.next();
					if(nextUpload.isDisplay()){
						imageKey = nextUpload.getKeyIdentification();
						break;
					}
				}
			}			
						
			String postCat = null;
			Set<Category> postCategories = post.getCategories();
			
			if(postCategories != null){
				Iterator<Category> iterator = postCategories.iterator();
				while (iterator.hasNext()) {
					Category firstCat = iterator.next();
					postCat = firstCat.getName();
					
					break;
				}
				
			}
			
			
			PostHighlight posted = new PostHighlight(
					post.getId(),
					post.getTitle(),
					imageKey,
					new SimpleDateFormat("EEE, d MMMMM yyyy HH:mm").format(post.getCreated()), 
					post.getProfile().getName(),
					postService.truncate(post.getContent(), post.getContent().length() > NO_RESUME_CHARS? NO_RESUME_CHARS : post.getContent().length()-1),
					postCat,
					post.getPublication().getFriendlyUrl()
					);
			if(imageKey != ""){
				sliderPosts.add(posted);
			}
			
			if(lastPostsHighlight.size() < NO_FEATURED){
				lastPostsHighlight.add(posted);
			}
			
		}
		
		
		
		model.addAttribute("featuredPublications", featuredPublications);
		model.addAttribute("lastPostsHighlight", lastPostsHighlight);
		model.addAttribute("sliderPosts", sliderPosts);
		model.addAttribute("videoPlays", videoPlays);
		model.addAttribute("dossiers", dossiers);
		String currentDate = new SimpleDateFormat("EEE, d MMMMM yyyy").format(new Date());
		model.addAttribute("currentDate", currentDate);
		model.addAttribute("sessionMessage", sessionMessage);
		
		return "homeBlog";
	}
	
}
