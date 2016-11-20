package com.elevysi.site.form;

import java.util.List;
import com.elevysi.site.entity.Dossier;

public class AlbumEdit {
	
	private List<Integer> uploads;
	private List<Integer> oldUploads;
	
	private Integer id;
	private String name;
	private String description;
	private String place;
	private Dossier dossier;
	private Boolean publicAlbum;
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public List<Integer> getUploads() {
		return uploads;
	}
	public void setUploads(List<Integer> uploads) {
		this.uploads = uploads;
	}
	public List<Integer> getOldUploads() {
		return oldUploads;
	}
	public void setOldUploads(List<Integer> oldUploads) {
		this.oldUploads = oldUploads;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Dossier getDossier() {
		return dossier;
	}
	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}
	public Boolean getPublicAlbum() {
		return publicAlbum;
	}
	public void setPublicAlbum(Boolean publicAlbum) {
		this.publicAlbum = publicAlbum;
	}

}
