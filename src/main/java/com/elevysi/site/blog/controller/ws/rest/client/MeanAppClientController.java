package com.elevysi.site.blog.controller.ws.rest.client;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.elevysi.site.commons.dto.MeanSnapDTO;

import javassist.NotFoundException;

/**
 * Used to get the pictures from the MEAN App
 * @author elvis
 *
 */
@RestController
//@Controller
@RequestMapping("/ui/public/rest/consumer/mean")
public class MeanAppClientController {
	
	private static String UPLOADBASE = "/media/elvis/DataHDD1/Projects/NodeJSProjectsGit/NodeJSProjects/laLifeApp/client/";
	
	@RequestMapping("/snaps")
	public MeanSnapDTO[] getSnaps() throws NotFoundException{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MeanSnapDTO[]> response = restTemplate.getForEntity("http://localhost:9090/api/snaps", MeanSnapDTO[].class);
		
		if(response.getStatusCode() != HttpStatus.OK) throw new NotFoundException("");
		
		return response.getBody();
	}
	
	@RequestMapping(value = "/download/{path}/{mime}/{fileName}", method = RequestMethod.GET)
	public void download(
			@PathVariable("path") String path, 
			@PathVariable("mime") String mime,
			@PathVariable("fileName") String fileName,
			@PathVariable("size") int size,
			HttpServletResponse response) throws Exception {
		
		
		// Stream the image
        BufferedInputStream in = null;
        try
        {
        	
        	File downloadFile = new File(UPLOADBASE+path);
        	if(downloadFile.exists() && downloadFile.isFile()){
        		
	            in = new BufferedInputStream(new FileInputStream(downloadFile));
	
	            response.setContentType(mime);
	            	response.setHeader("Content-Disposition",  " inline; filename=" + fileName);
	            	
	            	/**
	            	 * Force download put attachment in header instead
	            	 */
	            	
	            	//response.setHeader("Content-Disposition",  " attachment; filename=" + filename);
	            
	            response.setContentLength(size);
	            
	            response.setStatus(HttpServletResponse.SC_OK);
	            IOUtils.copy(in, response.getOutputStream());
	            response.flushBuffer();
        	}
            
        }
        catch(Exception e)
        {
           return;
        }
		
	}

}
