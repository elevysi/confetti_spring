package com.elevysi.site.blog.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elevysi.site.blog.entity.Upload;

public class SavedMedia {
	
	private List<Integer> uploads;
	
	private List<Integer> oldUploads;
	
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<Integer> getOldUploads() {
		return oldUploads;
	}

	public void setOldUploads(List<Integer> oldUploads) {
		this.oldUploads = oldUploads;
	}

	public List<Integer> getUploads() {
		return uploads;
	}

	public void setUploads(List<Integer> uploads) {
		this.uploads = uploads;
	}
	
	public Map<Integer, String> sampleUploads(){
		
		List<Upload> returnList = new ArrayList<Upload>();
		Upload upload1 = new Upload();
		upload1.setId(1);
		upload1.setFilename("mno");
		returnList.add(upload1);
		
		Upload upload2 = new Upload();
		upload2.setId(1);
		upload2.setFilename("mno2");
		returnList.add(upload2);
		
		Map<Integer, String> uploads = new HashMap<Integer, String>();
		uploads.put(1, "My name");
		uploads.put(2, "My second name");
		
		return uploads;
		
	}
	
}
