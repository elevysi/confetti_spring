package com.elevysi.site.blog.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
//import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.blog.entity.Category;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.PlayType;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.PublicationStatus;
import com.elevysi.site.blog.entity.PublicationType;
import com.elevysi.site.blog.service.CategoryService;
import com.elevysi.site.blog.service.DossierService;
import com.elevysi.site.blog.service.PlayService;
import com.elevysi.site.blog.service.PlayTypeService;
import com.elevysi.site.blog.service.PublicationTypeService;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.SessionMessage;
import com.elevysi.site.commons.pojo.Page.SortDirection;
import com.elevysi.site.blog.entity.Dossier_;

@Controller
@RequestMapping(value="/plays")
public class PlayController extends AbstractController{
	
	@Autowired
	private PlayService playService;
	
	@Autowired
	private DossierService dossierService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PublicationTypeService publicationTypeService;
	
	private final static int NO_LATEST_OWN_PLAYS = 3;
	private final static int NO_LATEST_PLAYS = 3;
	
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model){
		
		Play play = new Play();
		Publication publication = new Publication();
		play.setPublication(publication);
		
		/**
		 * Find the Lists
		 */
		List<Category> categories = categoryService.findAll();
		List<Dossier> dossiers = dossierService.findAll();
		List<PlayType> foundPlayTypes = playTypeService.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("dossiers", dossiers);
		model.addAttribute("playTypes", foundPlayTypes);
		
		model.addAttribute("play", play);
		
		return "playAdd";
	}
	
	
	@RequestMapping(value={"/view/{id}/*", "/view/{id}"})
	public String view(Model model, @PathVariable("id")Integer id, @ModelAttribute("sessionMessage")SessionMessage sessionMessage) throws Exception{
		Play play = playService.findByID(id);
		
		if(play == null){
			throw new Exception("The play was not found!");
		}
		boolean canEditPlay = false;
		ProfileDTO playProfile = play.getPlayProfile();
		if(playProfile!= null && playProfile.equals(playService.getActiveProfile())) canEditPlay = true;
		
		model.addAttribute("play", play);
		model.addAttribute("canEditPlay", canEditPlay);
		
		ProfileDTO playOwner = play.getPlayProfile();
		List<Play> latestProfilePlays = playService.findLatestPlaysForProfile(playOwner, play, 0, NO_LATEST_OWN_PLAYS, SORT_FIELD, SORT_DIRECTION);
		model.addAttribute("latestProfilePlays", latestProfilePlays);
		model.addAttribute("title", "Play - "+play.getId());
		model.addAttribute("pageTitle", play.getTitle());
		model.addAttribute("pageDescription", play.getTitle());
		return "viewplay";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String doAdd(Model model, @Valid @ModelAttribute("play") Play play, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam("action") String action){
		
		if(result.hasErrors()){
			
			List<Category> categories = categoryService.findAll();
			List<Dossier> dossiers = dossierService.findAll();
			List<PlayType> foundPlayTypes = playTypeService.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("dossiers", dossiers);
			model.addAttribute("playTypes", foundPlayTypes);
			
			SessionMessage dangerMsg = new SessionMessage();
			dangerMsg.setDangerClass();
			
			dangerMsg.setMsgText("Please address the errors before saving.");
			model.addAttribute("sessionMessage", dangerMsg);
			
			return "playAdd";
		}
		
		PublicationStatus postStatus;
		if(action.equals("draft")){
			postStatus = poublicationStatusService.findDraftPostStatus();
			
		}else if(action.equals("publish")){
			postStatus = poublicationStatusService.findPublishedPostStatus();
		}else if(action.equals("findToBePublishedPostStatus")){
			postStatus = poublicationStatusService.findToBePublishedPostStatus();
		}else{
			
			/**
			 * Cancel Button
			 */
			
			return "redirect:/";
		}
		
		
		
		ProfileDTO owningProfile = playService.getActiveProfile();
		play.setProfileID(owningProfile.getId());
		play.setPlayProfile(owningProfile);
		play.getPublication().setPublicationStatus(postStatus);
		play.getPublication().setProfileName(owningProfile.getName());
		play.getPublication().setProfileID(owningProfile.getId());
		PublicationType type = publicationTypeService.findByID(new Integer(2));
		play.getPublication().setType(type);
		
		Play savedPlay = playService.savePlay(play);
		
		SessionMessage sessionMessage = new SessionMessage("The play was successfully saved!");
		sessionMessage.setSuccessClass();
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		
		return "redirect:/plays/view/"+savedPlay.getId()+"/"+savedPlay.getPublication().getFriendlyUrl();
		
	}
	
	@RequestMapping(value={"/edit/{id}", "/edit/{id}/*"})
	public String edit(Model model, @PathVariable("id")Integer id, @ModelAttribute("sessionMessage")SessionMessage sessionMessage){
		Play play = playService.findByID(id);
		
		play.getPublication().setDossiers(new HashSet<Dossier>()); //Because failed to bind on the form
		
		List<Category> categories = categoryService.findAll();
		List<Dossier> dossiers = dossierService.findAll();
		List<PlayType> foundPlayTypes = playTypeService.findAll();
		
		model.addAttribute("play", play);
		model.addAttribute("categories", categories);
		model.addAttribute("dossiers", dossiers);
		model.addAttribute("playTypes", foundPlayTypes);
		
		return "editplay";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String doEdit(Model model, @PathVariable("id")Integer id, @Valid @ModelAttribute("play") Play play, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam("action") String action){
		
		SessionMessage dangerMsg = new SessionMessage();
		dangerMsg.setDangerClass();
		
		if(result.hasErrors()){
			
			List<Category> categories = categoryService.findAll();
			List<Dossier> dossiers = dossierService.findAll();
			List<PlayType> foundPlayTypes = playTypeService.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("dossiers", dossiers);
			model.addAttribute("playTypes", foundPlayTypes);
			
			
			dangerMsg.setMsgText("Please address the errors before saving.");
			model.addAttribute("sessionMessage", dangerMsg);
			
			return "editplay";
		}
		
		PublicationStatus postStatus;
		if(action.equals("draft")){
			postStatus = poublicationStatusService.findDraftPostStatus();
			
		}else if(action.equals("publish")){
			postStatus = poublicationStatusService.findPublishedPostStatus();
		}else if(action.equals("findToBePublishedPostStatus")){
			postStatus = poublicationStatusService.findToBePublishedPostStatus();
		}else{
			
			/**
			 * Cancel Button
			 */
			
			return "redirect:/plays/view/"+play.getId()+"/"+play.getPublication().getFriendlyUrl();
		}
		
		Play dbPlay = playService.findByID(id);
		if(dbPlay != null){
			dbPlay.setTitle(play.getTitle());
			dbPlay.setUrl(play.getUrl());
			dbPlay.setEmbeddedUrl(play.getEmbeddedUrl());
			dbPlay.setDescription(play.getDescription());
			dbPlay.setPlayType(play.getPlayType());
			dbPlay.getPublication().setPublicationStatus(postStatus);
			dbPlay.getPublication().setCategories(play.getPublication().getCategories());
			dbPlay.getPublication().setDossiers(play.getPublication().getDossiers());
			
			Play savedPlay = playService.saveEditedPlay(dbPlay);
			
			SessionMessage sessionMessage = new SessionMessage("The play was successfully saved!");
			sessionMessage.setSuccessClass();
			
			redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
			
			return "redirect:/plays/view/"+savedPlay.getId()+"/"+savedPlay.getPublication().getFriendlyUrl();
		}
		
		dangerMsg.setMsgText("Failed to edit the play!");
		dangerMsg.setDangerClass();
		
		redirectAttributes.addFlashAttribute("sessionMessage", dangerMsg);
		return "editplay";
		
	}
	
	@RequestMapping(value="delete/{id}", method=RequestMethod.POST)
	public String doDeletePlay(@PathVariable("id")Integer id, Model model, RedirectAttributes redirectAttributes){
		SessionMessage sessionMessage;
		
		Play play = playService.findByID(id);
		if(play != null){
			playService.delete(play);
			sessionMessage = new SessionMessage("The play was successfully deleted.");
			sessionMessage.setSuccessClass();
		}else{
			sessionMessage = new SessionMessage("Could not find the play.");
			sessionMessage.setDangerClass();
		}
		
		redirectAttributes.addFlashAttribute("sessionMessage",sessionMessage);
		
		return "redirect:/public/plays";
	}

}
