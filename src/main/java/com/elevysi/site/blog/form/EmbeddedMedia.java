package com.elevysi.site.blog.form;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

//@Entity
public class EmbeddedMedia {
	
	@NotNull(message ="The URL cannot be left empty")
	@URL(message="Please give a valid URL")
	private String url;
	
	@NotNull(message = "The media type cannot be left empty")
	private Integer mediaKind;
	
	
	public Integer getMediaKind() {
		return mediaKind;
	}

	public void setMediaKind(Integer mediaKind) {
		this.mediaKind = mediaKind;
	}

	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
