package com.library.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="publisher_name")
	private String name;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Publisher() {}
	public Publisher(int id, String name, User user) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
	}
	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + ", user=" + user + "]";
	}
	
}
