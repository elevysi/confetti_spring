package com.elevysi.site.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.blog.entity.Category;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.PlayType;
import com.elevysi.site.blog.entity.Publication;
import com.elevysi.site.blog.entity.PublicationStatus;
import com.elevysi.site.blog.entity.PublicationType;
import com.elevysi.site.blog.service.DossierService;
import com.elevysi.site.blog.service.PublicationTypeService;
import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.SessionMessage;

@Controller
@RequestMapping(value="/dossiers")
public class DossierController extends AbstractController {
	
	@Autowired
	private DossierService dossierService;
	
	@Autowired
	private PublicationTypeService publicationTypeService;

	@RequestMapping(value="add")
	public String add(Model model){
		Dossier dossier = new Dossier();
		Publication publication = new Publication();
		dossier.setPublication(publication);
		dossier.setUuid(dossier.generateUUID());
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("dossier", dossier);
		return "addDossier";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String doAdd(Model model, @Valid @ModelAttribute("dossier")Dossier dossier,BindingResult result ,RedirectAttributes redirectAttributes, @RequestParam("action") String action){
		
		SessionMessage sessionMessage = new SessionMessage();
		if(result.hasErrors()){
			sessionMessage.setDangerClass();
			sessionMessage.setMsgText("Please address the errors before saving.");
			List<Category> categories = categoryService.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("sessionMessage", sessionMessage);
			return "addDossier";
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
		
		ProfileDTO owningProfile = dossierService.getActiveProfile();
		dossier.getPublication().setProfile(owningProfile);
		dossier.getPublication().setPublicationStatus(postStatus);
		dossier.getPublication().setProfileName(owningProfile.getName());
		dossier.getPublication().setProfileID(owningProfile.getId());
		PublicationType type = publicationTypeService.findByID(new Integer(4));
		dossier.getPublication().setType(type);
		dossier.setProfileID(owningProfile.getId());
		Dossier savedDossier = dossierService.save(dossier);
		
		
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		if(savedDossier != null){
			sessionMessage.setMsgText("The dossier was successfully saved!");
			sessionMessage.setSuccessClass();
			redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
			return "redirect:/dossiers/view/"+savedDossier.getId()+"/";
		}
		sessionMessage.setMsgText("Failed to save !");
		sessionMessage.setDangerClass();
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		return "addDossier";
		
	}
	
	@RequestMapping(value={"/edit/{id}", "/edit/{id}/*"})
	public String edit(Model model, @PathVariable("id")Integer id){
		Dossier dossier = dossierService.findByID(id);
		if(dossier.getUuid() == null) dossier.setUuid(dossier.generateUUID());
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("dossier", dossier);
		return "editDossier";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String doEdit(Model model, @PathVariable("id")Integer id, @Valid @ModelAttribute("dossier")Dossier dossier,BindingResult result ,RedirectAttributes redirectAttributes, @RequestParam("action") String action){
		
		SessionMessage sessionMessage = new SessionMessage();
		if(result.hasErrors()){
			sessionMessage.setDangerClass();
			sessionMessage.setMsgText("Please address the errors before saving.");
			List<Category> categories = categoryService.findAll();
			
			model.addAttribute("categories", categories);
			model.addAttribute("sessionMessage", sessionMessage);
			return "editDossier";
		}
		
		
		Dossier dbDossier = dossierService.findByID(id);
		if(dbDossier != null){
			
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
				
				return "redirect:/dossiers/view/"+dossier.getId()+"/"+dossier.getPublication().getFriendlyUrl();
			}
			
			dbDossier.setIsPublic(dossier.getIsPublic());
			dbDossier.setName(dossier.getName());
			dbDossier.setDescription(dossier.getDescription());
			dbDossier.getPublication().setCategories(dossier.getPublication().getCategories());
			dbDossier.getPublication().setPublicationStatus(postStatus);
			
			Dossier savedDossier = dossierService.saveEdited(dbDossier);
			
			if(savedDossier != null){
				sessionMessage.setMsgText("The dossier was successfully edited!");
				sessionMessage.setSuccessClass();
				redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
				return "redirect:/dossiers/view/"+savedDossier.getId()+"/"+savedDossier.getPublication().getFriendlyUrl();
			}
		}
		
		sessionMessage.setMsgText("Failed to save !");
		sessionMessage.setDangerClass();
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		return "editDossier";
		
	}
	
	@RequestMapping(value={"/view/{id}/*", "view/{id}"})
	public String view(Model model, @PathVariable("id")Integer id, RedirectAttributes redirectAttributes) throws Exception{
		Dossier dossier = dossierService.findByID(id);
		if(dossier == null){
			throw new Exception("Could not find the Dossier");
		}
		
		boolean canEditDossier = false;
		ProfileDTO dossierProfile = dossier.getProfile();
		if(dossierProfile!= null && dossierProfile.equals(dossierService.getActiveProfile())) canEditDossier = true;
		
		model.addAttribute("canEditDossier", canEditDossier);
		model.addAttribute("dossier", dossier);
		model.addAttribute("pageTitle", dossier.getName());
		model.addAttribute("pageDescription", dossier.getDescription());
		model.addAttribute("canEditDossier", true);
		return "viewDossier";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String doDeleteDossier(@PathVariable("id")Integer id, Model model, RedirectAttributes redirectAttributes){
		SessionMessage sessionMessage;
		
		Dossier dossier = dossierService.findByID(id);
		if(dossier != null){
			dossierService.delete(dossier);
			sessionMessage = new SessionMessage("The dossier was successfully deleted.");
			sessionMessage.setSuccessClass();
		}else{
			sessionMessage = new SessionMessage("Could not find the dossier.");
			sessionMessage.setDangerClass();
		}
		
		redirectAttributes.addFlashAttribute("sessionMessage",sessionMessage);
		
		return "redirect:/public/dossiers";
	}
}
