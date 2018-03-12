package com.elevysi.site.blog.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.config.security.ActiveUser;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.entity.ProfileType;
import com.elevysi.site.blog.entity.Role;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service("customUserDetailsService")
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ProfileTypeService profileTypeService;
	
	/**
	 * Returns a populated {@link UserDetails} object. 
	 * The username is first retrieved from the database and then mapped to 
	 * a {@link UserDetails} object.
	 */
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
//        	com.elevysi.site.entity.User domainUser = userService.loadUserForLogin(username);
        	com.elevysi.site.blog.entity.User domainUser = userService.loadUserByUsername(username);
            
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            
            Set<Role> roles = domainUser.getRoles();
            Set<Profile> profiles = domainUser.getProfiles();
            List<String> springSecurityRoles = treatRoles(roles);
            
            List<GrantedAuthority> authList = getGrantedAuthorities(springSecurityRoles);
            
//            Profile userProfile = domainUser.getPrincipalProfile(); 
//            Profile userProfile = profileService.findProfileByUser(domainUser);
            Profile userProfile = profileService.getPrincipalProfile(domainUser);
            		
    		List<Profile> userProfiles = new ArrayList<Profile>();
    		
    		Iterator<Profile> it = profiles.iterator();
    		
    		while(it.hasNext()){
    			userProfiles.add(it.next());
    		}
            
            return new ActiveUser( 
            		domainUser.getUsername(), 
                    domainUser.getPassword(), 
                    enabled, 
                    accountNonExpired, 
                    credentialsNonExpired, 
                    accountNonLocked,
                    authList,
                    domainUser.getFirst_name(),
                    userProfile,
                    userProfile,
                    userProfiles);
            
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
        
    }
	
	public UserDetails cloneAndUpdateAuthenticatedUser(ActiveUser activeUser, Profile profile){
		activeUser.setActiveProfile(profile);
		this.addAndUpdateAuthenticatedUser(activeUser, profile);
		return activeUser;
	}

	public UserDetails addAndUpdateAuthenticatedUser(ActiveUser activeUser, Profile profile){
		
		boolean foundSameProfile = false;
		if(profile != null){
			List<Profile> userProfiles = activeUser.getProfiles();
			for(Profile storedProfile : userProfiles){
				if(storedProfile.getId() == profile.getId()){
					if(activeUser.getProfiles().remove(storedProfile)){
						activeUser.getProfiles().add(profile);
						foundSameProfile = true;
					}
					
				}
			}
			if(! foundSameProfile){
				activeUser.getProfiles().add(profile);
			}
		}
		
		return activeUser;
	}
    
    /**
	 * Converts a numerical role to an equivalent list of roles
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
    public List<String> treatRoles(Set<Role> roles) {
    	List<String> security_roles = new ArrayList<String>();
    	
    	for (Role userRole : roles) {
    		security_roles.add(userRole.getName());
		}
    	
    	return security_roles;
    }
    
    /**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
 
}