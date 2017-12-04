package com.elevysi.site.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Comment;
import com.elevysi.site.entity.Play;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.ProfileType;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.entity.Publication_;
import com.elevysi.site.entity.User;
import com.elevysi.site.form.FileUpload;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.pojo.ReturnValue;
import com.elevysi.site.pojo.SessionMessage;
import com.elevysi.site.repository.ProfileRepository;
import com.elevysi.site.security.ActiveUser;
import com.elevysi.site.service.AlbumService;
import com.elevysi.site.service.CustomUserDetailsService;
import com.elevysi.site.service.PlayService;
import com.elevysi.site.service.PostService;
import com.elevysi.site.service.ProfileService;
import com.elevysi.site.service.ProfileTypeService;
import com.elevysi.site.service.PublicationService;
import com.elevysi.site.service.UserService;
import com.elevysi.site.validator.FileUploadValidator;

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
			Principal principal, 
			@RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber
	){
		
		
		
		if(sessionMessage.getMsgText() == null && msg != null){
			sessionMessage = new SessionMessage();
			sessionMessage.setMsgText(msg);
			sessionMessage.setSuccessClass();
		}
		
		Profile actingProfile = profileService.getActiveProfile();
		//Load the profile specifics
		
		Profile profile = profileService.loadFullProfile(actingProfile.getId());
		
//		List<Post> profilePosts = postService.findProfilePosts(actingProfile, pageNumber, NO_PROFILE_POSTS_PER_LOAD, SORT_FIELD, SORT_DIRECTION);
		
//		model.addAttribute("profilePosts", profilePosts);
		model.addAttribute("actingProfile", profile);
		model.addAttribute("sessionMessage", sessionMessage);
		model.addAttribute("pageTitle", actingProfile.getName());
		model.addAttribute("pageDescription", actingProfile.getDescription());
		/**
		 * Find publications
		 */
		
		
		return "profileHome";
	}
	
	
	@RequestMapping({"/profile/home"})
	public String home(Model model, Principal principal){
		String username = principal.getName();
		
		User domainUser = userService.getUser(username);
		model.addAttribute("user", domainUser);
		
		return "profile";
	}
	
	@RequestMapping({"/profile/homeTab"})
	public String homeTab(Model model){
		
		
		
		return "profileHomeTab";
	}
	
	@RequestMapping({"/profile/profileTab"})
	public String profileTab(Model model, Principal principal, @RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber){
		
		Profile actingProfile = profileService.getActiveProfile();
		List<Post> profilePosts = postService.findProfilePosts(actingProfile, pageNumber, NO_PROFILE_POSTS_PER_LOAD, SORT_FIELD, SORT_DIRECTION);
		
		Page<Publication> profilePublicationsPage = publicationService.findProfilePublications(actingProfile, pageNumber, NO_PROFILE_POSTS_PER_LOAD, SORT_FIELD, SORT_DIRECTION);
		List<Publication> profilePublications = profilePublicationsPage.getContent();
		
		model.addAttribute("profilePosts", profilePosts);
		model.addAttribute("actingProfile", actingProfile);
		model.addAttribute("profilePublications", profilePublications);
		
		return "profileSelfTab";
		
	}
	
	@RequestMapping("/profile/messagesTab")
	public String messagesTab(Model model){
		
		
		return "profileMessagesTab";
	}
	
	
	@RequestMapping({"/profile/settingsTab"})
	public String settingsTab(Model model, Principal principal){
		User domainUser = userService.findOne(principal.getName());
		
		model.addAttribute("user", domainUser);
		return "profileSettingsTab";
	}
	
	@RequestMapping({"/profile/{username}"})
	public String view(@PathVariable("username") String username, @ModelAttribute("sessionMessage") SessionMessage sessionMessage, Model model, final RedirectAttributes redirectAttributes, @RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber){
		
		Profile profile = profileService.findOne(username);
		
		
		if(profile != null){
			
			com.elevysi.site.pojo.Page publicationsPage = publicationService.buildOffsetPage(pageNumber, 1, Publication_.created, SortDirection.DESC);
			List<Publication> profilePublications = publicationService.getProfilePublications(profile, publicationsPage);
			
			Set<Profile> friends = profileService.findProfileFriends(profile);			
			List<Post> posts = postService.findProfilePosts(profile, pageNumber, NO_PROFILE_POSTS, SORT_FIELD, SORT_DIRECTION);
			
			List<Play> plays = playService.findLatestPlaysForProfile(profile, new Play(), pageNumber-1, NO_PROFILE_POSTS, SORT_FIELD, SORT_DIRECTION);
			
			model.addAttribute("userProfile", profile);
			model.addAttribute("profilePublications", profilePublications);
			model.addAttribute("user", profile.getUser());
			model.addAttribute("friends", friends);
			model.addAttribute("posts", posts);
			model.addAttribute("plays", plays);
			model.addAttribute("sessionMessage", sessionMessage);
			model.addAttribute("pageTitle", profile.getName());
			model.addAttribute("pageDescription", profile.getDescription());
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			boolean isAuthenticated =  SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
			
			if(auth != null && isAuthenticated){
				ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
				Profile actingProfile = activeUser.getActiveProfile();
				if(actingProfile != null){
					model.addAttribute("actingProfile", actingProfile);
				}
			}
			
			return "profileView";
			
		}else{
			SessionMessage notFoundMsg = new SessionMessage("The specified profile was not found.");
			notFoundMsg.setDangerClass();
			redirectAttributes.addFlashAttribute("sessionMessage", notFoundMsg);
			return "redirect:/";
		}
	}
	
	
	
	@RequestMapping({"/bucket/{username}"})
	public String bucket(Model model, Principal principal, @PathVariable("username") String username, final RedirectAttributes redirectAttributes){
		/*
		 * Create a profile friend relationship
		 */
		User requestedUser = userService.findOne(username);
		ProfileType userProfileType = profileTypeService.findOne("user");
		Profile requestedProfile = profileService.findByUserAndProfileType(requestedUser, userProfileType);
		
		User requestingUser = userService.findOne((principal.getName()));
		Profile requestingProfile = profileService.findByUserAndProfileType(requestingUser, userProfileType);
		
		profileService.bucket(requestingProfile, requestedProfile);
		
		SessionMessage sessionMessage = new SessionMessage("Confetti "+username+  " has been successfully added to your bucket list.");
		sessionMessage.setSuccessClass();
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		return "redirect:/profile/"+username;
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
	public String profilePosts(Model model, RedirectAttributes redirectAttributes, Principal principal, @RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber){
		
		
		Profile actingProfile = profileService.getActiveProfile();
		List<Post> profilePosts = postService.findProfilePosts(actingProfile, pageNumber, NO_ITEMS_PER_PAGE, SORT_FIELD, SORT_DIRECTION);
		
		model.addAttribute("profilePosts", profilePosts);
		model.addAttribute("actingProfile", actingProfile);
		model.addAttribute("pageTitle", actingProfile.getName());
		model.addAttribute("pageDescription", actingProfile.getDescription());
		return "profilePosts";
		
	}
	
	@RequestMapping(value="/profile/{username}/plays")
	public String profilePlays(Model model, RedirectAttributes redirectAttributes, Principal principal, @RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber){
		
		Profile actingProfile = profileService.getActiveProfile();
		List<Play> profilePlays = playService.findLatestPlaysForProfile(actingProfile, new Play(), pageNumber-1, NO_ITEMS_PER_PAGE, SORT_FIELD, SORT_DIRECTION);
		
		model.addAttribute("profilePlays", profilePlays);
		model.addAttribute("actingProfile", actingProfile);
		model.addAttribute("pageTitle", actingProfile.getName());
		model.addAttribute("pageDescription", actingProfile.getDescription());
		return "profilePlays";
		
	}
	
	@RequestMapping(value="/profile/{username}/albums")
	public String profileAlbums(Model model, RedirectAttributes redirectAttributes, Principal principal, @RequestParam(defaultValue="1", required = false, value="page")Integer pageNumber){
		
		Profile actingProfile = profileService.getActiveProfile();
		List<Album> profileAlbums = albumService.getPaginatedAlbumsForProfile(actingProfile, pageNumber, NO_ITEMS_PER_PAGE, SORT_FIELD, SORT_DIRECTION);
		
		model.addAttribute("profileAlbums", profileAlbums);
		model.addAttribute("actingProfile", actingProfile);
		model.addAttribute("pageTitle", actingProfile.getName());
		model.addAttribute("pageDescription", actingProfile.getDescription());		
		return "profileAlbums";
		
	}
	
	@RequestMapping(value="/profile/{username}/settings")
	public String settings(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("sessionMessage") SessionMessage sessionMessage){
		ActiveUser activeUser = profileService.getActiveUser();		
		Profile actingProfile = activeUser.getActiveProfile();
		List<Profile> userProfiles = activeUser.getProfiles();
		List<ProfileType> profileTypes = profileTypeService.findProfileTypes();
		model.addAttribute("actingProfile", actingProfile);
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
	
	

	@RequestMapping({"/profile/friends"})
	public String friends(Model model, Principal principal){
		User domainUser = userService.findOne(principal.getName());
		model.addAttribute("user", domainUser);
		
		Profile actingProfile = profileService.getActiveProfile();
		model.addAttribute("actingProfile", actingProfile);
		
		Set<Profile> friends = profileService.findProfileFriends(actingProfile);
		model.addAttribute("friends", friends);
		
		model.addAttribute("pageTitle", actingProfile.getName());
		model.addAttribute("pageDescription", actingProfile.getDescription());
		
		return "profileFriends";
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
	
}
