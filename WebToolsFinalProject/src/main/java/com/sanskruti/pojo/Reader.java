package com.sanskruti.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Table;

@Entity
@Table(name="Reader_Table")
public class Reader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private long rid;
	
	@Column(name="name")
	private String name;
	
	@Column
	private String lastSearch;
	
	
	public String getLastSearch() {
		return lastSearch;
	}

	public void setLastSearch(String lastSearch) {
		this.lastSearch = lastSearch;
	}

	@Column(nullable = false)
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="reader_writer",joinColumns =@JoinColumn(name="wid"),inverseJoinColumns = @JoinColumn(name="rid"))
	private List<Writer> favouritereads = new ArrayList<Writer>();
	
	
	public List<Writer> getFavouritereads() {
		return favouritereads;
	}

	public void setFavouritereads(List<Writer> favouritereads) {
		this.favouritereads = favouritereads;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Writer> getArticles() {
//		return articles;
//	}
//
//	public void setArticles(List<Writer> articles) {
//		this.articles = articles;
//	}
	
	
	
}
