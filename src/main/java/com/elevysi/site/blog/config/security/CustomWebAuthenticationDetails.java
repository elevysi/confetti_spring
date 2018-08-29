package com.elevysi.site.blog.config.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2476613904576723342L;
	private final String redirectOption;
	
	public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        redirectOption = request.getParameter("redirectOption");
    }

	public String getRedirectOption() {
		return redirectOption;
	}

}
