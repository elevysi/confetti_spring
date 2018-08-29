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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.elevysi.site.commons.dto.ProfileDTO;
import com.elevysi.site.commons.pojo.PublicationMock;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="publications")
public class Publication extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="created", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name="modified", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	
	@OneToOne(mappedBy="publication", fetch=FetchType.LAZY)
	private Post post;
	
	@OneToOne(mappedBy="publication", fetch=FetchType.LAZY)
	private Play play;
	
	
	@OneToOne(mappedBy="publication", fetch=FetchType.LAZY)
	private Dossier dossier;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="publication_dossiers",
        joinColumns = {@JoinColumn(name="publication_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="dossier_id", referencedColumnName="id")}
    )
	private Set<Dossier> dossiers = new HashSet<Dossier>();
	
	
	@OneToMany(mappedBy = "publication", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@org.hibernate.annotations.Filter(name="commentItemIs", condition="item_type=:item_typeValue")
	private Set<Comment> comments = new HashSet<Comment>();
	
	private String profileName;
	
	
	/**
	 * Ignored because the Status is not Hibernate Initialized; it would throw an exception
	 * Not needed in JSON calls
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", referencedColumnName = "id")
	private PublicationStatus publicationStatus;
	
	/**
	 * Ignored because the Type is not Hibernate Initialized; it would throw an exception
	 * Not needed in JSON calls
	 */
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type")
	private PublicationType type;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="publication_categories",
        joinColumns = {@JoinColumn(name="publication_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="category_id", referencedColumnName="id")}
    )
	private Set<Category> categories = new HashSet<Category>();
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "publication", fetch=FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@org.hibernate.annotations.Filter(name="itemTableIs", condition="link_table=:link_tableValue and display=:displayValue")
	private Set<Upload> uploads = new HashSet<Upload>();
	
	public Set<Upload> getUploads() {
		return uploads;
	}


	public void setUploads(Set<Upload> uploads) {
		this.uploads = uploads;
	}
	
	
	public PublicationStatus getPublicationStatus() {
		return publicationStatus;
	}


	public void setPublicationStatus(PublicationStatus publicationStatus) {
		this.publicationStatus = publicationStatus;
	}
	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Play getPlay() {
		return play;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@OneToOne(cascade=CascadeType.ALL, mappedBy="publication", fetch=FetchType.LAZY)
	private Album album;
	
	@Column(name="public_publication", columnDefinition="INT(1)")
	private Boolean publicPublication;
	
	@Column(name="featured", columnDefinition="INT(1)")
	private Boolean featured;
	
	@Column(name="friendly_url")
	private String friendlyUrl;
	

	public PublicationType getType() {
		return type;
	}

	public void setType(PublicationType type) {
		this.type = type;
	}

	public String getFriendlyUrl() {
		return friendlyUrl;
	}

	public void setFriendlyUrl(String friendlyUrl) {
		this.friendlyUrl = friendlyUrl;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}

	public Boolean getPublicPublication() {
		return publicPublication;
	}

	public void setPublicPublication(Boolean publicPublication) {
		this.publicPublication = publicPublication;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Publication(ProfileDTO profile, Date created, Date modified, Boolean publicPublication){
		this.setProfile(profile);
		this.created  = created;
		this.modified = modified;
		this.publicPublication = publicPublication;
	}
	
	public Publication(){
		
	}
	
	public PublicationMock convertToMock(){
		PublicationMock publicationMock = new PublicationMock();
		publicationMock.setId(this.id);
		
		return publicationMock;
	}

	public Dossier getDossier() {
		return dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}
	
	@Transient
	private ProfileDTO profile;
	
	public ProfileDTO getProfile() {
		return profile;
	}

	public void setProfile(ProfileDTO profile) {
		this.profile = profile;
	}
	
	@Column(name="profile_id")
	private long profileID;
	
	public long getProfileID() {
		return profileID;
	}


	public void setProfileID(long profileID) {
		this.profileID = profileID;
	}
	

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	public Set<Dossier> getDossiers() {
		return dossiers;
	}

	public void setDossiers(Set<Dossier> dossiers) {
		this.dossiers = dossiers;
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
				upload.setPublication(this, false);
			}
		}
	}
	
	public void removeUpload(Upload upload){
		getUploads().remove(upload);
	}
	
	
}
