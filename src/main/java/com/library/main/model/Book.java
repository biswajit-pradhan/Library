package com.library.main.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="book_name")
	private String name;
	@Column(name="book_price")
	private double price;
	@ManyToOne
	private Publisher publisher;
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", publisher=" + publisher + "]";
	}
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Book(int id, String name, double price, Publisher publisher) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.publisher = publisher;
	}
	public Book() {}
}
