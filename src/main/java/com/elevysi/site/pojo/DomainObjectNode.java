package com.elevysi.site.pojo;

import java.util.HashSet;

import com.elevysi.site.entity.Upload;


public class DomainObjectNode<T> {
	private T domainObject;
	private Upload avatar;
	private HashSet<DomainObjectNode<T>> children = new HashSet<DomainObjectNode<T>>();
	
	//Inner class to allow this node to hold different data
	public class ChildDomainObjectNode<T1> extends DomainObjectNode<T>{
	}
	
//	private ChildDomainObjectNode<T1>;

	public T getDomainObject() {
		return domainObject;
	}
	
	public DomainObjectNode(T t){
		this.domainObject = t;
	}
	
	public DomainObjectNode(T t, Upload avatar){
		this.domainObject = t;
		this.avatar = avatar;
	}
	
	public DomainObjectNode(){
	}

	public void setDomainObject(T domainObject) {
		this.domainObject = domainObject;
	}

	public HashSet<DomainObjectNode<T>> getChildren() {
		return children;
	}

	public void setChildren(HashSet<DomainObjectNode<T>> children) {
		this.children = children;
	}
	
	public void addChild(DomainObjectNode<T> node){
		if(node != null){
			if(this.getChildren().contains(node)){
				this.getChildren().remove(node);
				this.getChildren().add(node);
			}else{
				this.getChildren().add(node);
			}
			
			this.getChildren().add(node);
		}
	}
	
	public void removeChild(T node){
		this.getChildren().remove(node);
	}

	public Upload getAvatar() {
		return avatar;
	}

	public void setAvatar(Upload avatar) {
		this.avatar = avatar;
	}
}
