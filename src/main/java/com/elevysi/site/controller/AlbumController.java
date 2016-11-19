package com.elevysi.site.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Dossier;
import com.elevysi.site.entity.Dossier_;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.Upload;
import com.elevysi.site.pojo.SessionMessage;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.service.AlbumService;
import com.elevysi.site.service.DossierService;
import com.elevysi.site.service.UploadService;

@Controller
@RequestMapping(value="/albums")
public class AlbumController extends AbstractController{
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private DossierService dossierService;
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping(value="/add")
	public String add(Model model){
		
		
		Album album = new Album();
		String uuid = album.generateUUID();
		album.setUuid(uuid);
		
		model.addAttribute("uuid", uuid);
		model.addAttribute("album", album);
		
		com.elevysi.site.pojo.Page dossiersPage = dossierService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_ITEMS, Dossier_.created, SortDirection.DESC);		
		List<Dossier> dossiers = dossierService.getDossiers(dossiersPage);
		model.addAttribute("dossiers", dossiers);
		
		return "albumAdd";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doAdd(Model model, @ModelAttribute("album")Album album, BindingResult result, RedirectAttributes redirectAttributes){
		
		if(result.hasErrors()){
			return "redirect:/albums/add";
		}
		
		/**
		 * Record the post to the profile Editing it
		 */
		Profile owningProfile = albumService.getActiveProfile();
		album.setProfileOwner(owningProfile);
		
		album.setLinkTable("profiles");
		album.setLinkId(owningProfile.getId());
		
		if(album.getDossier().getId() == null){
			album.setDossier(null);
		}
		
		Album savedAlbum = albumService.saveAlbum(album);
		
		/**
		 * Find the lists of uploads related to this
		 */
		
		SessionMessage sessionMessage = new SessionMessage("The album was successfully saved!");
		sessionMessage.setSuccessClass();
		
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		return "redirect:/profile";
		
	}
	
	@RequestMapping(value="/view/{id}/*")
	public String view(@PathVariable("id")Integer id, Model model, RedirectAttributes redirectAttributes, @RequestParam(defaultValue="1", required=false, value="page")Integer pageNumber){
		
		Album album = albumService.findOne(id);
		if(album != null){
			model.addAttribute("album", album);
			model.addAttribute("pageTitle", album.getName());
			model.addAttribute("pageDescription", album.getDescription());
			
			List<Upload> albumUploads = albumService.getPaginatedAlbumUploads(album, pageNumber, NO_ALBUM_UPLOADS, SORT_FIELD, SORT_DIRECTION);
			
			boolean canEditAlbum = false;
			Profile albumProfile = album.getProfileOwner();
			if(albumProfile!= null && albumProfile.equals(albumService.getActiveProfile())) canEditAlbum = true;
			
			model.addAttribute("albumUploads", albumUploads);
			model.addAttribute("canEditAlbum", canEditAlbum);
			return "albumView";
		}else{
			SessionMessage sessionMessage = new SessionMessage("The album was successfully saved!");
			sessionMessage.setSuccessClass();
			
			redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
			return "redirect:/profile";
		}
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Integer id){
		Album album = albumService.findById(id);
		model.addAttribute("album", album);
		if(album != null){
			model.addAttribute("album", album);
			
//			List<Upload> albumUploads = albumService.getPaginatedAlbumUploads(album, FIRST_PAGE, NO_ALBUM_UPLOADS, SORT_FIELD, SORT_DIRECTION);
			List<Upload> albumUploads = uploadService.getAllAlbumUploads(album);
			model.addAttribute("albumUploads", albumUploads);
			
			com.elevysi.site.pojo.Page dossiersPage = dossierService.buildOffsetPage(FIRST_PAGE, DEFAULT_NO_ITEMS, Dossier_.created, SortDirection.DESC);		
			List<Dossier> dossiers = dossierService.getDossiers(dossiersPage);
			model.addAttribute("dossiers", dossiers);
		}
		return "albumEdit";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String doEdit(Model model, @Valid @ModelAttribute("abum")Album album, BindingResult result, RedirectAttributes redirectAttributes){
//		if(result.hasErrors()){
//			return "redirect:/albums/edit/"+album.getId();
//		}
		
		Album originalAlbum = albumService.findOne(album.getId());
		originalAlbum.setPublicAlbum(album.getPublicAlbum());
		originalAlbum.setName(album.getName());
		originalAlbum.setPlace(album.getPlace());
		
		if(album.getDossier().getId() == null){
			album.setDossier(null);
			originalAlbum.setDossier(null);
		}else{
			originalAlbum.setDossier(album.getDossier());
		}
		
		
		
		/**
		 * Record the post to the profile Editing it
		 */		
		Album savedAlbum = albumService.saveEditedAlbum(originalAlbum);
		
		/**
		 * Find the lists of uploads related to this
		 */
		
		SessionMessage sessionMessage = new SessionMessage("The album was successfully saved!");
		sessionMessage.setSuccessClass();
		
		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		return "redirect:/albums/view/"+album.getId()+"/";
	}
	
	@RequestMapping(value="delete/{id}", method=RequestMethod.POST)
	public String doDeleteAlbum(@PathVariable("id")Integer id, Model model, RedirectAttributes redirectAttributes){
		SessionMessage sessionMessage;
		
		Album album = albumService.findById(id);
		if(album != null){
			albumService.deleteAlbum(album);
			sessionMessage = new SessionMessage("The album was successfully deleted.");
			sessionMessage.setSuccessClass();
		}else{
			sessionMessage = new SessionMessage("Could not find the album.");
			sessionMessage.setDangerClass();
		}
		
		redirectAttributes.addFlashAttribute("sessionMessage",sessionMessage);
		
		return "redirect:/public/albums";
	}
	
}
