package com.elevysi.site.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.entity.Dossier;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.pojo.SessionMessage;
import com.elevysi.site.service.DossierService;

@Controller
@RequestMapping(value="/dossiers")
public class DossierController extends AbstractController {
	
	@Autowired
	private DossierService dossierService;

	@RequestMapping(value="add")
	public String add(Model model){
		Dossier dossier = new Dossier();
		dossier.setUuid(dossier.generateUUID());
		model.addAttribute("dossier", dossier);
		return "addDossier";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String doAdd(Model model, @Valid @ModelAttribute("dossier")Dossier dossier,BindingResult result ,RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			return "addDossier";
		}
		Profile owningProfile = dossierService.getActiveProfile();
		dossier.setProfile(owningProfile);
		Dossier savedDossier = dossierService.save(dossier);
		SessionMessage sessionMessage = new SessionMessage();
		
		
		
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
		Dossier dossier = dossierService.findById(id);
		if(dossier.getUuid() == null) dossier.setUuid(dossier.generateUUID());
		model.addAttribute("dossier", dossier);
		return "editDossier";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String doEdit(Model model, @Valid @ModelAttribute("dossier")Dossier dossier,BindingResult result ,RedirectAttributes redirectAttributes){
		
		if(result.hasErrors()){
			return "editDossier";
		}
		Profile owningProfile = dossierService.getActiveProfile();
		dossier.setProfile(owningProfile);
		Dossier savedDossier = dossierService.saveEdited(dossier);
		SessionMessage sessionMessage = new SessionMessage();
		
		
		
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		if(savedDossier != null){
			sessionMessage.setMsgText("The dossier was successfully edited!");
			sessionMessage.setSuccessClass();
			redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
			return "redirect:/dossiers/view/"+savedDossier.getId()+"/";
		}
		sessionMessage.setMsgText("Failed to save !");
		sessionMessage.setDangerClass();
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		return "editDossier";
		
	}
	
	@RequestMapping(value={"/view/{id}/*", "view/{id}"})
	public String view(Model model, @PathVariable("id")Integer id, RedirectAttributes redirectAttributes){
		Dossier dossier = dossierService.findById(id);
		if(dossier == null){
			throw new NotFoundException("Could not find the Dossier");
		}
		
		boolean canEditDossier = false;
		Profile dossierProfile = dossier.getProfile();
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
		
		Dossier dossier = dossierService.findById(id);
		if(dossier != null){
			dossierService.deleteDossier(dossier);
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
