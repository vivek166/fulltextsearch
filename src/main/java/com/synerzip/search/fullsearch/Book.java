package com.synerzip.search.fullsearch;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "BOOK")
@Indexed
public class Book implements Serializable {
	private static final long serialVersionUID = -5129783468137830152L;
	private Long id = null;
	private String name = null;
	private String author = null;

	public Book() {
		super();
	}

	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOK_ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "BOOK_NAME")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "BOOK_AUTHOR")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "MHSBookEntityBean [id=" + id + ", name=" + name + ", author="
				+ author + "]";
	}
}