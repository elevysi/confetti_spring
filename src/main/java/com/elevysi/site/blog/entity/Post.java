package com.elevysi.site.blog.entity;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.Where;




@Entity
@Table(name = "posts")
public class Post extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -101538975217987565L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min=1, max=255, message="Title must be between 1 and 255 characters")
	@Column(nullable = false)
	private String title;
	
	@Size(max=255, message = "Description must not be more than 255 characters")
	private String description;
	
//	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	@OneToMany(mappedBy = "post")
	@Filter(name="postTag")
	private Set<Tag> tags;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	private Profile profile;
	
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
			dossier.addPost(this, false);
		}
		
	}


	@Size(min=2, message="Content must be at least 2 characters")
	@Column(nullable = false)
	private String content;
	
	@Column(name = "created", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name = "modified", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
//	@OneToMany(mappedBy="owningPost", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
////	@Where(clause = "display=1")
////	@org.hibernate.annotations.Filter(
////			name="itemTableIs"
//////			,
//////			condition=":linkTable = 'postImage'"
////	)
////	@FilterJoinTable(name="itemTableIs")
//	private Upload postImage;
	
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "post", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@org.hibernate.annotations.Filter(name="itemTableIs", condition="link_table=:link_tableValue and display=:displayValue")
	private Set<Upload> uploads = new HashSet<Upload>();
	
	public Set<Upload> getUploads() {
		return uploads;
	}


	public void setUploads(Set<Upload> uploads) {
		this.uploads = uploads;
	}


	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publication_id")
	private Publication publication; 
	
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

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="post_categories",
        joinColumns = {@JoinColumn(name="post_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="category_id", referencedColumnName="id")}
    )
	private Set<Category> categories = new HashSet<Category>();
	
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}


	@OneToMany(mappedBy = "post", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@org.hibernate.annotations.Filter(name="commentItemIs", condition="item_type=:item_typeValue")
	private Set<Comment> comments = new HashSet<Comment>();
	
	@OneToMany(mappedBy = "likedPost", fetch = FetchType.LAZY)
	private Set<Like> likes = new HashSet<Like>();
	
	@OneToMany(mappedBy = "sharedPost", fetch = FetchType.LAZY)
	private Set<Share> shares = new HashSet<Share>();
	
	public Set<Share> getShares() {
		return shares;
	}

	public void setShares(Set<Share> shares) {
		this.shares = shares;
	}

	public Set<Comment> getComments() {
		return comments;
	}


	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}


	private String uuid;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", referencedColumnName = "id")
	private PostStatus postStatus;
	
	
	public PostStatus getPostStatus() {
		return postStatus;
	}


	public void setPostStatus(PostStatus postStatus) {
		this.postStatus = postStatus;
	}


	public Set<Tag> getTags() {
		return tags;
	}


	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}


	


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setEditableFields(Post editObject){
		
		this.setTitle(editObject.getTitle());
		this.setCategories(editObject.getCategories());
		this.setDescription(editObject.getDescription());
		this.setContent(editObject.getContent());
		
		
	}
	
	public Post(){
		
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
				upload.setPost(this, false);
			}
		}
	}
	
	public void removeUpload(Upload upload){
		getUploads().remove(upload);
	}


	
	
//	public Post (List<Category> categories){
//		this.categories = categories;
//	}
	
	
	
}
