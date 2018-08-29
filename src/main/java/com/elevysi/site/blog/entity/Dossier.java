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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.elevysi.site.commons.dto.ProfileDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Transient
	private ProfileDTO profile;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="publication_id")
	private Publication publication;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "dossiers", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private Set<Publication> publications = new HashSet<Publication>();

	private String uuid;
	
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public ProfileDTO getProfile() {
		return profile;
	}

	public void setProfile(ProfileDTO profile) {
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
	
	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
	@Column(name="profile_id")
	private long profileID;
	
	public long getProfileID() {
		return profileID;
	}


	public void setProfileID(long profileID) {
		this.profileID = profileID;
	}
	
	public Set<Publication> getPublications() {
		return publications;
	}


	public void setPublications(Set<Publication> publications) {
		this.publications = publications;
	}
	
}
