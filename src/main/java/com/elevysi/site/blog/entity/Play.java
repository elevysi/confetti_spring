package com.elevysi.site.blog.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.elevysi.site.commons.dto.ProfileDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "plays")
public class Play extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min=3, message="URL must be at least 3 characters")
	@Column(nullable = false)
	private String url;
	
	private String embeddedUrl;

	@JsonIgnore
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="publication_id")
	private Publication publication;
	
	private String description;
	
	@Size(min=3, message="URL must be at least 3 characters")
	@Column(nullable = false)
	private String title;
	
	@OneToMany(mappedBy = "play", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@org.hibernate.annotations.Filter(name="commentItemIs", condition="item_type=:item_typeValue")
	private Set<Comment> comments = new HashSet<Comment>();
	
	@Column(name="profile_id")
	private long profileID;
	
	@Column(name="count_comments")
	private Integer commentCount;
	
	@Column(name="count_likes")
	private Integer likeCount;
	
	
	@Column(name="count_shares")
	private Integer shareCount;

	
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

	public String getEmbeddedUrl() {
		return embeddedUrl;
	}

	public void setEmbeddedUrl(String embeddedUrl) {
		this.embeddedUrl = embeddedUrl;
	}
	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PlayType getPlayType() {
		return playType;
	}

	public void setPlayType(PlayType playType) {
		this.playType = playType;
	}

	@Transient
	private ProfileDTO playProfile;
	
	@ManyToOne
	@JoinColumn(name="type", referencedColumnName="id")
	private PlayType playType;
	
	public ProfileDTO getPlayProfile() {
		return playProfile;
	}

	public void setPlayProfile(ProfileDTO playProfile) {
		this.playProfile = playProfile;
	}

	@Column(columnDefinition="INT(1)")
	private Boolean featured;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getFeatured(){
		return this.featured;
	}
	
	public void setFeatured(Boolean featured){
		this.featured = featured;
	}
	
	public long getProfileID() {
		return profileID;
	}


	public void setProfileID(long profileID) {
		this.profileID = profileID;
	}
	

}
