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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="Writer_Table")
public class Writer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private long wid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="title")
	private String title;
	
//	@Column(name="workFile")
//	private String workFile;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Article> article= new ArrayList<Article>();
	
	
	@ManyToMany( mappedBy = "favouritereads" , cascade = {CascadeType.ALL})
	private List<Reader> favs = new ArrayList<Reader>();
	

	public List<Reader> getFavs() {
		return favs;
	}

	public void setFavs(List<Reader> favs) {
		this.favs = favs;
	}

	@Transient
	private MultipartFile pdf;
	
	@Column(name="genre")
	private String genre;
	
	
	public long getWid() {
		return wid;
	}

	public void setWid(long wid) {
		this.wid = wid;
	}

	public Collection<Article> getArticle() {
		return article;
	}

	public void setArticle(Collection<Article> article) {
		this.article = article;
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

//	public String getWorkFile() {
//		return workFile;
//	}
//
//	public void setWorkFile(String workFile) {
//		this.workFile = workFile;
//	}

	public MultipartFile getPdf() {
		return pdf;
	}

	public void setPdf(MultipartFile pdf) {
		this.pdf = pdf;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String toString() {
		return this.name;
	}
	}

