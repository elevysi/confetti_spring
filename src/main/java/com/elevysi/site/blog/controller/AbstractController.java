package com.elevysi.site.blog.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.elevysi.site.blog.entity.Category;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.service.CategoryService;
import com.elevysi.site.blog.service.DossierService;
import com.elevysi.site.blog.service.PlayTypeService;
import com.elevysi.site.blog.service.PublicationStatusService;
import com.elevysi.site.commons.pojo.ActiveUser;
import com.elevysi.site.commons.pojo.SessionMessage;

public abstract class AbstractController implements ServletContextAware{
	
	protected static final Logger logger = LoggerFactory.getLogger(AbstractController.class);
	
	private enum FlashMessageCategory {success, danger, warning, info};
	
	protected ServletContext servletContext;
	
	@Value("${upload.path}")
	protected String avatarUploadPath;
	
	@Value("${separator.ds}")
	protected String ds;
	
	@Value("${separator.oppositeDS}")
	protected String oppositeDS;
	
	protected final static int PAGE_SIZE = 10;
	protected final static int DEFAULT_NO_POSTS = 12;
	protected final static int DEFAULT_NO_ITEMS = 12;
	protected final static int DEFAULT_ALL_ITEMS = 1000;
	protected final static int  DEFAULT_NO_DOSSIERS = 12;
	protected final static int ZERO_PAGE = 0;
	protected final static int FIRST_PAGE = 1;
	protected final static int ONE_PAGE = 1;
	protected final static String SORT_FIELD = "created";
	protected final static String SORT_DIRECTION = "DESC";
	protected final static int NO_INDEX_ITEMS = 20;
	protected final static int LA_LIFE_ALBUM_ID = 43;
	protected static final int NO_ALBUM_UPLOADS = 18;
	
	@Autowired
	protected CategoryService categoryService;
	
	@Autowired
	protected DossierService dossierService;
	
	@Autowired
	protected PlayTypeService playTypeService;
	
	@Autowired
	protected PublicationStatusService poublicationStatusService;
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(Set.class, "publication.categories", new CustomCollectionEditor(Set.class)
//        {
//          @Override
//          protected Object convertElement(Object element)
//          {
//              Integer id = null;
//
//              if(element instanceof String && !((String)element).equals("")){
//                  //From the JSP 'element' will be a String
//                  try{
//                      id = Integer.parseInt((String) element);
//                  }
//                  catch (NumberFormatException e) {
////                      System.out.println("Element was " + ((String) element));
//                      e.printStackTrace();
//                  }
//              }
//              else if(element instanceof Integer) {
//                  //From the database 'element' will be an Integer
//                  id = (Integer) element;
//              }
//
//              return id != null ? categoryService.findByID(id) : null;
//          }
//        });
//		
//		
//		binder.registerCustomEditor(Set.class, "publication.dossiers", new CustomCollectionEditor(Set.class)
//        {
//          @Override
//          protected Object convertElement(Object element)
//          {
//              Integer id = null;
//
//              if(element instanceof String && !((String)element).equals("")){
//                  //From the JSP 'element' will be a String
//                  try{
//                      id = Integer.parseInt((String) element);
//                  }
//                  catch (NumberFormatException e) {
////                      System.out.println("Element was " + ((String) element));
//                      e.printStackTrace();
//                  }
//              }
//              else if(element instanceof Integer) {
//                  //From the database 'element' will be an Integer
//                  id = (Integer) element;
//              }
//              return id != null ? dossierService.findByID(id) : null;
//        	  
//          }
//        });
		
		binder.registerCustomEditor(Dossier.class, new PropertyEditorSupport() {
			@Override
	         public void setAsText(String text) {
	            setValue((text.equals(""))? null : dossierService.findByID(Integer.parseInt((String)text)));
	         }
			
//			Converts a Dossier to a String (when displaying form)
		    @Override
		    public String getAsText() {
		        Dossier c = (Dossier) this.getValue();
		        return c.getId().toString();
		    }
		    
		    
	     });
		
		
		binder.registerCustomEditor(Category.class, new PropertyEditorSupport() {
			@Override
	         public void setAsText(String text) {
	            setValue((text.equals(""))? null : categoryService.findByID(Integer.parseInt((String)text)));
	         }
			
//			Converts a Category to a String (when displaying form)
		    @Override
		    public String getAsText() {
		        Dossier c = (Dossier) this.getValue();
		        return c.getId().toString();
		    }
		    
		    
	     });
		
		binder.registerCustomEditor(Set.class, "playType", new CustomCollectionEditor(Set.class)
        {
          @Override
          protected Object convertElement(Object element)
          {
              Integer id = null;

              if(element instanceof String && !((String)element).equals("")){
                  //From the JSP 'element' will be a String
                  try{
                      id = Integer.parseInt((String) element);
                  }
                  catch (NumberFormatException e) {
//                      System.out.println("Element was " + ((String) element));
                      e.printStackTrace();
                  }
              }
              else if(element instanceof Integer) {
                  //From the database 'element' will be an Integer
                  id = (Integer) element;
              }

              return id != null ? playTypeService.findByID(id) : null;
          }
        });
	}
	
	 
	
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
	
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleAllException(){
//		ModelAndView model = new ModelAndView("error/exception_error");
//		
//		return model;
//	}
	
//	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
//	@ExceptionHandler(IOException.class)
//	public ModelAndView handleIOException(){
//		ModelAndView model = new ModelAndView("error/exception_error");
//		
//		return model;
//	}
}
