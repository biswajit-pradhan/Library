package com.library.main.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="author_name")
	private String name;
	@ManyToMany
	private List<Book> book;
	@OneToOne
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBook() {
		return book;
	}
	public void setBook(List<Book> book) {
		this.book = book;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Author() {}
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", book=" + book + ", user=" + user + "]";
	}
	public Author(int id, String name, List<Book> book, User user) {
		super();
		this.id = id;
		this.name = name;
		this.book = book;
		this.user = user;
	}
	
}
