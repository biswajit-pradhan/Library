package com.library.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ReaderBook {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Book book;
	@ManyToOne
	private Reader reader;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	@Override
	public String toString() {
		return "ReaderBook [id=" + id + ", book=" + book + ", reader=" + reader + "]";
	}
	public ReaderBook(int id, Book book, Reader reader) {
		super();
		this.id = id;
		this.book = book;
		this.reader = reader;
	}
	public ReaderBook() {}
}
