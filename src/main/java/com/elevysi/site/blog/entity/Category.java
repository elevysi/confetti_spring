package com.elevysi.site.blog.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "categories")
public class Category extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2831035732242743941L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Integer order;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
	private Set<Publication> publications = new HashSet<Publication>();


	public Set<Publication> getPublications() {
		return publications;
	}


	public void setPublications(Set<Publication> publications) {
		this.publications = publications;
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


	public Integer getOrder() {
		return order;
	}


	public void setOrder(Integer order) {
		this.order = order;
	}


	

}
