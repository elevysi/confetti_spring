package com.elevysi.site.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Album_;
import com.elevysi.site.entity.Comment;
import com.elevysi.site.entity.Comment_;
import com.elevysi.site.entity.Dossier;
import com.elevysi.site.entity.Dossier_;
import com.elevysi.site.entity.Play;
import com.elevysi.site.entity.Play_;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.Post_;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Profile_;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.entity.Publication_;
import com.elevysi.site.entity.Upload;
import com.elevysi.site.entity.User;
import com.elevysi.site.pojo.DomainObjectNode;
import com.elevysi.site.pojo.DomainObjectTree;
import com.elevysi.site.pojo.LatestPost;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.SearchResult;
import com.elevysi.site.pojo.SessionMessage;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.security.ActiveUser;
import com.elevysi.site.service.AlbumService;
import com.elevysi.site.service.CommentService;
import com.elevysi.site.service.DossierService;
import com.elevysi.site.service.PlayService;
import com.elevysi.site.service.PostService;
import com.elevysi.site.service.ProfileService;
import com.elevysi.site.service.PublicationService;
import com.elevysi.site.service.UserService;

@Controller
@RequestMapping("/public")
public class PublicController extends AbstractController{
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private DossierService dossierService;
	
	@Autowired
	private  CommentService commentService;
	
	@Autowired
	private PlayService playService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private PublicationService publicationService;
	
	private final static  int NO_LATEST_POSTS = 3;
	
	
	@RequestMapping(value="/latestPosts", method = RequestMethod.GET)
	public @ResponseBody List<LatestPost> latestPosts(){
		
		List<LatestPost> latestPosts = new ArrayList<LatestPost>();
		
		List<Post> posts = postService.findLatestPosts(0, NO_LATEST_POSTS, SORT_FIELD, SORT_DIRECTION);
		
		for (Post post : posts) {
			String imageKey = "";
			
			Set<Upload> postUploads = post.getUploads();
			if(postUploads != null && postUploads.size() > 0){
				Iterator<Upload> itr = postUploads.iterator();
				imageKey = itr.next().getKeyIdentification();
			}
			
			
			LatestPost posted = new LatestPost(
					post.getId(),
					post.getTitle(),
					imageKey,
					new SimpleDateFormat("EEE, d MMMMM yyyy HH:mm").format(post.getCreated()), 
					post.getProfile().getName(),
					post.getPublication().getFriendlyUrl()
					);
			latestPosts.add(posted);
		}
		
		return latestPosts;
	}
	
	@RequestMapping("/latestComments")
	public String latestBlogComments(Model model){

		
		com.elevysi.site.pojo.Page page = commentService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_ITEMS, Comment_.created, com.elevysi.site.pojo.Page.SortDirection.DESC);
		List<Comment> latestBlogComments = commentService.getAllComments(page);
		
		model.addAttribute("latestBlogComments", latestBlogComments);
		return "latestBlogComments";
		
	}
	
	@RequestMapping(value="/cv")
	public String cv(){
		
		return "cv";
	}
	
	@RequestMapping(value="/comingsoon")
	public String nodejs(){
		
		return "comingsoon/comingSoon";
	}
	
	@RequestMapping(value="/posts")
	public String posts(Model model, @RequestParam(value="page", defaultValue="1", required=false)int pageIndex){
		
		
		OffsetPage page = postService.buildOffsetPage(pageIndex, DEFAULT_NO_ITEMS, Post_.created, SortDirection.DESC);
		List<Post> posts = postService.getAllPosts(page);
		double totalRecords = page.getTotalRecords();
		int totalPages = (int)Math.ceil(totalRecords / DEFAULT_NO_ITEMS);
		model.addAttribute("page", page);
		model.addAttribute("posts", posts);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalRecords", totalRecords);
		
		return "publicIndexPosts";
	}
	
	@RequestMapping(value="/plays")
	public String plays(Model model, @RequestParam(value="page", defaultValue="1", required=false)int pageIndex){
		
		OffsetPage page = playService.buildOffsetPage(pageIndex, DEFAULT_NO_ITEMS, Play_.created, SortDirection.DESC);
		List<Play> plays = playService.getPlays(page);
		double totalRecords = page.getTotalRecords();
		int totalPages = (int)Math.ceil(totalRecords / DEFAULT_NO_ITEMS);
		model.addAttribute("page", page);
		model.addAttribute("plays", plays);
		model.addAttribute("totalPages", totalPages);
		
		
		return "publicIndexPlays";
		
	}
	
	@RequestMapping(value="/albums")
	public String albums(Model model, @RequestParam(value="page", defaultValue="1", required=false)int pageIndex){
		
		OffsetPage page = albumService.buildOffsetPage(pageIndex, DEFAULT_NO_ITEMS, Album_.created, SortDirection.DESC);
		List<Album> albums = albumService.getAlbums(page);
		double totalRecords = page.getTotalRecords();
		int totalPages = (int)Math.ceil(totalRecords / DEFAULT_NO_ITEMS);
		model.addAttribute("page", page);
		model.addAttribute("albums", albums);
		model.addAttribute("totalPages", totalPages);
		
		
		return "publicIndexAlbums";
		
	}
	
	@RequestMapping(value="/dossiers")
	public String dossiers(Model model, @RequestParam(value="page", defaultValue="1", required=false)int pageIndex){
		
		OffsetPage page = dossierService.buildOffsetPage(pageIndex, DEFAULT_NO_ITEMS, Dossier_.created, SortDirection.DESC);
		List<Dossier> dossiers = dossierService.getDossiers(page);
		double totalRecords = page.getTotalRecords();
		int totalPages = (int)Math.ceil(totalRecords / DEFAULT_NO_ITEMS);
		model.addAttribute("page", page);
		model.addAttribute("dossiers", dossiers);
		model.addAttribute("totalPages", totalPages);
		
		
		return "publicIndexDossiers";
		
	}
	
	@RequestMapping(value="/search/tree", method = RequestMethod.GET)
	public String searchTree(
			Model model,
			@RequestParam(name="term", defaultValue="", required=false) String searchTerm,
			final RedirectAttributes redirectAttributes
	){
		
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		
		DomainObjectTree domainObjectTree = new DomainObjectTree();
		DomainObjectNode<?> rootNode = new DomainObjectNode<Object>();
		domainObjectTree.setDomainObjectRootNode(rootNode);
		
		DomainObjectNode<Post> postRoot = new DomainObjectNode<Post>();
		DomainObjectNode<Post> playRoot = new DomainObjectNode<Post>();
		DomainObjectNode<User> userRoot = new DomainObjectNode<User>();
		DomainObjectNode<Profile> profileRoot = new DomainObjectNode<Profile>();
		
		
		
		if(! searchTerm.equals("")){
			
			List<User> usersFound = new ArrayList<User>();
			usersFound = userService.findByTerm(searchTerm);
			
			List<Post> foundPosts = new ArrayList<Post>();
			foundPosts = postService.findMatching(searchTerm);
			
			int i = 0;
			 
			for (Post post : foundPosts) {
				SearchResult searchResult = new SearchResult();
				searchResult.setIndex(post.getId());
				searchResult.setType("post");
				searchResult.setHeading(post.getTitle());
				searchResult.setDetails(post.getContent());
				searchResult.setCreated(post.getCreated());
				searchResult.setAuthor(post.getProfile());
				
				Upload avatar = null;
				if(post.getUploads().iterator().hasNext()){
					avatar = post.getUploads().iterator().next();
					searchResult.setUpload(avatar);
				}
				
				searchResult.setObject(post);
				searchResults.add(searchResult);
				postRoot.addChild(new DomainObjectNode<Post>(post, avatar));
				
				i++;
			}
			
			for(User user: usersFound){
				SearchResult searchResult = new SearchResult();
				searchResult.setIndex(user.getId());
				searchResult.setType("user");
				searchResult.setHeading(user.getFirst_name() + " " + user.getLast_name());
				searchResult.setCreated(user.getCreated());
				
				Profile userProfile = profileService.findUserProfile(user);	
				searchResult.setDetails(userProfile.getDescription());
				Upload avatar = null;
				if(userProfile.getProfilePicture().iterator().hasNext()){
					avatar = userProfile.getProfilePicture().iterator().next();
					searchResult.setUpload(avatar);
				}
				
				searchResult.setObject(user);
				searchResults.add(searchResult);
				
				userRoot.addChild(new DomainObjectNode<User>(user, avatar));
				
				i++;
				
			}
		}
		model.addAttribute("searchResults", searchResults);
		model.addAttribute("searchTerm", searchTerm);
		model.addAttribute("postRoot", postRoot);
		model.addAttribute("userRoot", userRoot);
		return "search-tree";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String doSearch(
			Model model,
			final RedirectAttributes redirectAttributes,
			@RequestParam(name="globalSearch", defaultValue="", required=false) String searchTerm
	){
		redirectAttributes.addFlashAttribute("searchTerm", searchTerm);
		return "redirect:/public/search?term="+searchTerm;
		
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String search(
			Model model,
			@RequestParam(name="term", defaultValue="", required=false) String searchTerm,
			final RedirectAttributes redirectAttributes
	){
		
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		
		if(! searchTerm.equals("")){
			
			List<User> usersFound = new ArrayList<User>();
			usersFound = userService.findByTerm(searchTerm);
			
			List<Post> foundPosts = new ArrayList<Post>();
			foundPosts = postService.findMatching(searchTerm);
			
			int i = 0;
			 
			for (Post post : foundPosts) {
				SearchResult searchResult = new SearchResult();
				searchResult.setIndex(post.getId());
				searchResult.setType("post");
				searchResult.setHeading(post.getTitle());
				searchResult.setCreated(post.getCreated());
				searchResult.setAuthor(post.getProfile());
//				String details = "";
//				String postContent = post.getContent();
//				if(postContent != null && postContent.length() > 100)
//					details = postContent.substring(0, 100);
//				else details = postContent;
				searchResult.setDetails(post.getContent());
				
				if(post.getUploads().iterator().hasNext()){
					searchResult.setUpload(post.getUploads().iterator().next());
				}
				
				
				searchResult.setObject(post);
				
				searchResults.add(searchResult);
				
				i++;
			}
//			
			for(User user: usersFound){
				SearchResult searchResult = new SearchResult();
				searchResult.setIndex(user.getId());
				searchResult.setType("user");
				searchResult.setHeading(user.getFirst_name() + " " + user.getLast_name());
				searchResult.setCreated(user.getCreated());
				
				Profile userProfile = profileService.findUserProfile(user);	
				searchResult.setDetails(userProfile.getDescription());
				if(userProfile.getProfilePicture().iterator().hasNext()){
					searchResult.setUpload(userProfile.getProfilePicture().iterator().next());
				}
				
				searchResult.setObject(user);
				searchResults.add(searchResult);
				
				
				i++;
				
			}
		}
		model.addAttribute("searchResults", searchResults);
		model.addAttribute("searchTerm", searchTerm);
		return "search-results";
	}
	
	@RequestMapping(value="/search/tree", method=RequestMethod.POST)
	public String doSearchTree(
			Model model,
			final RedirectAttributes redirectAttributes,
			@RequestParam(name="globalSearch", defaultValue="", required=false) String searchTerm
	){
		redirectAttributes.addFlashAttribute("searchTerm", searchTerm);
		return "redirect:/public/search/tree?term="+searchTerm;
		
	}
	
	
	
	@RequestMapping("/photoStream")
	public String photoStream(Model model, @RequestParam(defaultValue="1", required=false, value="page")Integer pageNumber){

		Album streamAlbum = albumService.findById(43);
		List<Upload> albumUploads = albumService.getPaginatedAlbumUploads(streamAlbum, pageNumber, 20, SORT_FIELD, SORT_DIRECTION);
		model.addAttribute("albumUploads", albumUploads);
		model.addAttribute("streamAlbum", streamAlbum);
		return "photoStreamRightBar";
		
	}
	
	@RequestMapping(value="/albums/lalife/**")
	public String lalifealbum(Model model, RedirectAttributes redirectAttributes, @RequestParam(defaultValue="1", required=false, value="page")Integer pageNumber){
		
		Album album = albumService.findOne(LA_LIFE_ALBUM_ID);
		if(album != null){
			model.addAttribute("album", album);
			model.addAttribute("pageTitle", album.getName());
			model.addAttribute("pageDescription", album.getDescription());
			
			List<Upload> albumUploads = albumService.getPaginatedAlbumUploads(album, pageNumber, NO_ALBUM_UPLOADS, SORT_FIELD, SORT_DIRECTION);
			boolean canEditAlbum = false;
			if(album.getProfileOwner().equals(albumService.getActiveProfile())) canEditAlbum = true;
			
			model.addAttribute("albumUploads", albumUploads);
			model.addAttribute("canEditAlbum", canEditAlbum);
			return "albumView";
		}else{
			SessionMessage sessionMessage = new SessionMessage("The album was successfully saved!");
			sessionMessage.setSuccessClass();
			
			redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
			return "redirect:/";
		}
	}
	
	
	@RequestMapping({"/profile/{username}"})
	public String view(@PathVariable("username") String username, @ModelAttribute("sessionMessage") SessionMessage sessionMessage, Model model, final RedirectAttributes redirectAttributes, @RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber){
		
		Profile profile = profileService.findOne(username);
		
		
		if(profile != null){
			
			
//			com.elevysi.site.pojo.Page publicationsPage = publicationService.buildOffsetPage(pageNumber, DEFAULT_NO_ITEMS, Publication_.created, SortDirection.DESC);
//			List<Publication> profilePublications = publicationService.getProfilePublications(profile, publicationsPage);
//			List<Publication> featuredPublications = new ArrayList<Publication>();
//			
//			
//			
//			for(Publication publication : profilePublications){
//				Post post = publication.getPost();
//				Play play = publication.getPlay();
//				if(post != null){
//					publication.setPost(postService.getPost(post.getId()));
//					featuredPublications.add(publication);
//					
//				}else if(play != null){
//					publication.setPlay(playService.getPlay(play.getId()));
//					featuredPublications.add(publication);
//				}
//			}
			
			Page publicationPage = publicationService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_ITEMS, Publication_.modified, SortDirection.DESC);
			List<Publication> foundFeaturedPublications = publicationService.getProfilePublications(profile, publicationPage);
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
			
			Set<Profile> friends = profileService.findProfileFriends(profile);			
			
			
			model.addAttribute("userProfile", profile);
			model.addAttribute("user", profile.getUser());
			model.addAttribute("friends", friends);
			model.addAttribute("sessionMessage", sessionMessage);
			model.addAttribute("pageTitle", profile.getName());
			model.addAttribute("pageDescription", profile.getDescription());
			model.addAttribute("featuredPublications", featuredPublications);
			
			
			return "publicViewProfile";
			
		}else{
			SessionMessage notFoundMsg = new SessionMessage("The specified profile was not found.");
			notFoundMsg.setDangerClass();
			redirectAttributes.addFlashAttribute("sessionMessage", notFoundMsg);
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value="/profiles")
	public String profiles(Model model, @RequestParam(value="page", defaultValue="1", required=false)int pageIndex){
		
		OffsetPage page = profileService.buildOffsetPage(pageIndex, DEFAULT_NO_ITEMS, Profile_.created, SortDirection.DESC);
		List<Profile> profiles = profileService.getProfiles(page);
		double totalRecords = page.getTotalRecords();
		int totalPages = (int)Math.ceil(totalRecords / DEFAULT_NO_ITEMS);
		model.addAttribute("page", page);
		model.addAttribute("profiles", profiles);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalRecords", totalRecords);
		
		return "publicIndexProfiles";
	}

}
