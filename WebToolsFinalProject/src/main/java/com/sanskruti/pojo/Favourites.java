package com.sanskruti.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Favourites_Table")
public class Favourites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="title")
	private String title;
	
	
//	@ManyToMany
//	private Set<Reader> favs = new HashSet<Reader>();
	
	
//	public Set<Reader> getFavs() {
//		return favs;
//	}
//
//	public void setFavs(Set<Reader> favs) {
//		this.favs = favs;
//	}

	public long getId() {
		return id;
	}

	public Favourites(long id, String name, String title) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
