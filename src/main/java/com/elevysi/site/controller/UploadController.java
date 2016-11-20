package com.elevysi.site.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.elevysi.site.entity.MediaKind;
import com.elevysi.site.entity.Profile;
import com.elevysi.site.entity.ProfileType;
import com.elevysi.site.entity.Upload;
import com.elevysi.site.entity.Upload_;
import com.elevysi.site.entity.User;
import com.elevysi.site.form.EmbeddedMedia;
import com.elevysi.site.form.SavedMedia;
import com.elevysi.site.pojo.OffsetPage;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.pojo.ReturnValue;
import com.elevysi.site.security.ActiveUser;
import com.elevysi.site.service.MediaKindService;
import com.elevysi.site.service.ProfileService;
import com.elevysi.site.service.ProfileTypeService;
import com.elevysi.site.service.UploadService;
import com.elevysi.site.service.UserService;

@Controller
@RequestMapping("/uploads")
public class UploadController extends AbstractController{
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ProfileTypeService profileTypeService;
	
	@Autowired
	private MediaKindService mediaKindService;

	@RequestMapping(value="/album", method= RequestMethod.GET)
	public String album(){
		
		
		
		return "upload-album";
	}
	
	
	
	
	@RequestMapping(value="/profile", method= RequestMethod.GET)
	public String profilePic(Model model){
		
		Profile actingProfile = userService.getActiveProfile();
		model.addAttribute("actingProfile", actingProfile);
		model.addAttribute("profile", actingProfile);
		
		Profile requestingProfile = userService.getActiveProfile();
		model.addAttribute("requestingProfile", requestingProfile);
		
		return "profilePicUpload";
	}
	
	@RequestMapping(value="/profile", method= RequestMethod.POST)
	public @ResponseBody ReturnValue uploadProfilePicture(MultipartHttpServletRequest request, HttpServletResponse response, Principal principal) throws Exception{
	    
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(0);
		returnValue.setMessage("Failed to Upload the file.");
		
		Upload upload = null;
		
	    //1. Build an Iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		
		//2. get each file
		while(itr.hasNext()){
			//2.1 Get Next Multipart File
			mpf = request.getFile(itr.next());
			
			
			
			//2.3 Create new Upload
			
			/**
			 * Check if Profile has a profile Picture Already
			 */
			
			Profile requestedProfile = userService.getActiveProfile();
			String fileName = mpf.getOriginalFilename();
			
			if(requestedProfile.getProfilePicture().iterator().hasNext()){
				upload = requestedProfile.getProfilePicture().iterator().next();
			}
			
			
			
			if(upload == null){
				upload = new Upload();				
			}
			
			upload.setFilename(fileName);
			upload.setFilesize((int)mpf.getSize());
			upload.setFilemine(mpf.getContentType());
			upload.setOwningProfilePicture(requestedProfile);
			upload.setLinkTable("profilePicture");
			
			String uploadKey = this.generateUUID();
			upload.setKeyIdentification(uploadKey);
			
			upload.setAltText("profilePicture");
			
			Long timeofUpload  = System.currentTimeMillis();
			
			
			
			try{
				
				
				String avatarDirPath = this.avatarUploadPath+"profiles"+this.ds+requestedProfile.getId()+this.ds+"avatar"+this.ds+timeofUpload+this.ds;
				File avatarDir = new File(avatarDirPath);
				if (!avatarDir.exists()) {
					if (avatarDir.mkdirs()) {
						
						String saveRelativePath = "profiles"+this.ds+requestedProfile.getId()+this.ds+"avatar"+this.ds+timeofUpload+this.ds+fileName;
						upload.setPath(saveRelativePath);
						uploadService.saveImage(mpf, avatarDirPath+fileName);
					} else {
						
					}
				}
				
				
				
				uploadService.saveUpload(upload);
				
				upload.setOwningProfilePicture(requestedProfile);
				upload.setLinkTable("profilePicture");
				uploadService.saveUpload(upload);
				
				returnValue.setCode(1);
				returnValue.setMessage("The file was successfully uploaded.");
				returnValue.setExtra("");
				
				
				/**
				 * Reload the Profile User to display data
				 */
				userService.reloadCurrentProfile();
				
			}catch(Exception e){
				throw e;
			}			
			
			
		}
		
		return returnValue;
	}
	
	@RequestMapping(value="/post", method= RequestMethod.POST)
	public @ResponseBody ReturnValue uploadPostPicture(MultipartHttpServletRequest request, HttpServletResponse response, Principal principal, @RequestParam("uuid")String uuid, @RequestParam(value="type", required=false, defaultValue="posts")String type) throws Exception{
	    
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(0);
		returnValue.setMessage("Failed to Upload the file.");
		
		Upload upload = new Upload();
		
	    //1. Build an Iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		
		//2. get each file
		while(itr.hasNext()){
			mpf = request.getFile(itr.next());
			String fileName = mpf.getOriginalFilename();
			upload.setFilename(fileName);
			upload.setFilesize((int)mpf.getSize());
			upload.setFilemine(mpf.getContentType());
			upload.setLinkTable(type);
			upload.setUuid(uuid);
			upload.setDisplay(true);
			
			String uploadKey = this.generateUUID();
			upload.setKeyIdentification(uploadKey);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			boolean isAuthenticated =  SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
			
			if(auth != null && isAuthenticated){
				ActiveUser activeUser = (ActiveUser)auth.getPrincipal();
				Profile owningProfile = activeUser.getActiveProfile();
				upload.setUploadOwner(owningProfile);
			}
			
			
			Long timeofUpload  = System.currentTimeMillis();
			
			try{
				String avatarDirPath = this.avatarUploadPath+"posts"+this.ds+uuid+this.ds+"image"+this.ds+timeofUpload+this.ds;
				File avatarDir = new File(avatarDirPath);
				if (!avatarDir.exists()) {
					if (avatarDir.mkdirs()) {
						
						
					} else {
						
					}
				}
				
				String saveRelativePath = "posts"+this.ds+uuid+this.ds+"image"+this.ds+timeofUpload+this.ds+fileName;
				upload.setPath(saveRelativePath);
				uploadService.saveImage(mpf, avatarDirPath+fileName);
				
				
				Upload savedUpload = uploadService.saveUpload(upload);
				if(savedUpload != null){
					returnValue.setExtra(savedUpload.getId().toString());
				}
				returnValue.setCode(1);
				returnValue.setMessage("The file was successfully uploaded.");
				
			}catch(Exception e){
				throw e;
			}			
			
			
		}
		
		return returnValue;
	}
	
	@RequestMapping(value="/upload")
	public @ResponseBody LinkedList<Upload> upload(MultipartHttpServletRequest request, HttpServletResponse response, Principal principal) throws Exception{
		
		LinkedList<Upload> files = new LinkedList<Upload>();
	    Upload upload = null;
		
	    //1. Build an Iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		
		//2. get each file
		while(itr.hasNext()){
			//2.1 Get Next Multipart File
			mpf = request.getFile(itr.next());
			
			//2.2 if files > 10 remove the first from list
			if(files.size() > 10)
				files.pop();
			
			//2.3 Create new Upload
			String fileName = mpf.getOriginalFilename();
			upload = new Upload();
			upload.setFilename(fileName);
			upload.setFilesize((int)mpf.getSize());
			upload.setFilemine(mpf.getContentType());
			
			String uploadKey = this.generateUUID();
			upload.setKeyIdentification(uploadKey);
			
//			String username = principal.getName();
//			User domainUser = userService.getUser(username);
			
			uploadService.saveUpload(upload);
			try{
				String path = this.avatarUploadPath + fileName;
				uploadService.saveImage(mpf, path);
			}catch(Exception e){
				throw e;
			}
			
			
			//2.4 add to files
			files.add(upload);
			
			
		}
		
		return files;
		
			
		
	}
	
	/***************************************************
	     * URL: /rest/controller/get/{value}
	     * get(): get file as an attachment
	     * @param response : passed by the server
	     * @param value : value from the URL
	     * @return void
	     ****************************************************/
	
	
	@RequestMapping(value="/get", method= RequestMethod.GET)
	public ResponseEntity<byte[]> getUpload() throws IOException {
//	    InputStream in = servletContext.getResourceAsStream("/Users/elvishatungimana/Documents/spring_uploads/avatar/passport.jpg");
	    InputStream in = new FileInputStream(this.avatarUploadPath+"passport.jpg");
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);

	    return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
	}
	
	
	
//	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
//	public void get(HttpServletResponse response,@PathVariable String value){
//	FileMeta getFile = files.get(Integer.parseInt(value));
//	         try {      
//	          response.setContentType(getFile.getFileType());
//	response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
//	FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
//	         }catch (IOException e) {
//				e.printStackTrace();
//	         }
//	     }
	
	
	@RequestMapping(value="/manage/{uuid}")
	public String managePostUploads(@PathVariable("uuid") String uuid, Model model, @RequestParam(value="type", required=false, defaultValue="posts")String type){
		model.addAttribute("uuid", uuid);
		model.addAttribute("type", type);
		return "managePostUploads";
	}
	
	@RequestMapping(value = "embeddedMedia/{uuid}", method = RequestMethod.GET)
	public String embeddedMedia(Model model, @PathVariable("uuid")String uuid, @RequestParam(value="type", required=false, defaultValue="posts")String type){
		EmbeddedMedia embeddedMedia = new EmbeddedMedia();
		embeddedMedia.setUuid(uuid);
		
		List<MediaKind> allMediaKinds = mediaKindService.findAll();
		Map<Integer, String> mediaKinds = new HashMap<Integer, String>();
		for (MediaKind mediaKind : allMediaKinds) {
			mediaKinds.put(mediaKind.getId(), mediaKind.getName());
		}
		
		model.addAttribute("mediaKinds", mediaKinds);
		model.addAttribute("embeddedMedia", embeddedMedia);
		model.addAttribute("type", type);
		return "embeddedMedia";
		
	}
	
	
	@RequestMapping(value="embeddedMediaForm", method=RequestMethod.POST)
	public @ResponseBody ReturnValue doEmbeddedMediaForm(@ModelAttribute("embeddedMedia")EmbeddedMedia embeddedMedia, BindingResult result, @RequestParam(value="type", required=false, defaultValue="posts")String type){
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(0);
		if(result.hasErrors()){
			returnValue.setMessage("Validation Errors!");
		}
		
		Upload upload = new Upload();
		upload.setUrl(embeddedMedia.getUrl());
		MediaKind mediaKind = mediaKindService.findOneWithId(embeddedMedia.getMediaKind());
		upload.setMediaType(mediaKind);
		upload.setDisplay(true);
		upload.setUuid(embeddedMedia.getUuid());
		upload.setFilename(embeddedMedia.getUrl());
		upload.setLinkTable(type);
		String uploadKey = this.generateUUID();
		upload.setKeyIdentification(uploadKey);
		
		
		
		/**
		 * Set profile Owner
		 */
		/**
		 * Record the upload to the profile and User creating it
		 */
		
		Profile owningProfile = userService.getActiveProfile();
		upload.setUploadOwner(owningProfile);
		
		uploadService.saveUpload(upload);
		
		returnValue.setCode(1);
		returnValue.setMessage("Successfully added");
		
		
		
		
		
		return returnValue;
	}
	
	@RequestMapping(value="savedMedia/{uuid}", method=RequestMethod.GET)
	public String savedMedia(@RequestParam(value="page", defaultValue="1", required=false)int pageIndex, @PathVariable("uuid") String uuid, Model model, @RequestParam(required = false) String message, @RequestParam(value="type", required=false, defaultValue="posts")String type){
		/**
		 * Find all uploads related to post
		 */
		List<Integer> selected = new ArrayList<Integer>();
		List<Upload> uploads = uploadService.findAllWithUuid(uuid);
		Map<Integer, String> uploadsMap = new HashMap<Integer, String>(); 
		
		for (Upload upload : uploads) {
			uploadsMap.put(upload.getId(), upload.getFilename());
			
			if(upload.isDisplay()){
				selected.add(upload.getId());
			}
			
			/**
			 * Set to false to allow selection save only the displayed ones
			 */
			upload.setDisplay(false);
			String uploadKey = this.generateUUID();
			upload.setKeyIdentification(uploadKey);
			uploadService.saveUpload(upload);
			
		}
		
		/**
		 * Find all uploads owned by profile
		 */
		
		Profile owningProfile = userService.getActiveProfile();
		
		OffsetPage page = uploadService.buildOffsetPage(pageIndex, DEFAULT_NO_ITEMS, Upload_.created, SortDirection.DESC);
		List<Upload> profileUploads = uploadService.findPaginatedProfileUploads(owningProfile, page);
		long totalRecords = page.getTotalRecords();
		int totalPages = Math.round(totalRecords / DEFAULT_NO_ITEMS);
		
//		List<Upload> profileUploads = uploadService.findProfileUploads(owningProfile);
		List<Upload> filteredProfileUploads = new ArrayList<Upload>();
		Map<Integer, String> profileUploadsMap = new HashMap<Integer, String>(); 
		
		for (Upload upload : profileUploads) {
			
			String upName = uploadsMap.get(upload.getId());
			
			if(upName == null){
				profileUploadsMap.put(upload.getId(), upload.getFilename());
				
				filteredProfileUploads.add(upload);
			}
			
			
		}
				 
		
		SavedMedia savedMedia = new SavedMedia();
//		Map uploads2 = savedMedia.sampleUploads();
		
		savedMedia.setUploads(selected);
		savedMedia.setUuid(uuid);
		 
		model.addAttribute("savedUploads", uploadsMap);
		model.addAttribute("profileSavedUploads", profileUploadsMap);
		model.addAttribute("savedMedia", savedMedia);
		
		model.addAttribute("uploads", uploads);
		model.addAttribute("profileUploads", filteredProfileUploads);
		
		model.addAttribute("message", message);
		model.addAttribute("type", type);
		
		return "savedMedia";
	}
	
	@RequestMapping(value="savedMedia", method=RequestMethod.POST)
	public @ResponseBody ReturnValue doSavedMediaForm(@ModelAttribute("savedMedia") SavedMedia savedMedia, @RequestParam(value="type", required=false, defaultValue="posts")String type){
		
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(1);
		List<Integer> selectedUploads = savedMedia.getUploads();
		List<Integer> selectedoldUploads = savedMedia.getOldUploads();
		
		boolean foundOneToAdd = false;
		
		if(selectedUploads != null){
			
			foundOneToAdd = true;
			
			for (Integer upload_id : selectedUploads) {
				Upload concernedUpload = uploadService.findOne(upload_id);
				concernedUpload.setDisplay(true);
				concernedUpload.setLinkTable(type);
				uploadService.saveUpload(concernedUpload);
			}			
		}
		
		if(selectedoldUploads != null){
			
			foundOneToAdd = true;
			
			for (Integer upload_id : selectedoldUploads) {
				Upload concernedUpload = uploadService.findOne(upload_id);
				concernedUpload.setLinkTable(type);
				/**
				 * Remove ID, uuid and keyIdentification to set as a fresh copy
				 */
				Upload newUploadValue = concernedUpload.createDuplicate();
				
				newUploadValue.setUuid(savedMedia.getUuid());
				newUploadValue.setDisplay(true);
				uploadService.saveUpload(newUploadValue);
			}
			
			
		}
		
		if(!foundOneToAdd){
			returnValue.setMessage("No selected items !");
		}else{
			returnValue.setMessage("The selected items will be displayed within the Post.");
		}
		
		
		return returnValue;
	}
	
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(Model model, @RequestParam("key") String key, HttpServletResponse response, @RequestParam(value="inline", required=false) Boolean inline) throws Exception {
		
		Upload upload = uploadService.findOne(key);
		
		if(upload != null){
					
				String contentType = upload.getFilemine();
				
				String filename = upload.getFilename();
				
		        // Stream the image
		        BufferedInputStream in = null;
		        try
		        {
		        	String uploadPath = upload.getPath();
		        	String finalPath = upload.getPath();
		        	if(uploadPath != null){
		        		/**
		        		 * Check which env this is
		        		 */
		        		if(uploadPath.contains(this.oppositeDS)){
		        			finalPath = uploadPath.replace(this.oppositeDS, this.ds);
		        			
		        		}
		        	}
		            in = new BufferedInputStream(new FileInputStream(this.avatarUploadPath+finalPath));
		
		            response.setContentType(contentType);
		            if(inline == null){
		            	response.setHeader("Content-Disposition",  " inline; filename=" + filename);
		            	
		            	/**
		            	 * Force download put attachment in header instead
		            	 */
		            	
		            	//response.setHeader("Content-Disposition",  " attachment; filename=" + filename);
		            }
		            
		            response.setContentLength(upload.getFilesize());
		            
		            response.setStatus(HttpServletResponse.SC_OK);
		            IOUtils.copy(in, response.getOutputStream());
		            response.flushBuffer();
		            
		        }
		        catch(Exception e)
		        {
		           e.printStackTrace();
		           return;
		        }
		        finally
		        {
		            try
		            {
		                in.close();
		            }
		            catch(Exception ee)
		            {
		                ee.printStackTrace();
		            }
		        }
		}
	}
	
	
	
	
	@RequestMapping(value = "get/{key}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<InputStreamResource> downloadUserAvatarImage(@PathVariable String key) {
	    

	    Upload upload = uploadService.findOne(key);
	    if(upload != null){
	    	try {
				return ResponseEntity.ok()
				        .contentLength(upload.getFilesize())
				        .contentType(MediaType.parseMediaType(upload.getFilemine()))
				        .body(new InputStreamResource(new FileInputStream(this.avatarUploadPath+upload.getPath())));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	    return null;
	    
	}
	
	
	@RequestMapping(value="newMedia/{uuid}", method = RequestMethod.GET)
	public String newMediaTab(@PathVariable("uuid")String uuid, Model model, @RequestParam(value="type", required=false, defaultValue="posts")String type){
		
		model.addAttribute("uuid", uuid);
		model.addAttribute("type", type);
		
		return "newMediaTab";
	}
	
	@RequestMapping(value="media")
	public String listProfileMedia(Model model){
		
		Profile owningProfile = userService.getActiveProfile();
		List<Upload> profileUploads = uploadService.findProfileUploads(owningProfile);
		
		model.addAttribute("profileUploads", profileUploads);
		
		return "profileMediaList";
	}
	
	@RequestMapping(value="/album/add", method=RequestMethod.POST)
	public @ResponseBody ReturnValue uploadImageToAlbum(MultipartHttpServletRequest request, HttpServletResponse response, Principal principal, @RequestParam("uuid")String uuid) throws Exception{
		
		ReturnValue returnValue = new ReturnValue();
		returnValue.setCode(0);
		returnValue.setMessage("Failed to Upload the file.");
		
		LinkedList<Upload> files = new LinkedList<Upload>();
	    //1. Build an Iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		
		//2. get each file
		while(itr.hasNext()){
			//2.1 Get Next Multipart File
			mpf = request.getFile(itr.next());
			
			//2.2 if files > 10 remove the first from list
			if(files.size() > 10)
				files.pop();
			
			//2.3 Create new Upload
			
			Upload savedUpload = uploadService.uploadImageToItem(mpf, uuid, "albums");
			if(savedUpload != null){
				files.add(savedUpload);
			}
		}
		
		if(files.size() > 0){
			returnValue.setCode(1);
			returnValue.setMessage("The file was successfully added!.");
		}
		
		return returnValue;
	}
	
}
