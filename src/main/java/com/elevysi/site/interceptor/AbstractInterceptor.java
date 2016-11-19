package com.elevysi.site.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.elevysi.site.entity.Post;
import com.elevysi.site.service.PostService;

//import com.elevysi.site.pojo.SessionMessage;

public class AbstractInterceptor implements HandlerInterceptor{
	
//	@Autowired
//	private PostService postService;
	
	@Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
         
//		SessionMessage sessionMessage = new SessionMessage();
//		request.setAttribute("sessionMessage", sessionMessage);
		
         
        return true;
    }
     
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    	
    	
//    	List<Post> latestPosts = postService.findLatestPosts(3);
//    	if(modelAndView == null) return;
//        	modelAndView.getModelMap().addAttribute("latestPosts", latestPosts);
        	
    }
     
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	
    }
}
