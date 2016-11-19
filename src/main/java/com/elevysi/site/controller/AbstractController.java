package com.elevysi.site.controller;

import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.elevysi.site.entity.Profile;
import com.elevysi.site.pojo.SessionMessage;
import com.elevysi.site.security.ActiveUser;

public abstract class AbstractController implements ServletContextAware{
	
	protected static final Logger logger = LoggerFactory.getLogger(AbstractController.class);
	
	private enum FlashMessageCategory {success, danger, warning, info};
	
	protected ServletContext servletContext;
	
	@Value("${upload.img.path}")
	protected String avatarUploadPath;
	
	@Value("${ds}")
	protected String ds;
	
	@Value("${oppositeDS}")
	protected String oppositeDS;
	
	protected final static int PAGE_SIZE = 10;
	protected final static int DEFAULT_NO_POSTS = 10;
	protected final static int DEFAULT_NO_ITEMS = 10;
	protected final static int ZERO_PAGE = 0;
	protected final static int FIRST_PAGE = 1;
	protected final static int ONE_PAGE = 1;
	protected final static String SORT_FIELD = "created";
	protected final static String SORT_DIRECTION = "DESC";
	protected final static int NO_INDEX_ITEMS = 20;
	protected final static int LA_LIFE_ALBUM_ID = 43;
	protected static final int NO_ALBUM_UPLOADS = 18;
	
	 
	
	public void setSessionMsg(Model model, String msgTxt, String msgClass){
		SessionMessage sessionMessage = new SessionMessage(msgTxt, msgClass);
		
		FlashMessageCategory msgTranslated = FlashMessageCategory.valueOf(msgClass);
		switch (msgTranslated) {
			case success:
				sessionMessage.setSuccessClass();
				break;
			case danger:
				sessionMessage.setWarmingClass();
				break;
			case warning:
				sessionMessage.setDangerClass();
			case info:
				sessionMessage.setInfoClass();
				break;
	
			default:
				sessionMessage.setInfoClass();
		}
		
		model.addAttribute("sessionMessage", sessionMessage);
	}
	
	public void setSessionMsg(Model model, SessionMessage sessionMessage){
		model.addAttribute("sessionMessage", sessionMessage);
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public String generateUUID(){
		 UUID uniqueKey = UUID.randomUUID();   
		 return uniqueKey.toString();
		 
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(){
		ModelAndView model = new ModelAndView("error/exception_error");
		
		return model;
	}
	
}
