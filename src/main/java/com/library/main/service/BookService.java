package com.library.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.BookRepository;
import com.library.main.model.Book;
@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public void postBook(Book book) {
		
		bookRepository.save(book);
	}

}
