package com.elevysi.site.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;

import com.elevysi.site.commons.dto.ProfileDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

//Hibernate pp337 Filtering Data
@Entity
@Table(name = "uploads")
@FilterDefs({
	@FilterDef(name="uploadOwnerFilter", defaultCondition="link_table = 'profiles'"),
	@FilterDef(
				name="itemTableIs",
//				defaultCondition="link_table='post'",
				parameters = {
					@org.hibernate.annotations.ParamDef(name="link_tableValue", type = "java.lang.String"),
					@org.hibernate.annotations.ParamDef(name="displayValue", type = "int")
				}
			)
})

public class Upload implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8860852816106784878L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	private String title;
	private String description;
	private String filename;
	private Integer filesize;
	private String filemine;
	private String path;
	private String file_identificator;
	private String pathOriginal;
	
	
	public String getPathOriginal() {
		return pathOriginal;
	}

	public void setPathOriginal(String pathOriginal) {
		this.pathOriginal = pathOriginal;
	}

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

	private String altText;
	

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	@Column(name = "created", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name = "modified", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	@Transient
	private ProfileDTO owningProfilePicture;
	
	@Transient
	private ProfileDTO owningAvatar;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="link_id", nullable = false, insertable = false, updatable = false)
	private Publication publication;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="link_id", nullable = false, insertable = false, updatable = false)
	private Dossier dossier;
	
	
	public Publication getPublication() {
		return publication;
	}

	public void setPost(Publication publication) {
		this.publication = publication;
	}
	public void setPublication(Publication publication, boolean add){
		this.publication = publication;
		if(publication != null && add){
			publication.addUpload(this, false);
		}
	}
	
	
	@Column(name="profile_id")
	private Integer profileID;
	
	public Integer getProfileID() {
		return profileID;
	}

	public void setProfileID(Integer profileID) {
		this.profileID = profileID;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "album_id", referencedColumnName = "id")
	private Album album;
	
	
	private Integer position;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type", referencedColumnName = "id")
	private MediaKind mediaType;
	
	private String url;
	
	private String keyIdentification;
	
	

	public String getKeyIdentification() {
		return keyIdentification;
	}

	public void setKeyIdentification(String keyIdentification) {
		this.keyIdentification = keyIdentification;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MediaKind getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaKind mediaType) {
		this.mediaType = mediaType;
	}

	private String uuid;
	
	@Column(columnDefinition="INT(1)")
	private boolean display;
	
	
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public void setAlbum(Album album, boolean add) {
		this.album = album;
		if(album != null && add){
			album.addUpload(this, false);
		}
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public ProfileDTO getOwningProfilePicture() {
		return owningProfilePicture;
	}

	public void setOwningProfilePicture(ProfileDTO owningProfilePicture) {
		this.owningProfilePicture = owningProfilePicture;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getFilesize() {
		return filesize;
	}

	public void setFilesize(Integer filesize) {
		this.filesize = filesize;
	}

	public String getFilemine() {
		return filemine;
	}

	public void setFilemine(String filemine) {
		this.filemine = filemine;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFile_identificator() {
		return file_identificator;
	}

	public void setFile_identificator(String file_identificator) {
		this.file_identificator = file_identificator;
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
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Upload))
            return false;
 
        final Upload upload = (Upload)object;
 
        if (id != null && upload.getId() != null) {
            return id.equals(upload.getId());
        }
        return false;
    }
	
	/**
	 * https://www.mkyong.com/java/java-how-to-overrides-equals-and-hashcode/
	 * http://www.baeldung.com/java-hashcode
	 */
	@Override
	public int hashCode() {
//	    return id.hashCode();
	    return id != null ? id.hashCode() : 0; //https://stackoverflow.com/questions/21535029/what-must-be-hashcode-of-null-objects-in-java
	}
	
	
	
	public static String generateUUID(){
		 UUID uniqueKey = UUID.randomUUID();   
		 return uniqueKey.toString();
		 
	}
	
	public Object clone() {
		Upload upload = new Upload();
		
		upload.setUrl(this.url);
		upload.setMediaType(this.mediaType);
		upload.setFilename(this.filename);
		upload.setTitle(this.title);
		upload.setDescription(this.description);
		upload.setFilemine(this.filemine);
		upload.setFilename(this.filename);
		upload.setFilesize(this.filesize);
		upload.setPath(this.path);
		upload.setAltText(this.altText);
		
		return upload;
	}
	

}
