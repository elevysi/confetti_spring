package com.elevysi.site.blog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.elevysi.site.commons.dto.ProfileDTO;

//escape table name because like is a reserved word in Query Language
@Entity
@Table(name = "\"likes\"")
public class Like implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1080082379187458652L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="profile_id")
	@Transient
	private ProfileDTO likeOwner;
	
	@Column(name="item_id")
	private Integer itemId;
	
	@Column(name="item_type")
	private String itemType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id", insertable = false, updatable = false)
	private Post likedPost;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id", insertable = false, updatable = false)
	private Upload likedUpload;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id", insertable = false, updatable = false)
	private Album likedAlbum;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="item_id", insertable = false, updatable = false)
	@Transient
	private ProfileDTO likedProfile;
	
	
	@Column(name = "created", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name = "modified", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProfileDTO getLikeOwner() {
		return likeOwner;
	}

	public void setLikeOwner(ProfileDTO likeOwner) {
		this.likeOwner = likeOwner;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Post getLikedPost() {
		return likedPost;
	}

	public void setLikedPost(Post likedPost) {
		this.likedPost = likedPost;
	}

	public Upload getLikedUpload() {
		return likedUpload;
	}

	public void setLikedUpload(Upload likedUpload) {
		this.likedUpload = likedUpload;
	}

	public Album getLikedAlbum() {
		return likedAlbum;
	}

	public void setLikedAlbum(Album likedAlbum) {
		this.likedAlbum = likedAlbum;
	}

	public ProfileDTO getLikedProfile() {
		return likedProfile;
	}

	public void setLikedProfile(ProfileDTO likedProfile) {
		this.likedProfile = likedProfile;
	}
	
//	public Like(Integer itemId, String itemType){
//		
//		this.itemId = itemId;
//		this.itemType = itemType;
//		
//	}
	
	public Like(){
		
	}
	
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Like))
            return false;
 
        final Like like = (Like)object;
 
        if (id != null && like.getId() != null) {
            return id.equals(like.getId());
        }
        return false;
    }
	

}
