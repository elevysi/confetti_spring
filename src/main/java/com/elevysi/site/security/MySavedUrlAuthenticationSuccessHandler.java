package com.elevysi.site.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MySavedUrlAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	
	@Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		
		return super.determineTargetUrl(request, response);
    }
 
    

}
