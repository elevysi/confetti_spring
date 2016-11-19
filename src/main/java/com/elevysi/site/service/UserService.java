package com.elevysi.site.service;

import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.ProfileType;
import com.elevysi.site.entity.Role;
import com.elevysi.site.entity.Upload;
import com.elevysi.site.entity.User;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.repository.RoleRepository;
import com.elevysi.site.repository.UploadRepository;
import com.elevysi.site.repository.UserDAO;
import com.elevysi.site.repository.UserRepository;
import com.elevysi.site.security.ActiveUser;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private ProfileTypeService profileTypeService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private UploadService uploadService;
	
	
	
	@Value("${relativePathToDefaultAvatar}")
	private String relativePathToDefaultAvatar;
	
	
	public void registerUser(User user){
		
		Date now = new Date();
		
		user.setActive(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		user.setCreated(now);
		user.setModified(now);
		
		Role userRole = roleRepository.findByName("ROLE_USER");
		user.getRoles().add(userRole);
		
		
		
		//Add a profile for this user
		Profile userProfile = new Profile();
		userProfile.setUser(user);
		ProfileType userProfileType = profileTypeService.findOne("user");
		userProfile.setProfileType(userProfileType);
		
		userProfile.setName(user.getUsername());
		userProfile.setCreated(now);
		userProfile.setModified(now);
		
		
		
		user.getProfiles().add(userProfile);
		User savedUser = userRepository.save(user);
		Profile savedUserProfile = profileService.findByUserAndProfileType(savedUser, userProfileType);
		
		
		
		String savingPath = this.relativePathToDefaultAvatar;
		
		/*
		 * Give profile a default profile picture
		 * Can't save upload directly with profile Owner because of multiple mappings on link_id and updatable, insertable is set to false for Profile in favor of Post
		 */
		
		Upload profilePicture = new Upload();
		profilePicture.setPath(savingPath);
		profilePicture.setLinkTable("profilePicture");		
		profilePicture.setLinkId(savedUserProfile.getId());
		
		uploadService.saveUpload(profilePicture);
		
		
		
	}
	
	
	public User loadUserByUsername(String username){
		return userDAO.loadByUsername(username);
	}
	
	public void saveEdit(User user){
		user.setActive(true);
		userRepository.save(user);		
	}
	
	public User getUser(String username){
		return userRepository.findByUsername(username);
	}
	
	public User getUser(Integer id){
		return userRepository.findById(id);
	}
	
	public Page<User> findPaginatedUsers(Integer pageNumber, Integer limit, String sortField, String sortDirection) {
		
		/**
		 * Modify pageNumber to use 1 index instead of zero index pages
		 */
		
		Page<User> requestedPage = userRepository.findAll(constructPageSpecification(pageNumber - 1, limit, sortField, sortDirection));
		return requestedPage;
		
	}
	
	/**
     * Returns a new object which specifies the the wanted result page.
     * @param pageIndex The index of the wanted result page
     * @return
     */
    private Pageable constructPageSpecification(int pageIndex, int limit, String sortField, String sortDirection) {
        Pageable pageSpecification = new PageRequest(pageIndex, limit, sortByField(sortField, sortDirection));
        return pageSpecification;
    }
 
    /**
     * Returns a Sort object which sorts persons in ascending order by using the last name.
     * @return
     */
    private Sort sortByField(String sortField, String sortDirection) {
    	
    	if(sortDirection.equalsIgnoreCase("desc")){
    		return new Sort(Sort.Direction.DESC, sortField);
    	}else return new Sort(Sort.Direction.ASC, sortField);
        
    }

	public void delete(int id) {
		
		userRepository.delete(id);
		
	}

	public User findOne(String username) {
		return userRepository.findByUsername(username);
	}

	public User findOneWithEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> findMatching(String term) {
		return userRepository.searchFor(term);
	}

	public User findOne(Integer id) {
		
		return userRepository.findById(id);
	}

	public void reloadCurrentProfile() {
		
		
		ActiveUser activeUser = this.getActiveUser();
		Profile userProfile = this.getActiveProfile();
		
		/**
		 * ReFind Profile with new details
		 */
		Profile reloadedProfile = profileService.findOne(userProfile.getId());
		activeUser.setActiveProfile(reloadedProfile);
		
	}
	
	public Profile getActiveProfile(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
			return activeUser.getUserProfile();	
		}
		
		return null;
	}
	
	public ActiveUser getActiveUser(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return (ActiveUser)auth.getPrincipal();	
		}
		
		
		return null;	
	}
	
	public OffsetPage buildOffsetPage(int pageIndex, int size,  SingularAttribute sortField, SortDirection sortDirection){
		return userDAO.buildOffsetPage(pageIndex, size, sortField, sortDirection);
	}


	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUsers(com.elevysi.site.pojo.Page page){
		return userDAO.getUsers(page);
	}
	

	@PostAuthorize("returnObject.username == principal.username || hasRole('ADMIN')")
	public User getUserById(int id){
		return userDAO.findById(id);
	}
	
	@PreAuthorize("#user.username == principal.username || hasRole('ADMIN')")
	public User updateUser(User user){
		Date now = new Date();
		user.setModified(now);
		return userDAO.update(user);
	}
	
	public User updatePassword(String newPassword){
		ActiveUser activeUser = this.getActiveUser();
		String username = activeUser.getUsername();
		
		User dbUser = userDAO.findByUsername(username);
		if(dbUser != null){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			dbUser.setPassword(encoder.encode(newPassword));
			Date now = new Date();
			dbUser.setModified(now);
			User savedUser = userDAO.update(dbUser);
			
			return savedUser;
		}
		
		return null;
	}
	
	public boolean comparePasswords(String givenPassword){
		ActiveUser activeUser = this.getActiveUser();
		String username = activeUser.getUsername();
		
		User dbUser = userDAO.findByUsername(username);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(encoder.matches(givenPassword, dbUser.getPassword())){
			return true;
		}
		
		return false;
	}
	
}
