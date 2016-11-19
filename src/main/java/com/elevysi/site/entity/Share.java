package com.elevysi.site.entity;

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

@Entity
@Table(name="shares")
public class Share {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="item_type")
	private  String itemType;
	
	@Column(name="item_id")
	private Integer itemId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id", insertable = false, updatable = false)
	private Post sharedPost;
	
	@Column(name = "created", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "modified", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="profile_id")
	private Profile shareOwner;
	

	public Profile getShareOwner() {
		return shareOwner;
	}

	public void setShareOwner(Profile shareOwner) {
		this.shareOwner = shareOwner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Post getSharedPost() {
		return sharedPost;
	}

	public void setSharedPost(Post sharedPost) {
		this.sharedPost = sharedPost;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
}
