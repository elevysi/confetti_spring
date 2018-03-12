package com.elevysi.site.blog.controller;

import java.util.HashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Play;
import com.elevysi.site.blog.entity.PlayType;
import com.elevysi.site.blog.entity.Profile;
import com.elevysi.site.blog.pojo.SessionMessage;
import com.elevysi.site.blog.pojo.Page.SortDirection;
import com.elevysi.site.blog.service.DossierService;
import com.elevysi.site.blog.service.PlayService;
import com.elevysi.site.blog.service.PlayTypeService;
import com.elevysi.site.blog.entity.Dossier_;

@Controller
@RequestMapping(value="/plays")
public class PlayController extends AbstractController{
	
	@Autowired
	private PlayTypeService playTypeService;
	
	@Autowired
	private PlayService playService;
	
	@Autowired
	private DossierService dossierService;
	
	private final static int NO_LATEST_OWN_PLAYS = 3;
	private final static int NO_LATEST_PLAYS = 3;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "playType", new CustomCollectionEditor(Set.class)
          {
            @Override
            protected Object convertElement(Object element)
            {
            	System.out.println("called!");
                Integer id = null;

                if(element instanceof String && !((String)element).equals("")){
                    //From the JSP 'element' will be a String
                    try{
                        id = Integer.parseInt((String) element);
                    }
                    catch (NumberFormatException e) {
//                        System.out.println("Element was " + ((String) element));
                        e.printStackTrace();
                    }
                }
                else if(element instanceof Integer) {
                    //From the database 'element' will be an Integer
                    id = (Integer) element;
                }

                return id != null ? playTypeService.findOne(id) : null;
            }
          });
    }
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model){
		
		Play play = new Play();
		List<PlayType> foundPlayTypes = playTypeService.findAll();
		
		
		model.addAttribute("play", play);
		model.addAttribute("playTypes", foundPlayTypes);
		
		com.elevysi.site.blog.pojo.Page dossiersPage = dossierService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_DOSSIERS, Dossier_.created, SortDirection.DESC);		
		List<Dossier> dossiers = dossierService.getDossiers(dossiersPage);
		model.addAttribute("dossiers", dossiers);
		
		return "playAdd";
	}
	
	
	@RequestMapping(value={"/view/{id}/*", "/view/{id}"})
	public String view(Model model, @PathVariable("id")Integer id, @ModelAttribute("sessionMessage")SessionMessage sessionMessage) throws Exception{
		Play play = playService.getPlay(id);
		
		if(play == null){
			throw new Exception("The play was not found!");
		}
		boolean canEditPlay = false;
		Profile playProfile = play.getPlayProfile();
		if(playProfile!= null && playProfile.equals(playService.getActiveProfile())) canEditPlay = true;
		
		model.addAttribute("play", play);
		model.addAttribute("canEditPlay", canEditPlay);
		
		Profile playOwner = play.getPlayProfile();
		List<Play> latestProfilePlays = playService.findLatestPlaysForProfile(playOwner, play, 0, NO_LATEST_OWN_PLAYS, SORT_FIELD, SORT_DIRECTION);
		model.addAttribute("latestProfilePlays", latestProfilePlays);
		model.addAttribute("title", "Play - "+play.getId());
		model.addAttribute("pageTitle", play.getTitle());
		model.addAttribute("pageDescription", play.getTitle());
		return "viewplay";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String doAdd(Model model, @Valid @ModelAttribute("play") Play play, BindingResult result, RedirectAttributes redirectAttributes){
		
		if(result.hasErrors()){
			return "playAdd";
		}
		
		/**
		 * Record the post to the profile Editing it
		 */
		Profile owningProfile = playService.getActiveProfile();
		
		play.setPlayProfile(owningProfile);
		
		if(play.getDossier().getId() == null){
			play.setDossier(null);
		}
		
		Play savedPlay = playService.savePlay(play);
		
		SessionMessage sessionMessage = new SessionMessage("The play was successfully saved!");
		sessionMessage.setSuccessClass();
		
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		
		return "redirect:/plays/view/"+savedPlay.getId()+"/";
	}
	
	@RequestMapping(value={"/edit/{id}", "/edit/{id}/*"})
	public String edit(Model model, @PathVariable("id")Integer id, @ModelAttribute("sessionMessage")SessionMessage sessionMessage){
		Play play = playService.getPlay(id);
		model.addAttribute("play", play);
		List<PlayType> foundPlayTypes = playTypeService.findAll();		
		model.addAttribute("playTypes", foundPlayTypes);
		com.elevysi.site.blog.pojo.Page dossiersPage = dossierService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_DOSSIERS, Dossier_.created, SortDirection.DESC);		
		List<Dossier> dossiers = dossierService.getDossiers(dossiersPage);
		model.addAttribute("dossiers", dossiers);
		
		return "editplay";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String doEdit(Model model, @PathVariable("id")Integer id, @Valid @ModelAttribute("play") Play play, BindingResult result, RedirectAttributes redirectAttributes){
		
		if(result.hasErrors()){
			return "redirect:edit/"+id;
		}
		
		/**
		 * Record the post to the profile Editing it
		 */
		Play dbPlay = playService.getPlay(id);
		Profile owningProfile = playService.getActiveProfile();
		if(dbPlay != null){
			dbPlay.setTitle(play.getTitle());
			dbPlay.setPlayProfile(owningProfile);
			dbPlay.setUrl(play.getUrl());
			dbPlay.setEmbeddedUrl(play.getEmbeddedUrl());
			dbPlay.setDescription(play.getDescription());
			dbPlay.setPlayType(play.getPlayType());
			
			if(play.getDossier().getId() == null){
				dbPlay.setDossier(null);
			}else{
				dbPlay.setDossier(play.getDossier());
			}
			
			Play savedPlay = playService.saveEditedPlay(dbPlay);
			Play reloadPlay = playService.getPlay(savedPlay.getId());
			
			SessionMessage sessionMessage = new SessionMessage("The play was successfully saved!");
			sessionMessage.setSuccessClass();
			
			redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
			
			return "redirect:/plays/view/"+savedPlay.getId()+"/"+reloadPlay.getPublication().getFriendlyUrl();
		}
		
		SessionMessage sessionMessage = new SessionMessage("Failed to edit the play!");
		sessionMessage.setDangerClass();
		
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		
		return "redirect:edit/"+id;
		
	}
	
	@RequestMapping(value="delete/{id}", method=RequestMethod.POST)
	public String doDeletePlay(@PathVariable("id")Integer id, Model model, RedirectAttributes redirectAttributes){
		SessionMessage sessionMessage;
		
		Play play = playService.getPlay(id);
		if(play != null){
			playService.deletePlay(play);
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
