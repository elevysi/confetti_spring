package com.elevysi.site.blog.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.blog.config.security.ActiveUser;
import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.Post;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.entity.ProfileType;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.User;
import com.elevysi.site.blog.form.FileUpload;
import com.elevysi.site.blog.pojo.OffsetPage;
import com.elevysi.site.blog.pojo.PublicationMock;
import com.elevysi.site.blog.pojo.ReturnValue;
import com.elevysi.site.blog.pojo.SessionMessage;
import com.elevysi.site.blog.pojo.Page.SortDirection;
import com.elevysi.site.blog.service.AlbumService;
import com.elevysi.site.blog.service.DossierService;
import com.elevysi.site.blog.service.PlayService;
import com.elevysi.site.blog.service.PostService;
import com.elevysi.site.blog.service.ProfileService;
import com.elevysi.site.blog.service.ProfileTypeService;
import com.elevysi.site.blog.service.PublicationService;
import com.elevysi.site.blog.service.UserService;
import com.elevysi.site.blog.validator.FileUploadValidator;
import com.elevysi.site.blog.entity.Album_;
import com.elevysi.site.blog.entity.Dossier_;
import com.elevysi.site.blog.entity.Play_;
import com.elevysi.site.blog.entity.Post_;

@Controller
public class ProfileController extends AbstractController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;
	
	
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ProfileTypeService profileTypeService;
	
	@Autowired
	private PlayService playService;
	
	@Autowired
	private DossierService dossierService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private PublicationService publicationService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	private final static  int NO_PROFILE_POSTS = 3;
	private final static  int NO_PROFILE_POSTS_PER_LOAD = 10;
	private final static int NO_ITEMS_PER_PAGE = 20;
	
	
	
	@Autowired
	private FileUploadValidator fileUploadValidator;
	
	
	
	
	@InitBinder("fileUpload")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(fileUploadValidator);
	}
	
	/**
	 * User is redirected to his User Profile account after login or if requested
	 * S/he sees the timeline, i.e. self posts and following posts sorted by creation date
	 * @param model
	 * @param sessionMessage
	 * @return
	 */
//	@RequestMapping({"/profile/", "/profile"})
//	public String profile(Model model, @ModelAttribute("sessionMessage") SessionMessage sessionMessage, @RequestParam(required = false, value="message")String msg){
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		ActiveUser activeUser = (ActiveUser)auth.getPrincipal();		
//		Profile userProfile = activeUser.getUserProfile();	
//		
//		
//		if(sessionMessage.getMsgText() == null && msg != null){
//			sessionMessage = new SessionMessage();
//			sessionMessage.setMsgText(msg);
//			sessionMessage.setSuccessClass();
//		}
//		
//		
//		model.addAttribute("userProfile", userProfile);
//		model.addAttribute("sessionMessage", sessionMessage);
//		return "profileHome";
//	}
	
	@RequestMapping({"/profile/", "/profile"})
	public String profile(Model model, 
			@ModelAttribute("sessionMessage")SessionMessage sessionMessage, 
			@RequestParam(required = false, value="message")String msg,
			@RequestParam(value="username", defaultValue="", required=false)String username
			
	){
		
		if(sessionMessage.getMsgText() == null && msg != null){
			sessionMessage = new SessionMessage();
			sessionMessage.setMsgText(msg);
			sessionMessage.setSuccessClass();
		}
		Profile actingProfile = profileService.getActiveProfile();
		Profile profile;
		if(username.equals("")){
			profile = actingProfile;
		}else{
			profile = profileService.findByName(username);
		}
		
		model.addAttribute("actingProfile", actingProfile);
		model.addAttribute("profile", profile);
		
		model.addAttribute("sessionMessage", sessionMessage);
		model.addAttribute("pageTitle", profile.getName());
		model.addAttribute("pageDescription", profile.getDescription());
		
		
		
		return "profileHome";
	}
	
	@RequestMapping({"/profile/{username}"})
	public String view(
			@PathVariable("username") String username, 
			@ModelAttribute("sessionMessage") SessionMessage sessionMessage, 
			Model model, final RedirectAttributes redirectAttributes, 
			@RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber){
		
		return "redirect:/profile?username="+username;
	}
	
	@RequestMapping(value={"/bucket/{username}"}, method=RequestMethod.POST)
	public String doBucket(
			Model model,
			Principal principal, 
			@PathVariable("username") String username,
			@RequestParam("bucketID") String bucketID,
			final RedirectAttributes redirectAttributes
	){
		/*
		 * Create a profile friend relationship
		 * Find acting profile, add the requested profile to their list of friends
		 */
		Profile activeProfile = profileService.getActiveProfile();
		//Loaded Profile with Friends
		Profile requestingProfile = profileService.findByName(activeProfile.getName());
		Profile requestedProfile = profileService.findByName(bucketID);
		if(! requestingProfile.equals(requestedProfile)) profileService.bucket(requestingProfile, requestedProfile);
		
		SessionMessage sessionMessage = new SessionMessage("Confetti "+requestedProfile.getName()+  " has been successfully added to your bucket list.");
		sessionMessage.setSuccessClass();
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		
		return "redirect:/profile?username="+username;
	}
	
	
	@RequestMapping(value="/profile/picture", method=RequestMethod.GET)
	public String profilePicture(Model model){
		
		model.addAttribute("fileUpload", new FileUpload());
		return "profilePicture";
		
	}
	
	
	@RequestMapping(value="/profile/picture", method=RequestMethod.POST)
	public String doProfilePicture(Model model, @ModelAttribute("fileUpload") FileUpload fileUpload, BindingResult result) throws Exception{
		
		fileUploadValidator.validate(fileUpload, result);
		
		if(result.hasErrors()){
			
			return "profilePicture";
			
		}else{
			
			MultipartFile image = fileUpload.getFile();
//			 try{
//				 
//				 if (!image.getContentType().equals("image/jpeg")) {
//					 throw new RuntimeException("Only JPG images are accepted");
//				 }
//				 
//			 }catch(Exception e){
//					
//			 }
			 
			 try{
				 this.saveImage("avatar", image);
			 }catch(Exception e){
				 throw e;
			 }
			
		}
		
		return "redirect:/profile/";
		
	}


	@Override
	public void setServletContext(ServletContext arg0) {
		
		
	}
	
	
	
	private void saveImage(String fileName, MultipartFile image) throws RuntimeException, IOException {
		try {
			
			String saveDirectory = this.avatarUploadPath;
			fileName = image.getOriginalFilename();
			
			byte[] bytes = image.getBytes();
            BufferedOutputStream buffStream = 
                    new BufferedOutputStream(new FileOutputStream(new File((saveDirectory + fileName))));
            buffStream.write(bytes);
            buffStream.close();
            
//            if (!image.getOriginalFilename().equals("")) {
//            	image.transferTo(new File(saveDirectory + image.getOriginalFilename()));
//            }
            
            System.out.println("Saving file: " + saveDirectory + fileName);

		} catch (IOException e) {
			throw e;
		}
	}
	
	@RequestMapping(value="/admin/profiles/", method = RequestMethod.GET)
	public String index(Model model){
		
		List<Profile> profiles = profileService.findAll();
		model.addAttribute("profiles", profiles);
		return "indexProfile";
	}
	
	
	
	@RequestMapping(value = "proSettings", method= RequestMethod.GET)
	public @ResponseBody ReturnValue doSavePostComment(){
		
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(0);
		returnValue.setMessage("Failed to save the comment");
		
		
		

		return returnValue;
		
	}
	
	@RequestMapping(value="/profile/{username}/posts")
	public String profilePosts(
			Model model, 
			@PathVariable("username") String username,
			@RequestParam(value="page", defaultValue="1", required=false)int pageIndex
	){
		Profile profile = profileService.findByName(username);
		OffsetPage page = postService.buildOffsetPage(pageIndex, DEFAULT_NO_ITEMS, Post_.created, SortDirection.DESC);
		
		List<Post> posts = new ArrayList<Post>();
		if(profile != null){
			posts = postService.getLatestPostsForProfile(profile, null, page);
		}
		
		model.addAttribute("posts", posts);
		double totalRecords = page.getTotalRecords();
		int totalPages = (int)Math.ceil(totalRecords / DEFAULT_NO_ITEMS);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", totalPages);
		
		return "indexpostsajax";
		
	}
	
	@RequestMapping(value="/profile/{username}/plays")
	public String plays(
			Model model, 
			@PathVariable("username") String username,
			@RequestParam(value="page", defaultValue="1", required=false)int pageNumber
	){
		Profile profile = profileService.findByName(username);
		OffsetPage page = playService.buildOffsetPage(pageNumber, DEFAULT_NO_ITEMS, Play_.created, SortDirection.DESC);
		List<Play> plays = new ArrayList<Play>();
		
		if(profile != null){
			plays = playService.getProfilePlays(profile, page);
		}
		
		
		model.addAttribute("plays", plays);
		double totalRecords = page.getTotalRecords();
		int totalPages = (int)Math.ceil(totalRecords / DEFAULT_NO_ITEMS);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", totalPages);
		
		return "indexplaysajax";
		
	}
	
	
	
	@RequestMapping(value="/profile/{username}/albums")
	public String albums(
			Model model,
			@PathVariable("username") String username,
			@RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber
	){
		
		Profile profile = profileService.findByName(username);
		OffsetPage page = albumService.buildOffsetPage(pageNumber, DEFAULT_NO_ITEMS, Album_.created, SortDirection.DESC);
		List<Album> albums = new ArrayList<Album>();
		
		if(profile != null){
			albums = albumService.getAlbumsForProfile(profile, page);
		}
		
		model.addAttribute("albums", albums);
		double totalRecords = page.getTotalRecords();
		int totalPages = (int)Math.ceil(totalRecords / DEFAULT_NO_ITEMS);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", totalPages);
		
		return "indexalbumsajax";
	}
	
	@RequestMapping(value="/profile/{username}/dossiers")
	public String dossiers(
			Model model,
			@PathVariable("username") String username,
			@RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber
	){
		Profile profile = profileService.findByName(username);
		OffsetPage page = dossierService.buildOffsetPage(pageNumber, DEFAULT_NO_ITEMS, Dossier_.created, SortDirection.DESC);
		List<Dossier> dossiers = new ArrayList<Dossier>();
		
		if(profile != null){
			dossiers = dossierService.getDossiersForProfile(profile, page);
		}
		
		model.addAttribute("dossiers", dossiers);
		double totalRecords = page.getTotalRecords();
		int totalPages = (int)Math.ceil(totalRecords / DEFAULT_NO_ITEMS);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", totalPages);
		
		return "indexdossiersajax";
		
	}
	
	@RequestMapping(value="/profile/{username}/settings")
	public String settings(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("sessionMessage") SessionMessage sessionMessage){
		ActiveUser activeUser = profileService.getActiveUser();		
		Profile actingProfile = activeUser.getActiveProfile();
		List<Profile> userProfiles = activeUser.getProfiles();
		List<ProfileType> profileTypes = profileTypeService.findProfileTypes();
	
		model.addAttribute("actingProfile", actingProfile);
		model.addAttribute("profile", actingProfile);
		model.addAttribute("userProfiles", userProfiles);
		
		model.addAttribute("profileTypes", profileTypes);
		model.addAttribute("profile", actingProfile);
		model.addAttribute("pageTitle", actingProfile.getName());
		model.addAttribute("pageDescription", actingProfile.getDescription());
		model.addAttribute("sessionMessage", sessionMessage);
		
		return "profileSettings";
		
	}
	
	@RequestMapping(value="/profile/{username}/messages")
	public String profileMessages(Model model, RedirectAttributes redirectAttributes, Principal principal, @RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber){
		
		Profile actingProfile = profileService.getActiveProfile();
		model.addAttribute("actingProfile", actingProfile);
		model.addAttribute("pageTitle", actingProfile.getName());
		model.addAttribute("pageDescription", actingProfile.getDescription());
		
		return "profileMessages";
		
	}
	
	
	@RequestMapping(value="bucket")
	public String bucket(Model model){
		Profile activeProfile = profileService.getActiveProfile();
		
		model.addAttribute("actingProfile", activeProfile);
		
		model.addAttribute("pageTitle", activeProfile.getName());
		model.addAttribute("pageDescription", activeProfile.getDescription());
		
		return "profilebucket";
	}
	
	@RequestMapping(value="/profile/add")
	public String add(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("sessionMessage") SessionMessage sessionMessage){
				
		Profile actingProfile = profileService.getActiveProfile();
		List<ProfileType> profileTypes = profileTypeService.findProfileTypes();
		model.addAttribute("actingProfile", actingProfile);
		model.addAttribute("profileTypes", profileTypes);
		model.addAttribute("profile", new Profile());
		
		model.addAttribute("pageTitle", actingProfile.getName());
		model.addAttribute("pageDescription", actingProfile.getDescription());
		model.addAttribute("sessionMessage", sessionMessage);
		
		return "profileAdd";
		
	}
	
	@RequestMapping(value="/profile/settings/", method=RequestMethod.POST)
	public String doSettings(Model model, @Valid @ModelAttribute("profile")Profile profile, BindingResult result, RedirectAttributes redirectAttributes,
			HttpServletRequest request){
		if(result.hasErrors()){
			
			return "redirect: /profile/profileSettings";
		}else{
			Profile dbProfile = profileService.loadFullProfile(profile.getId());
			dbProfile.setProfileType(profile.getProfileType());
			dbProfile.setName(profile.getName());
			dbProfile.setTitle(profile.getTitle());
			dbProfile.setDescription(profile.getDescription());
			
			
			/**
			 * Create a new Active User with Profile
			 */
			
			Profile savedProfile = profileService.savedEditedProfile(dbProfile);
			ActiveUser activeUser = profileService.getActiveUser();
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					activeUser.getUsername(), activeUser.getPassword());
			
			
			
			// generate session if one doesn't exist
	        
//	        userDetailsService.
//	        Authentication authenticatedUser = userDetailsService.loadUserByUsername(activeUser.getUsername())

//	        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	        
//			Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetailsService.cloneAndUpdateAuthenticatedUser(activeUser, savedProfile), null, activeUser.getAuthorities());
			
	        request.getSession();
	        token.setDetails(new WebAuthenticationDetails(request));
			
			
//			Authentication authentication = new UsernamePasswordAuthenticationToken(activeUser.getUsername(), activeUser.getPassword());
			SecurityContextHolder.getContext().setAuthentication(token);
			
			SessionMessage successMsg = new SessionMessage("The profile was successfully edited.");
			successMsg.setSuccessClass();
			redirectAttributes.addFlashAttribute("sessionMessage",successMsg);

		}
		
		
		return "redirect:/profile/";
	}
	
	@RequestMapping(value="/profile/add/", method=RequestMethod.POST)
	public String doAdd(Model model, @Valid @ModelAttribute("profile")Profile profile, BindingResult result, RedirectAttributes redirectAttributes){
		SessionMessage successMsg;
		if(result.hasErrors()){
			successMsg = new SessionMessage("Could not add the profile.");
			successMsg.setDangerClass();
			redirectAttributes.addFlashAttribute("sessionMessage",successMsg);
			return "redirect: /profile/add";
		}else{
			ActiveUser activeUser = profileService.getActiveUser();
			User dbUser = userService.findOne(activeUser.getUsername());
			profile.setUser(dbUser);
			
			Profile savedProfile = profileService.saveProfile(profile);

//			Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetailsService.addAndUpdateAuthenticatedUser(activeUser, savedProfile), null, activeUser.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(activeUser.getUsername(), activeUser.getPassword());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			successMsg = new SessionMessage("The profile was successfully edited.");
			successMsg.setSuccessClass();
			redirectAttributes.addFlashAttribute("sessionMessage",successMsg);
			
			/**
			 * Create a new Active User with Profile
			 * 
			 */
		}
		
		
		return "redirect:/profile/";
	}
	
	@RequestMapping({"/profile/pbucket/"})
	public String friends(
			Model model, 
			Principal principal
		){
		
		Profile actingProfile = profileService.getActiveProfile();
		model.addAttribute("actingProfile", actingProfile);
		
		List<Profile> friends = profileService.findProfileConnections(actingProfile);
		
		model.addAttribute("friends", friends);
		
		model.addAttribute("pageTitle", actingProfile.getName());
		model.addAttribute("pageDescription", actingProfile.getDescription());
		
		return "profileFriends";
	}
	
	/**
	 * Ajax Calls
	 */
	@RequestMapping(value="/profile/{username}/profileBucketNetworkAjax/{networkType}/")
	public String profileBucketNetworkAjax(
			Model model,
			@PathVariable("username") String username,
			@PathVariable("networkType") int networkType,
			@RequestParam(value="page", defaultValue="1", required=false)int pageIndex
	){
		
		OffsetPage page = playService.buildOffsetPage(pageIndex, DEFAULT_NO_ITEMS, Play_.created, SortDirection.DESC);
		
		List<Profile> friends = new ArrayList<Profile>();
		Profile profile = profileService.findByName(username);
		
		if(profile != null){
			
		
			switch(networkType){
				case 1: //all
					friends = profileService.findProfileConnections(profile);
				break;
				case 2: //I added them
					friends = profileService.findFollowing(profile);
				break;
				case 3: //They added me
					friends = profileService.findFollowers(profile);
				break;
				case 4: //Mutual
					friends = profileService.findMutualBucket(profile);
				break;
			}
		}
		
		model.addAttribute("profiles", friends);
		
		double totalRecords = page.getTotalRecords();
		int totalPages = (int)Math.ceil(totalRecords / DEFAULT_NO_ITEMS);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", totalPages);
		
		return "indexprofilesajax";
	}
	
	@RequestMapping(value="/profile/searchAjax")
	public @ResponseBody List<Profile> searchAjax(
			@RequestParam(value="term", required=true)String term
	){
		List<Profile> profiles = new ArrayList<Profile>();
		List<Profile> foundProfiles = profileService.searchByTerm(term);
		if(foundProfiles != null){
//			for(Publication publication: foundPublications){
//				PublicationMock publicationMock = new PublicationMock();
//				publicationMock.createMockFromPublication(publication);
//				publications.add(publicationMock);
//			}
		}
		
		
		return foundProfiles;
	}
	
}
