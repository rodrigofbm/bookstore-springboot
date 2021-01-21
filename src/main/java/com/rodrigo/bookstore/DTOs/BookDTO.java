package com.rodrigo.bookstore.DTOs;

import java.io.Serializable;

import com.rodrigo.bookstore.domain.Book;

public class BookDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String title;
	private String authorName;
	private String text;
	
	public BookDTO() {
	}

	public BookDTO(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.authorName = book.getAuthorName();
		this.text = book.getText();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
