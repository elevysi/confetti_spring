package com.elevysi.site.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="dossiers")
public class Dossier extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="public", columnDefinition="INT(1)")
	private Boolean isPublic;
	
	@Column(name="featured", columnDefinition="INT(1)")
	private Boolean isFeatured;
	
	@Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Profile profile;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dossier", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private Set<Post> posts = new HashSet<Post>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dossier", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private Set<Play> plays = new HashSet<Play>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dossier", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private Set<Album> albums = new HashSet<Album>();
	
	/**
	 * Cascade is set to merge so as when i create a dossier, i have already created the images
	 * So I need to merge (i.e. update) only rather than creating new persistence
	 * http://stackoverflow.com/questions/13370221/jpa-hibernate-detached-entity-passed-to-persist
	 */
	@OneToMany(cascade = CascadeType.MERGE, mappedBy="dossier", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@org.hibernate.annotations.Filter(name="itemTableIs", condition="link_table=:link_tableValue and display=:displayValue")
	private Set<Upload> uploads = new HashSet<Upload>();
	
	public Set<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(Set<Upload> uploads) {
		this.uploads = uploads;
	}

	private String uuid;
	
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Set<Play> getPlays() {
		return plays;
	}

	public void setPlays(Set<Play> plays) {
		this.plays = plays;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
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

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Boolean getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
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

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Dossier))
            return false;
 
        final Dossier dossier = (Dossier)object;
 
        if (id != null && dossier.getId() != null) {
            return id.equals(dossier.getId());
        }
        return false;
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
				upload.setDossier(this, false);
			}
		}
	}
	
	public void removeUpload(Upload upload){
		getUploads().remove(upload);
	}
	
	public void addPlay(Play play){
		addPlay(play, true);
	}
	
	public void addPlay(Play play, boolean set){
		if(play != null){
			
			if(this.getPlays().contains(play)){
				this.getPlays().remove(play);
				this.getPlays().add(play);
				
			}else{
				getPlays().add(play);
			}
			
			if(set){
				play.setDossier(this, false);
			}
		}
	}
	
	public void removePlay(Play play){
		getPlays().remove(play);
	}
	
	public void addPost(Post post){
		addPost(post, true);
	}
	
	public void addPost(Post post, boolean set){
		if(post != null){
			
			if(this.getPosts().contains(post)){
				this.getPosts().remove(post);
				this.getPosts().add(post);
				
			}else{
				getPosts().add(post);
			}
			
			if(set){
				post.setDossier(this, false);
			}
		}
	}
	
	public void removePost(Post post){
		getPosts().remove(post);
	}
	
	public void addAlbum(Album album){
		addAlbum(album, true);
	}
	
	
	
	public void addAlbum(Album album, boolean set){
		if(album != null){
			
			if(this.getAlbums().contains(album)){
				this.getAlbums().remove(album);
				this.getAlbums().add(album);
				
			}else{
				getAlbums().add(album);
			}
			
			if(set){
				album.setDossier(this, false);
			}
		}
	}
	
	public void removeAlbum(Album album){
		getAlbums().remove(album);
	}
	
}
