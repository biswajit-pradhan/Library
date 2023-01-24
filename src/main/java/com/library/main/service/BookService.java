package com.library.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.BookRepository;
import com.library.main.model.Book;
@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public Optional<Book> getBookByID(int bookId) {
		Optional<Book> optional = bookRepository.findById(bookId);
		return optional;
	}

}
