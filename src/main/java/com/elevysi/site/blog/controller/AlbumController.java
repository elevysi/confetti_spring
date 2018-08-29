package com.elevysi.site.blog.controller;

import java.util.ArrayList;
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

import com.elevysi.site.blog.entity.Album;
import com.elevysi.site.blog.entity.Dossier;
import com.elevysi.site.blog.entity.Upload;
import com.elevysi.site.blog.form.AlbumEdit;
import com.elevysi.site.blog.service.AlbumService;
import com.elevysi.site.blog.service.DossierService;
import com.elevysi.site.blog.service.UploadService;
import com.elevysi.site.commons.pojo.SessionMessage;
import com.elevysi.site.commons.pojo.Page.SortDirection;
import com.elevysi.site.blog.entity.Dossier_;

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
		
		com.elevysi.site.commons.pojo.Page dossiersPage = dossierService.buildOffsetPage(FIRST_PAGE, DEFAULT_ALL_ITEMS, Dossier_.created, SortDirection.DESC);		
		List<Dossier> dossiers = dossierService.getDossiers(dossiersPage);
		model.addAttribute("dossiers", dossiers);
		
		return "albumAdd";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doAdd(Model model, @ModelAttribute("album")Album album, BindingResult result, RedirectAttributes redirectAttributes){
		
//		if(result.hasErrors()){
//			return "redirect:/albums/add";
//		}
//		
//		/**
//		 * Record the post to the profile Editing it
//		 */
//		Profile owningProfile = albumService.getActiveProfile();
//		album.setProfileOwner(owningProfile);
//		
//		album.setLinkTable("profiles");
//		album.setLinkId(owningProfile.getId());
//		
//		if(album.getDossier().getId() == null){
//			album.setDossier(null);
//		}
//		
//		Album savedAlbum = albumService.saveAlbum(album);
//		
//		/**
//		 * Find the lists of uploads related to this
//		 */
//		
//		SessionMessage sessionMessage = new SessionMessage("The album was successfully saved!");
//		sessionMessage.setSuccessClass();
//		
//		redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
		return "redirect:/profile";
		
	}
	
	@RequestMapping(value={"/view/{id}/*", "/view/{id}"})
	public String view(@PathVariable("id")Integer id, Model model, RedirectAttributes redirectAttributes, @RequestParam(defaultValue="1", required=false, value="page")Integer pageNumber){
		
//		Album album = albumService.findOne(id);
//		if(album != null){
//			model.addAttribute("album", album);
//			model.addAttribute("pageTitle", album.getName());
//			model.addAttribute("pageDescription", album.getDescription());
//			
//			List<Upload> albumUploads = uploadService.getAllAlbumUploads(album);
//			
//			boolean canEditAlbum = false;
//			Profile albumProfile = album.getProfileOwner();
//			if(albumProfile!= null && albumProfile.equals(albumService.getActiveProfile())) canEditAlbum = true;
//			
//			model.addAttribute("albumUploads", albumUploads);
//			model.addAttribute("canEditAlbum", canEditAlbum);
//			return "albumView";
//		}else{
//			SessionMessage sessionMessage = new SessionMessage("The album was successfully saved!");
//			sessionMessage.setSuccessClass();
//			
//			redirectAttributes.addFlashAttribute("sessionMessage", sessionMessage);
//			return "redirect:/profile";
//		}
		
		return "redirect:/profile";
	}
	
	@RequestMapping(value={"/edit/{id}", "/edit/{id}/*"}, method=RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Integer id){
		Album album = albumService.findById(id);
		model.addAttribute("album", album);
		if(album != null){
			model.addAttribute("album", album);
			
//			List<Upload> albumUploads = albumService.getPaginatedAlbumUploads(album, FIRST_PAGE, NO_ALBUM_UPLOADS, SORT_FIELD, SORT_DIRECTION);
			List<Upload> albumUploads = uploadService.getAllAlbumUploads(album);
			model.addAttribute("albumUploads", albumUploads);
			
			AlbumEdit albumEdit = new AlbumEdit();
			List<Integer> selected = new ArrayList<Integer>();
			for(Upload albumUpload : albumUploads){
				selected.add(albumUpload.getId());
			}
			albumEdit.setName(album.getName());
			albumEdit.setDescription(album.getDescription());
			albumEdit.setPlace(album.getPlace());
			albumEdit.setPublicAlbum(album.getPublicAlbum());
//			albumEdit.setDossier(album.getDossier());
			albumEdit.setUploads(selected);
			albumEdit.setId(album.getId());
			albumEdit.setUuid(album.getUuid());
			
			model.addAttribute("albumEdit", albumEdit);
			
			com.elevysi.site.commons.pojo.Page dossiersPage = dossierService.buildOffsetPage(FIRST_PAGE, DEFAULT_ALL_ITEMS, Dossier_.created, SortDirection.DESC);		
			List<Dossier> dossiers = dossierService.getDossiers(dossiersPage);
			model.addAttribute("dossiers", dossiers);
		}
		return "albumEdit";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String doEdit(Model model, @Valid @ModelAttribute("abumEdit")AlbumEdit albumEdit, BindingResult result, RedirectAttributes redirectAttributes){
//		if(result.hasErrors()){
//			return "redirect:/albums/edit/"+album.getId();
//		}
		
		Album originalAlbum = albumService.findOne(albumEdit.getId());
		originalAlbum.setPublicAlbum(albumEdit.getPublicAlbum());
		originalAlbum.setName(albumEdit.getName());
		originalAlbum.setPlace(albumEdit.getPlace());
		
		if(albumEdit.getDossier().getId() == null){
			albumEdit.setDossier(null);
//			originalAlbum.setDossier(null);
		}else{
//			originalAlbum.setDossier(albumEdit.getDossier());
		}
		
		/**
		 * Find all this album uploads and set their display to false
		 */
		uploadService.setAllAlbumUploads(originalAlbum, false);
		
		/**
		 * Check which uploads have been kept or not
		 */
		List<Integer> selectedUploads = albumEdit.getUploads();
		if(selectedUploads != null){
			for(Integer upload_id : selectedUploads){
				uploadService.updateUploadForDisplay(upload_id, true);
			}
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
		return "redirect:/albums/view/"+albumEdit.getId()+"/";
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
