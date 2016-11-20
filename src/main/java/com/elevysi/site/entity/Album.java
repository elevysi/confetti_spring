package com.elevysi.site.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;

@Entity
@Table(name = "albums")
@FilterDefs({
	@FilterDef(name="postAlbum", defaultCondition="link_table = 'posts'"),
	@FilterDef(name="profileAlbum", defaultCondition="link_table = 'profiles'")
})
@org.hibernate.annotations.Filter(name="profileAlbum")
public class Album implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5623560970761577249L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private String place;
	
	private String uuid;
		
	@Column(columnDefinition="INT(1)")
	private Boolean publicAlbum;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publication_id")
	private Publication publication;
	
	@Column(name="count_comments")
	private Integer commentCount;
	
	@Column(name="count_likes")
	private Integer likeCount;
	
	
	@Column(name="count_shares")
	private Integer shareCount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dossier_id", referencedColumnName = "id")
	private Dossier dossier;
	
	public Dossier getDossier() {
		return dossier;
	}


	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}
	
	public void setDossier(Dossier dossier, boolean add) {
		this.dossier = dossier;
		if(dossier != null && add){
			dossier.addAlbum(this, false);
		}
		
	}
	
	
	public Integer getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}


	public Integer getShareCount() {
		return shareCount;
	}


	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}


	public Integer getCommentCount() {
		return commentCount;
	}


	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	
	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
	
	public Boolean getPublicAlbum() {
		return publicAlbum;
	}

	public void setPublicAlbum(Boolean publicAlbum) {
		this.publicAlbum = publicAlbum;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "created", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name = "modified", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	@Column(name = "dateofshot", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateofshot;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "album", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@org.hibernate.annotations.Filter(name="itemTableIs", condition="link_table=:link_tableValue and display=:displayValue")
	private Set<Upload> uploads = new HashSet<Upload>();
	
	public Set<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(Set<Upload> uploads) {
		this.uploads = uploads;
	}

	public void addUpload(Upload upload){
		addUpload(upload, true);
	}
	
	public void addUpload(Upload upload, boolean set){
		if(upload != null){
			
			if(this.getUploads().contains(upload)){
				this.getUploads().remove(upload);
				this.getUploads().add(upload);
				
			}else{
				getUploads().add(upload);
			}
			
			if(set){
				upload.setAlbum(this, false);
			}
		}
	}
	
	public void removeUpload(Upload upload){
		getUploads().remove(upload);
		upload.setAlbum(null);
	}
	
	/**
	 * Cannot make it eager because of link_id is at the same type Profile, Post, ...
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="link_id", nullable = false, insertable = false, updatable = false)
	private Profile profileOwner;
	
	@Column(name="link_table")
	private String linkTable;
	
	@Column(name="link_id")
	private Integer linkId;

	

	public String getLinkTable() {
		return linkTable;
	}

	public void setLinkTable(String linkTable) {
		this.linkTable = linkTable;
	}

	public Integer getLinkId() {
		return linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getDateofshot() {
		return dateofshot;
	}

	public void setDateofshot(Date dateofshot) {
		this.dateofshot = dateofshot;
	}

	

	public Profile getProfileOwner() {
		return profileOwner;
	}

	public void setProfileOwner(Profile profileOwner) {
		this.profileOwner = profileOwner;
	}

	
	public String generateUUID(){
		 UUID uniqueKey = UUID.randomUUID();   
		 return uniqueKey.toString();
		 
	}
	
	
	
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Album))
            return false;
 
        final Album album = (Album)object;
 
        if (id != null && album.getId() != null) {
            return id.equals(album.getId());
        }
        return false;
    }
	
}
