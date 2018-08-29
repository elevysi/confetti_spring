//package com.elevysi.site.blog.config.security;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import com.elevysi.site.blog.soa.client.AuthFeignClient;
//import com.elevysi.site.blog.soa.client.SocialFeignClient;
//import com.elevysi.site.commons.dto.ProfileDTO;
//import com.elevysi.site.commons.dto.UserDTO;
//
////https://github.com/shahbour/microservices-security/blob/master/ui/src/main/java/com/shahbour/config/CustomPrincipal.java
//
//@Component
//public class CustomPrincipal implements PrincipalExtractor{
//	
//	@Autowired
//	SocialFeignClient socialFeignClient;
//	
//	@Autowired
//	AuthFeignClient authFeignClient;
//	
//	@Autowired
//	private ModelMapper modelMapper;
//	
//	private static final String[] PRINCIPAL_KEYS = new String[] { "user", "username",
//            "userid", "user_id", "login", "id", "name" };
//	
//	@Override
//    public Object extractPrincipal(Map<String, Object> map) {
//		System.out.println("Extracting....");
//        Object principal = map.getOrDefault("principal", null);
//        if(principal != null) {
//            Map<String,Object> userDetail = (Map) principal;
//            String username = userDetail.get("username").toString();
//            
//            UserDTO user = authFeignClient.getUserByUsername(username);
//        	
//        	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        	
//        	
//        	//Get the user's profile
//        	Object profileObject = socialFeignClient.getProfileByUser(user.getId().toString());
//        	ProfileDTO profile = modelMapper.map(profileObject, ProfileDTO.class);
//        	
//        	 ActiveUser activeUser = new ActiveUser(
//        			 user.getUsername(),
//	            	 	"dummyPassword",
//	            		true,
//	            		true,
//	            		true,
//	            		true,
//	            		authorities,
//	            		user.getFirst_name(),
//	            		null,
//	            		null,
//	            		null
//     		);
//        	 
//        	 activeUser.setUserProfile(profile);
//        	 activeUser.setActiveProfile(profile);
//        	 activeUser.setProfiles(null);
//        	 
//        	 return activeUser;
//            
//            
////            User user = new User();
////            user.setFirstName(userDetail.get("firstName").toString());
////            user.setLastName(userDetail.get("lastName").toString());
////            user.setEmail(userDetail.get("email").toString());
////            user.setUsername(userDetail.get("username").toString());
////            user.setDn(userDetail.get("dn").toString());
////            user.setIpPhone(userDetail.get("ipPhone").toString());
//////	            user.setPhoto(userDetail.get("photo").toString());
////            return user;
//        } else {
//            for (String key : PRINCIPAL_KEYS) {
//                if (map.containsKey(key)) {
//                    return map.get(key);
//                }
//            }
//        }
//        return null;
//    }
//}
